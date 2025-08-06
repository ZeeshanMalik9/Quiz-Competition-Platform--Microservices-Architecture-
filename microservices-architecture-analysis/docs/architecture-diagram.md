# Architecture Diagram

This document provides an overview of the architecture diagram for the microservices architecture analysis project. The diagram illustrates how the various microservices interact with each other and the API Gateway.

## Overview

The architecture consists of the following components:

1. **API Gateway**: Acts as the entry point for all client requests. It routes requests to the appropriate microservices and handles cross-cutting concerns such as authentication, logging, and rate limiting.

2. **Eureka Server**: A service discovery server that allows microservices to register themselves and discover other services. This enables dynamic scaling and load balancing.

3. **Question Service**: Manages quiz questions, including CRUD operations for questions. It communicates with the API Gateway to serve requests related to quiz questions.

4. **Quiz Service**: Handles quiz-related operations, such as starting a quiz, submitting answers, and calculating scores. It also communicates with the API Gateway for client interactions.

## Diagram

![Architecture Diagram](path/to/architecture-diagram.png)

*Note: Replace `path/to/architecture-diagram.png` with the actual path to the architecture diagram image file.*

## Conclusion

This architecture provides a scalable and maintainable solution for the quiz application, leveraging microservices principles to ensure that each component can be developed, deployed, and scaled independently.