# Quiz Service Documentation

This README file provides specific documentation for the Quiz Service within the Microservices Architecture Analysis Project. The Quiz Service is responsible for handling all operations related to quizzes, including creating, retrieving, updating, and deleting quizzes.

## Purpose

The Quiz Service is designed to manage quiz-related functionalities, allowing users to interact with quizzes effectively. It communicates with other services, such as the Question Service, to provide a seamless experience.

## Project Structure

- `src/`  
  Contains the source code for the Quiz Service, including controllers, services, and data models.

## Getting Started

1. **Clone the repository:**
   ```sh
   git clone <repository-url>
   ```

2. **Navigate to the quiz service directory:**
   ```sh
   cd quiz-service
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

- **Create Quiz**
  - `POST /quizzes`
  - Request body: Quiz details
  - Response: Created quiz object

- **Get All Quizzes**
  - `GET /quizzes`
  - Response: List of quizzes

- **Get Quiz by ID**
  - `GET /quizzes/{id}`
  - Response: Quiz object

- **Update Quiz**
  - `PUT /quizzes/{id}`
  - Request body: Updated quiz details
  - Response: Updated quiz object

- **Delete Quiz**
  - `DELETE /quizzes/{id}`
  - Response: Success message

## Roadmap

- [x] Implement quiz creation and management
- [ ] Add quiz statistics and analytics
- [ ] Integrate with the Question Service for question management
- [ ] Implement user-specific quiz features

## Contributing

Contributions are welcome! Please open issues or submit pull requests.

## License

This project is licensed under the MIT License.