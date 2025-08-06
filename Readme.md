# Microservices Quiz Project

This project is a work-in-progress microservices-based quiz application. Currently, only one service is implemented; additional services are planned and under development.

## Project Structure

- `Question-sevicse1/`  
  Contains the initial microservice for handling quiz questions.

## Getting Started

1. **Clone the repository:**
   ```sh
   git clone <repository-url>
   ```

2. **Navigate to the service directory:**
   ```sh
   cd Question-sevicse1
   ```

3. **Build the service:**# Microservices Quiz Project

A scalable, modular quiz application built using microservices architecture with Spring Boot. The project is under active development. This README provides a detailed overview of the planned architecture, current status, and how to get started.

---

## Architecture Overview

The system is designed with the following components:

- **API Gateway**: Entry point for all client requests. Handles routing, load balancing, and security.
- **Eureka Server**: Service registry for dynamic discovery of microservice instances.
- **Question Service**: Manages quiz questions. Multiple instances can run on different ports (e.g., 8081, 8082, 8083).
- **Quiz Service**: Handles quiz logic, interacts with Question Service using OpenFeign (planned, e.g., ports 8091, 8092).
- **OpenFeign**: For inter-service REST communication.
- **Databases**: Each service manages its own database.

**Sample Request Flow:**
```
Client → API Gateway (localhost:8765) → Eureka Service Discovery → Microservices (Quiz/Question)
```
Example endpoint:  
`http://localhost:8765/quiz-service/quiz/get/3`

---

## Architecture Diagram

You can find the architecture diagram in the `docs/` folder or refer to the image below:

![Architecture Diagram](docs/architecture-diagram.jpg)

---

## Project Structure

```
Microservises_Quiz/
│
├── api-gateway/           # API Gateway service (planned)
├── eureka-server/         # Eureka service registry (planned)
├── question-service/      # Handles quiz questions (implemented)
│   ├── src/
│   └── ...
├── quiz-service/          # Handles quiz logic (planned)
├── docs/
│   └── architecture-diagram.jpg  # Architecture diagram
├── docker-compose.yml     # For containerized deployment (planned)
└── README.md
```

---

## Getting Started

### Prerequisites

- Java 17+
- Maven
- (Optional) Docker

### Steps

1. **Clone the repository:**
   ```sh
   git clone <repository-url>
   ```

2. **Navigate to the Question Service:**
   ```sh
   cd question-service
   ```

3. **Build the service:**
   ```sh
   ./mvnw clean install
   ```

4. **Run the service:**
   ```sh
   ./mvnw spring-boot:run
   ```

> _Repeat similar steps for other services as they are implemented._

---

## Planned Microservices

- **Question Service**: CRUD operations for quiz questions (implemented).
- **Quiz Service**: Quiz logic, interacts with Question Service (planned).
- **User Service**: User management and authentication (planned).
- **Result Service**: Stores and retrieves quiz results (planned).
- **API Gateway**: Unified entry point for all services (planned).
- **Eureka Server**: Service discovery (planned).

---

## Roadmap

- [x] Implement initial Question Service
- [ ] Add Quiz Service
- [ ] Add Eureka Server
- [ ] Add API Gateway
- [ ] Integrate services with OpenFeign
- [ ] Add User and Result Services
- [ ] Add frontend
- [ ] Add Docker support

---

## Contributing

Contributions are welcome! Please open issues or submit pull requests.  
For major changes, please open an issue first to discuss what you would like to change.

---

## License

This project is licensed under the MIT
   ```sh
   ./mvnw clean install
   ```

4. **Run the service:**
   ```sh
   ./mvnw spring-boot:run
   ```

## Roadmap

- [x] Implement initial question service
- [ ] Add more microservices (e.g., user, results, authentication)
- [ ] Integrate services
- [ ] Add frontend

## Contributing

Contributions are welcome! Please open issues or submit pull requests.

## License

This project is licensed under the MIT License.