# API Gateway Service

The API Gateway is a crucial component of the microservices architecture, acting as a single entry point for all client requests. It routes incoming requests to the appropriate microservices, handles authentication, and can perform load balancing and caching.

## Purpose

The API Gateway simplifies the client-side interaction with multiple microservices by providing a unified interface. It abstracts the complexity of the underlying services and allows for easier management of service endpoints.

## Project Structure

- `src/`  
  Contains the source code for the API Gateway service.

## Getting Started

1. **Clone the repository:**
   ```sh
   git clone <repository-url>
   ```

2. **Navigate to the API Gateway directory:**
   ```sh
   cd api-gateway
   ```

3. **Build the service:**
   ```sh
   ./mvnw clean install
   ```

4. **Run the service:**
   ```sh
   ./mvnw spring-boot:run
   ```

## Usage

Once the API Gateway is running, you can access the various microservices through the defined endpoints. For example:

- To get quiz questions:  
  `GET /api/questions`

- To submit a quiz:  
  `POST /api/quizzes`

## Roadmap

- [x] Implement routing to microservices
- [ ] Add authentication and authorization
- [ ] Implement rate limiting
- [ ] Enhance error handling and logging

## Contributing

Contributions are welcome! Please open issues or submit pull requests.

## License

This project is licensed under the MIT License.