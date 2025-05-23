Spring Boot REST API Course
Welcome to the Spring Boot REST API Course! This course guides you through building modern, scalable RESTful APIs using Spring Boot and Java. You'll learn the fundamentals of web development, Spring MVC, REST API design, and best practices for creating robust backend services.
Course Overview
This course is designed for developers who want to master building REST APIs with Spring Boot. You'll start with the basics of web development and Spring MVC, then progress to creating, testing, and optimizing APIs. By the end, you'll have hands-on experience building a product and user management API with proper error handling and validation.
Prerequisites

Basic knowledge of Java and object-oriented programming
Familiarity with Maven or Gradle
A code editor (e.g., IntelliJ IDEA, VS Code)
Basic understanding of HTTP and REST concepts

Course Outline

Introduction and Course Overview

Understand the course objectives and structure.
Learn what REST APIs are and their importance in modern web applications.


Setting Up Development Environment and Project Structure

Install Java, Spring Boot, and an IDE.
Create a Spring Boot project with Maven/Gradle.
Understand the project structure (src/main/java, application.properties, etc.).


How the Web Works: HTTP Requests and Responses

Learn about HTTP methods (GET, POST, PUT, DELETE, etc.).
Understand request/response cycles, headers, and status codes.


Spring MVC Basics: Model-View-Controller Pattern

Explore the MVC pattern in Spring.
Set up controllers, services, and views.


Creating Controllers and Views (Static and Dynamic with Thymeleaf)

Build controllers to handle web requests.
Create static and dynamic views using Thymeleaf templates.

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to Spring Boot!");
        return "index"; // Renders index.html Thymeleaf template
    }
}


Building REST APIs with @RestController

Create RESTful endpoints using @RestController.
Handle JSON responses automatically.

@RestController
@RequestMapping("/api/users")
public class UserController {
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }
}


Returning JSON Data and Automatic Serialization

Use Jackson for automatic JSON serialization/deserialization.
Customize JSON output with annotations.


Using Postman for API Testing

Set up Postman to test API endpoints.
Send GET, POST, PUT, and DELETE requests.


Fetching All Users and Fetching a User by ID

Implement endpoints to retrieve all users or a single user by ID.

@GetMapping("/{id}")
public User getUserById(@PathVariable Long id) {
    return userService.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found"));
}


Handling HTTP Status Codes and ResponseEntity

Return appropriate HTTP status codes using ResponseEntity.

@GetMapping("/{id}")
public ResponseEntity<User> getUserById(@PathVariable Long id) {
    User user = userService.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    return ResponseEntity.ok(user);
}


Using DTOs (Data Transfer Objects) for API Stability

Create DTOs to decouple API responses from entity classes.
Ensure API stability and flexibility.


Mapping Entities to DTOs Manually and with MapStruct

Manually map entities to DTOs.
Use MapStruct for automated mapping.

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
}


JSON Serialization Control with Annotations

Use @JsonIgnore, @JsonProperty, etc., to control JSON output.

public class UserDTO {
    @JsonProperty("userId")
    private Long id;
    @JsonIgnore
    private String password;
}


Extracting Query Parameters and Request Headers

Handle query parameters and headers in requests.

@GetMapping
public List<User> getUsersByRole(@RequestParam String role, @RequestHeader("Authorization") String token) {
    return userService.findByRole(role);
}


Handling Request Body for POST Requests

Process JSON request bodies for creating resources.


Creating Users with POST and Returning 201 Created with Location Header

Implement POST endpoints with proper status codes and headers.

@PostMapping
public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
    User user = userService.create(userDTO);
    return ResponseEntity.created(URI.create("/api/users/" + user.getId())).body(userDTO);
}


Updating Users with PUT and PATCH, Including Validation and DTOs

Implement full (PUT) and partial (PATCH) updates with validation.


Deleting Users with DELETE and Proper Status Codes

Create DELETE endpoints with appropriate responses.

@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
}


Action-Based POST Endpoints (e.g., Change Password)

Create custom action endpoints for specific operations.

@PostMapping("/{id}/change-password")
public ResponseEntity<Void> changePassword(@PathVariable Long id, @RequestBody PasswordDTO passwordDTO) {
    userService.changePassword(id, passwordDTO);
    return ResponseEntity.ok().build();
}


Building Product APIs: Listing, Filtering by Category, Creating, Updating, Deleting

Develop a complete product API with filtering capabilities.


Optimizing Database Queries with JPQL and Entity Graph

Write efficient JPQL queries and use entity graphs to reduce database calls.

@Query("SELECT p FROM Product p WHERE p.category = :category")
List<Product> findByCategory(@Param("category") String category);


Error Handling and Validation Basics

Implement global exception handling and input validation.

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}


Best Practices for RESTful API Design and Conventions

Follow REST principles: statelessness, resource-based URLs, proper HTTP methods.
Use consistent naming, versioning, and error responses.



Getting Started

Clone the Repository
git clone https://github.com/your-repo/spring-boot-rest-api-course.git


Set Up the Project

Import the project into your IDE.
Run mvn install to download dependencies.
Configure application.properties for your database (e.g., MySQL, H2).


Run the Application
mvn spring-boot:run


Test APIs

Use Postman to test endpoints at http://localhost:8080/api.



Resources

Spring Boot Documentation
Thymeleaf Documentation
Postman Download
MapStruct Documentation

Happy coding, and enjoy building REST APIs with Spring Boot!
