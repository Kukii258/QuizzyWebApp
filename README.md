# Quizzy Web Application

## Introduction
Welcome to Quizzy, a web application built using Spring Boot and various other technologies. Quizzy allows users to create, share, and take quizzes online. Users can also view leaderboards, search for other players or quizzes, and manage their own quizzes and statistics.

## Features
- **User Authentication**: Users can create an account and log in securely.
- **Quiz Creation**: Users can create their own quizzes with custom questions and answers.
- **Quiz Sharing**: Quizzes can be shared publicly or kept private for personal use.
- **Quiz Taking**: Users can take quizzes created by others and view their scores.
- **Leaderboards**: View leaderboards to see top performers.
- **Search Functionality**: Search for other players or quizzes by name or category.
- **User Statistics**: View statistics for individual users, including quiz performance and activity.

## Technologies Used
- **Spring Boot**: Framework for building web applications in Java.
- **Thymeleaf**: Server-side Java template engine for rendering HTML.
- **Spring Security**: Provides authentication and authorization.
- **Hibernate**: ORM (Object-Relational Mapping) tool for database interaction.
- **MariaDB**: Relational database management system (MySQL-compatible).
- **HTML/CSS/JavaScript**: Frontend technologies for building the user interface.
- **Bootstrap**: Frontend framework for responsive design.
- **jQuery**: JavaScript library for DOM manipulation and AJAX requests.

## Getting Started
1. **Prerequisites**: Make sure you have Java JDK, Maven, and MariaDB installed on your system.
2. **Clone the Repository**: `git clone https://github.com/yourusername/quizzy.git`
3. **Database Configuration**: Update `application.properties` with your MariaDB database configuration.
4. **Build and Run**: Execute `mvn spring-boot:run` to run the application locally.
5. **Access the Application**: Open a web browser and navigate to `http://localhost:8080` to access Quizzy.

## Code Snippets
Here are some additional important parts of the code:

### Quiz Controller
- Manages endpoints for creating quizzes, setting quiz questions, and playing quizzes.
- Handles quiz statistics, answer submission, and quiz privacy toggling.
- Retrieves quiz and player information for display.

## Contributing
Contributions are welcome! If you'd like to contribute to Quizzy, please follow these steps:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature/myfeature`).
3. Make your changes and commit them (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature/myfeature`).
5. Create a new Pull Request.
