# Question Service Documentation

This document provides specific information about the Question Service within the Microservices Architecture Analysis Project. The Question Service is responsible for managing quiz questions, including creating, retrieving, updating, and deleting questions.

## Purpose

The Question Service serves as a dedicated microservice for handling all operations related to quiz questions. It allows other services to interact with the question data through a well-defined API.

## Project Structure

- `src/`  
  Contains the source code for the Question Service, including controllers, services, and data access layers.

## Getting Started

1. **Clone the repository:**
   ```sh
   git clone <repository-url>
   ```

2. **Navigate to the Question Service directory:**
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

## API Endpoints

- **Create Question**
  - `POST /questions`
  - Request Body: JSON representation of the question.
  
- **Get All Questions**
  - `GET /questions`
  
- **Get Question by ID**
  - `GET /questions/{id}`
  
- **Update Question**
  - `PUT /questions/{id}`
  - Request Body: JSON representation of the updated question.
  
- **Delete Question**
  - `DELETE /questions/{id}`

## Usage Examples

### Create a Question

```sh
curl -X POST http://localhost:8080/questions -H "Content-Type: application/json" -d '{"question": "What is the capital of France?", "options": ["Paris", "London", "Berlin"], "answer": "Paris"}'
```

### Get All Questions

```sh
curl -X GET http://localhost:8080/questions
```

## Roadmap

- [x] Implement CRUD operations for questions
- [ ] Add validation for question data
- [ ] Implement pagination for question retrieval
- [ ] Integrate with other microservices

## Contributing

Contributions are welcome! Please open issues or submit pull requests.

## License

This project is licensed under the MIT License.