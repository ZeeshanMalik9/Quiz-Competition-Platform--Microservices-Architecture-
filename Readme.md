# QuizZee: A Microservices-Based Quiz Contest Platform

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)](https://github.com/)
[![Java Version](https://img.shields.io/badge/java-17+-orange.svg)](https://www.java.com)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg)](https://spring.io/projects/spring-boot)

QuizZee is a modern, scalable, and resilient online quiz and contest platform designed from the ground up using a **microservices architecture**. The platform is engineered to handle everything from small-scale quizzes to large, concurrent competitive events.

This project was born from the personal experience of organizing university events and struggling to find an affordable, flexible quizzing tool. QuizZee aims to solve that problem by providing a powerful, open-source solution for student communities, educators, and organizations.

---

## 🏗️ Architecture Overview

The system is designed as a suite of independent, self-contained services that communicate over the network. This approach ensures scalability, resilience, and maintainability.

- **API Gateway**: The single entry point for all client requests. It handles routing, CORS policies, and serves as the first line of security.
- **Eureka Server**: Acts as the service registry, allowing services to dynamically discover and communicate with each other using logical names instead of hardcoded addresses.
- **Core Microservices**: Five distinct services handle the business logic for Users, Questions, Quizzes, Contests, and Submissions. Each service owns its own database.
- **Inter-Service Communication**: Services communicate synchronously using **Spring Cloud OpenFeign**, a declarative REST client that makes cross-service calls feel as simple as local method invocations.

**Sample Request Flow:**

Client App → API Gateway (localhost:8765) → Eureka Service Discovery → Target Microservice (e.g., Contest Service)

---

## 🎨 Architecture Diagram

The following diagram illustrates the high-level architecture and the interactions between the various services.

![Architecture Diagram](https://i.imgur.com/your-diagram-image-url.png) 
*(Note: Replace the URL above with a link to your diagram image after you upload it.)*

---

## 📂 Project Structure

This project is structured as a monorepo, containing all the microservices and configuration in one place for easier management and development.

QuizZee-Microservices/
│
├── api-gateway/          # Spring Cloud Gateway for routing and filters
├── eureka-server/        # Spring Cloud Netflix Eureka for service discovery
│
├── user-service/         # Manages user identity, registration, and authentication (JWT)
├── question-service/     # Manages the question bank (CRUD, generation, scoring)
├── quiz-service/         # Manages quiz templates (a list of question IDs)
├── contest-service/      # Manages the lifecycle of live contest events
└── submission-service/   # Handles user answer submissions and orchestrates scoring

---

## 🛠️ Technology Stack

- **Framework:** Spring Boot 3.x, Spring Cloud
- **Language:** Java 17
- **Service Discovery:** Spring Cloud Netflix Eureka
- **API Gateway:** Spring Cloud Gateway
- **Inter-Service Communication:** Spring Cloud OpenFeign
- **Database:** MySQL (with Spring Data JPA & Hibernate)
- **Security:** Spring Security (with JWT for stateless authentication)
- **Build Tool:** Apache Maven

---

## 🏁 Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- Apache Maven
- MySQL Server
- An IDE like IntelliJ IDEA or VS Code

### Steps to Run Locally

1.  **Clone the repository:**
    ```sh
    git clone [https://github.com/your-username/QuizZee-Microservices.git](https://github.com/your-username/QuizZee-Microservices.git)
    cd QuizZee-Microservices
    ```

2.  **Setup Databases:**
    Ensure your MySQL server is running. Create a separate database schema for each of the five core services: `user_db`, `question_db`, `quiz_db`, `contest_db`, `submission_db`.

3.  **Update Configurations:**
    Navigate into each service's `src/main/resources/application.yml` file and update the `spring.datasource` properties (URL, username, password) to match your local MySQL setup.

4.  **Build All Services:**
    From the root directory of the project, run the Maven wrapper to build all modules:
    ```sh
    ./mvnw clean install
    ```

5.  **Run the Services:**
    You must run the services in the correct order. Open a new terminal for each service.

    * **1. Eureka Server:**
        ```sh
        cd eureka-server
        ./mvnw spring-boot:run
        ```
        Wait for it to start, then check the Eureka dashboard at `http://localhost:8761`.

    * **2. API Gateway:**
        ```sh
        cd api-gateway
        ./mvnw spring-boot:run
        ```

    * **3. Core Services (in any order):**
        Open new terminals and run each of the five core services (`user-service`, `question-service`, etc.) using the same `./mvnw spring-boot:run` command from within their respective directories.

    Once all services are running, you should see them registered on the Eureka dashboard. The application is now accessible through the API Gateway at `http://localhost:8765`.

---

## 🗺️ Roadmap

This project has a solid foundation with a clear path toward becoming a feature-complete, production-grade application.

-   [x] Implement Eureka Server and API Gateway
-   [x] Implement User Service with JWT Authentication
-   [x] Implement Question Service for question bank management
-   [x] Implement Quiz Service for creating quiz templates
-   [x] Implement Contest Service for live event management
-   [x] Implement Submission Service for real-time scoring
-   [ ] **Develop a Rich User Interface** using a modern framework like **React** or **Vue**.
-   [ ] **Containerize all services** with **Docker** and create a `docker-compose.yml` for single-command setup.
-   [ ] **Implement Asynchronous Communication** with **RabbitMQ** or **Kafka** for a real-time leaderboard.
-   [ ] **Introduce a full Observability Stack** (Prometheus, Grafana, Jaeger) for monitoring and tracing.
-   [ ] **Automate with CI/CD Pipelines** using **GitHub Actions** or Jenkins.

---

## 🤝 Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".

1.  Fork the Project
2.  Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3.  Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4.  Push to the Branch (`git push origin feature/AmazingFeature`)
5.  Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License. See the `LICENSE` file for details.