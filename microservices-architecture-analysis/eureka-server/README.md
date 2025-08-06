# Eureka Server Documentation

This README file provides specific documentation for the Eureka Server, which is a key component of the microservices architecture. The Eureka Server is responsible for service discovery, allowing microservices to find and communicate with each other dynamically.

## Purpose

The Eureka Server acts as a service registry for microservices. It enables services to register themselves at runtime and discover other services for communication. This is essential for maintaining a resilient and scalable microservices architecture.

## Project Structure

- `src/`  
  Contains the source code for the Eureka Server, including configuration files and application logic.

## Getting Started

1. **Clone the repository:**
   ```sh
   git clone <repository-url>
   ```

2. **Navigate to the Eureka Server directory:**
   ```sh
   cd eureka-server
   ```

3. **Build the service:**
   ```sh
   ./mvnw clean install
   ```

4. **Run the service:**
   ```sh
   ./mvnw spring-boot:run
   ```

## Configuration

The Eureka Server can be configured using the `application.yml` file located in the `src/main/resources` directory. Key configurations include:

- `server.port`: The port on which the Eureka Server will run.
- `eureka.client.registerWithEureka`: Set to `false` for the Eureka Server itself.
- `eureka.client.fetchRegistry`: Set to `false` for the Eureka Server itself.

## Usage

Once the Eureka Server is running, other microservices can register themselves with it by including the appropriate Eureka client dependencies and configurations. You can access the Eureka dashboard by navigating to `http://localhost:<port>/eureka` in your web browser.

## Roadmap

- [x] Implement basic service registration and discovery
- [ ] Add support for service health checks
- [ ] Implement security features for service registration
- [ ] Enhance the dashboard with additional metrics

## Contributing

Contributions are welcome! Please open issues or submit pull requests to improve the Eureka Server functionality.

## License

This project is licensed under the MIT License.