# Course Management REST API with Java and Spring Boot
This project is a microservice that serves as the backend for a University Course Management System. It is designed to handle CRUD operations on courses offered by the university. As part of a larger microservices architecture, this microservice focuses on managing the course-related functionalities of the university.
## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Code Structure](#code-structure)
- [License](#license)

## Features
- **Course Management:**
  - Create, Read, Update, and Delete (CRUD) operations for courses.
  - Unit tests
  - Test Coverage
  - Documentation
  
## Overview
This project is  a REST API built with Java and Spring Boot. It provides endpoints for CRUD operations.

## Tech Stack
- **Java 16:** Java 16 was chosen because of the plan of adding this API to a microservice system, and Java 16 offers efficient inter-process communication on the same host
- **Spring Boot:** Spring Boot was chosen for its convention-over-configuration approach, simplifying the setup and development of the application.
- **JPA:** JPA is used for object-relational mapping, allowing seamless interaction with the database.
- **SQLite:** SQLite was selected as the database engine for its lightweight, serverless architecture
- **REST:** The REST architectural style was chosen for designing the API to ensure scalability.
- **Junit-Mockito:** Junit and Mockito are used for testing the application.
- **Jacoco:** Jacoco is used for code coverage analysis
- **Swagger:** Swagger is used to automatically generate API documentation.
- **Maven:** Maven is used for project management and builds because it offers an organized approach to a development workflow.

## Code Structure
In the source directory, the Java classes have been organized based on the functionality of the classes. The controller has the controller class which contains the rest endpoints, the repository folder holds the repository class which extends the JPA repository, and so on. Here is a snapshot of the directory structure:

![image](https://user-images.githubusercontent.com/15722492/154832755-072c411d-8c01-411b-b191-77ac1fef2d97.png)


## License
Apache 2.0
