# Project Overview
This project is a microservice that serves as the backend for a University Management System.

# Idea
In joint master's programs across different universities, students and teachers face challenges in managing and updating their course information. My project aims to simplify this process by creating a centralized system, allowing users to enter course details only once and ensuring that updates are synchronized across various university portals, reducing redundancy and saving time.

## Table of Contents
- [Tech Stack](#tech-stack)
- [Concepts](#concepts)
  - [Webservice](#web-service)
  - [Common Terminology](#common-terminology)
  - [SOAP](#soap)
  - [REST](#rest)
  - [HTTP](#http)
  - [Microservices](#microservices)
- [Tools](#tools)
  - [Spring Framework](#spring-framework)
  - [Spring MVC](#spring-mvc)
  - [Spring Boot](#spring-boot)
  - [Spring Boot Architecture](#spring-boot-architecture)
  - [Spring Cloud](#spring-cloud)
  - [Build automation with Maven](#build-automation-with-maven)
  - [Testing Frameworks](#testing-frameworks)

# Tech Stack
- **Java 16:** Java 16 was chosen because of the plan of adding this API to a microservice system, and Java 16 offers efficient inter-process communication on the same host
- **Spring Boot:** Spring Boot was chosen for its convention-over-configuration approach, simplifying the setup and development of the application.
- **JPA:** JPA is used for object-relational mapping, allowing seamless interaction with the database.
- **SQLite:** SQLite was selected as the database engine for its lightweight, serverless architecture
- **REST:** The REST architectural style was chosen for designing the API to ensure scalability.
- **Junit-Mockito:** Junit and Mockito are used for testing the application.
- **Jacoco:** Jacoco is used for code coverage analysis
- **Swagger:** Swagger is used to automatically generate API documentation.
- **Maven:** Maven is used for project management and builds because it offers an organized approach to a development workflow.

# Concepts

## Web Service
- designed for application-to-application interaction
- platform independent
- allow communication over a network
- has request and response in formats supported by all platforms(e.g: JSON, XML)
- offers service definition(request/response formats, request structure, response structure, endpoint)

## Common Terminology
- Request: input
- Response: output
- Message Exchange Format: format of request/response(e.g: JSON, XML)
- Service Provider: server
- Service Consumer: client
- Service Definition: contract between server and client
- Transport: how is the service called? is it exposed over URL(http)? or over queue(MQ)?

## SOAP
- Simple Object Access Protocol
- XML is the request/response exchange format
- SOAP wraps the header(optional) and body inside envelop
- transport over MQ and HTTP
- Service Definition is done with WSDL which defines endpoint, operations, request/response structure 

## REST
- Rest is an architectural approach
- no  restriction on data exchange format
- transport is only over HTTP
- a resource in REST is what we exposed over HTTP
- resources have URI(Uniform Resource Identifier)
- service definition is not mandatory, can be done with Swagger, WADl

### Richardson Maturity Model
- Level 0: Expose SOAP with Rest
- Level 1: Expose resources with proper URI
- Level 2: Level 1 + proper HTTP methods
- Level 3: Level 2 + Next possible actions/HATEOAS

### Best Practices
- Consumer first: is it a mobile/web app?
- Make best use of HTTP(Request methods, response status)
- No secure info in URI
- Use plurals
- Use noun for resources(for complicated cases, define a standard)

## HTTP
- Hyper Text Transfer Protocol
- HTTP actions: Get, put, post, delete, fetch ...
- http status: 200, 404

## Microservices
- small, independent REST services that work together 
- have small deployable units
- cloud-enabled: adding/taking down instances, does not need a lot of configuration

### Advantages
- Easy to adapt to new technology: Each service can use different technology
- Dynamic Scaling: cloud-enabled services can scale dynamically depending on load
- Faster Release: smaller components enable faster release

### Challenges
- Bounded Context(how to identify what each of the microservices do?can be an evolutionary process(Domain Driven Design)
- Configuration Management (since there can be 100s of microservices, we have to manage tons of configurations)
- Dynamic scale-up and scale-down of each service(increasing and decreasing instances)
- Dynamic load balancing(making sure load is distributed between instances)
- Visibility(what is happening in each service? to determine which service caused an issue, need centralized log)
- Pack of Cards(if architecture is bad, they can collapse easily, need fault tolerance)


# Tools

## Spring Framework
- the main feature is Dependency Injection/Inversion of Control(IoC)
- helps to make loosely coupled application
- does not offer embedded server or in memory database
- have to configure pom.xml

## Spring MVC
- A model-view-controller-based web framework under Spring
- has a readymade feature for web applications, but needs manual build configuration and deployment descriptor
- the dependencies are specified separately

## Spring Boot
- a module of Spring framework for packaging with defaults
- main features are auto-configuration, reduced boilerplate code
- offers embedded servers like Tomcat and in-memory database
- has "starter" in pom.xml which downloads the dependencies, and wraps them in a single unit
- reduces development time

### Dispatcher Servlet
- After receiving a request, DispatcherServlet analyzes it, determines which controller should handle the request 
using HandlerMapping strategies(defined in application's configuration)
- After finding the appropriate controller, DispatcherServlet calls the respective controller method
- When controller produces a response, HttpMessageConverter(like Jackson) and DispatcherServlet work together to convert Java Objects into JSON

### Spring Boot Autoconfiguration
- Spring Boot checks the frameworks available on the classpath and given configuration
- depending on these 2 Spring Boot provides the basic configuration needed for the application

## Spring Boot Architecture
Spring Boot has 4 layers:  

### Presentation Layer/Controller
- handles user requests, manages the data flow between client and business logic
- components are the Dispatcher Servlet and controllers
- controllers process requests, interact with services and prepare response
- Dispatcher Servlet is a part of Spring Web MVC and works like a front controller

### Business Layer/Service
- an intermediary between controller and repository
- contains business logic and validation 

### Persistence Layer/ Repository
- handles the interactions with Data sources

### Database Layer/ Model
- Represents the domain entities/entity classes or data structures used in the application.

## Spring Cloud
- provides solutions to some challenges of microservices.
- Configuration Management: In Spring Cloud Config Server we can save all the configurations of all services, can expose all those services
- Dynamic scale up and scale down: Eureka provides naming server and service discovery options
- Dynamic load balancing: Ribbon can be used to ensure that the load is distributed
- Visibility and Monitoring: Spring Cloud Slout can be used to assign ID to requests across multiple components. Zipkin can trace request across components. To avoid implementing common solutions in every service, we can use API Gateway.
- To handle fault tolerance, we can use hystrix

## Build automation with Maven
- Build automation tool, uses XML
- Follows a convention over configuration for build life cycle
- Maven plugins and dependencies are configured in Pom, rely on central repo

## Testing Frameworks
### Junit
- Uses annotations @Test, @Before, @After for setup/define test methods
- has assertion methods for verifying expected outcomes in test cases
- supports default/custom test runners for verifying test cases
  
### Mockito
- Mocking framework that creates mock objects for testing, mock objects simulate the object's behavior without running actual code
- provides stubbing options, e.g- by using 'when' and 'thenReturn', we can define what should happen when a method is called
- we can use 'verify' to check if certain mock objects were actually called
