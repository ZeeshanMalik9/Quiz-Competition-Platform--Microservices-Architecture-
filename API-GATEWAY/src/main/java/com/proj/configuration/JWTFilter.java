package com.proj.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collections;

// JWT Filter for validating tokens in API Gateway
// redundant code, not in use so remove it

@Component
public class JWTFilter implements WebFilter {
    private static final String SECRET = "defaultsecretkeydefaultsecretkey";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET.getBytes())
                        .parseClaimsJws(token)
                        .getBody();
                String username = claims.getSubject();
                if (username != null) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(username, null, Collections.singletonList(new SimpleGrantedAuthority("USER")));
                    SecurityContext context = org.springframework.security.core.context.SecurityContextHolder.createEmptyContext();
                    context.setAuthentication(authentication);
                    return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(context)));
                }
            } catch (SignatureException | IllegalArgumentException e) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
        }
        return chain.filter(exchange);
    }
}