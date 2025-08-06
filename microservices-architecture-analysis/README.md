# Microservices Architecture Analysis Project

This project is designed to analyze and demonstrate a microservices architecture for a quiz application. It includes multiple services that work together to provide a complete solution.

## Project Structure

- `api-gateway/`  
  Contains the API Gateway service that routes requests to the appropriate microservices.

- `eureka-server/`  
  Contains the Eureka Server for service discovery.

- `question-service/`  
  Manages quiz questions.

- `quiz-service/`  
  Handles quiz-related operations.

- `docs/`  
  Contains documentation, including architecture diagrams.

- `scripts/`  
  Contains setup scripts for the project.

## Getting Started

1. **Clone the repository:**
   ```sh
   git clone <repository-url>
   ```

2. **Navigate to the project directory:**
   ```sh
   cd microservices-architecture-analysis
   ```

3. **Set up the environment:**
   ```sh
   ./scripts/setup.sh
   ```

4. **Run the services using Docker Compose:**
   ```sh
   docker-compose up
   ```

## Roadmap

- [x] Implement API Gateway
- [x] Implement Eureka Server
- [x] Implement Question Service
- [x] Implement Quiz Service
- [ ] Add monitoring and logging
- [ ] Integrate frontend application

## Contributing

Contributions are welcome! Please open issues or submit pull requests.

## License

This project is licensed under the MIT License.