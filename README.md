# Project Overview
A practice project

# Tech Stack
- JAVA 16
- Spring Boot
- Spring Cloud
- JPA
- Maven

# Concepts

## Web Service
- designed for application to application interaction
- platform independent
- allow communication over network
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
- transport over MQ and http
- Service Definition is done with WSDL which defines endpoint, operatios, request/response structure 

## REST
- Rest is an architectural approach
- no  restriction on data exchange format
- transport is only over http
- a resource in REST is what we exposed over http
- resources have URI(Uniform Resource Identifier)
- service definition is not mandatory, can be done with Swagger, WADl

### Richardson Maturity Model
- Level 0: Expose SOAP with Rest
- Level 1: Expose resources with proper URI
- Level 2: Level 1 + proper HTTP methods
- Level 3: Level 2 + Next possible actions/HATEOAS

### Best Practices
- Consumer first : is it a mobile/web app?
- Make best use of HTTP(Request methods, response status)
- No secure info in URI
- Use plurals
- Use noun for resources(for complicated cases, define a standard)

## HTTP
- Hyper Text Transfer Protocol
- http actions: Get, put, post, delete, fetch ...
- http status: 200, 404

## Microservices
- small, independent REST services that work together 
- have small deployable units
- cloud enabled: adding/taking down instances, does not need a lot of configuration

### Advantages
- Easy to adapt new technology: Each service can use different technology
- Dynamic Scaling: cloud enabled services can scale dynamically depending on load
- Faster Release: smaller components enable faster release

### Challenges
- Bounded Context(how to identify what each of the microservices do?can be an evolutionary process(Domain Driven Design)
- Configuration Management (since there can be 100s of microservice, we have to manage tons of configurations)
- Dynamic scale up and scale down of each service(increasing and decreasing instance)
- Dynamic load balancing(making sure load is distributed between instances)
- Visibility(what is happening in each service? to determine which service caused an issue, need centralized log)
- Pack of Cards(if architecture is bad, they can collapse easily, need fault tolerance)


# On Spring 

## Spring Framework
- main feature is Dependency Injection/Inversion of Control(IoC)
- helps to make loosely couples application
- does not offer embedded server or im memory database
- have to configure pom.xml

## Spring MVC
- A model-view-controller based web framework under Spring
- has readymade feature for web applications, but needs manual build configuration and deployment descriptor
- the dependencies are specified seperately

## Spring Boot
- a module of Spring framework for packaging with defaults
- main features is auto-configuration, reduces boilerplate code
- offers embedded server like Tomcat and in memory database
- has "starter" in pom.xml which downloads the dependencies, and wraps them in single unit
- reduces development time

### Dispatcher Servlet
- After receiving a request, DispatcherServlet analyzes it, determines which controller should handle the request 
using HandlerMapping strategies(defined in application's configuration)
- After finding the appropriate controller, DispatcherServlet calls the respective controller method
- When controller produces a response, HttpMessageConverter(like Jackson) and DispatcherServlet work together to convert Java Objects into JSON

### Spring Boot Autoconfiguration
- Spring Boot checks the frameworks available on the classpath and given configuration
- depending on these 2 Spring Boot provides basic configuration needed for the application

## Spring Boot Architecture
Spring Boot has 4 layers:  

### Presentation Layer/Controller
- handles user request, manages the data flow between client and business logic
- components are Dispatcher Servlet and controllers
- controllers process requests, interacts with services and prepares response
- Dispatcher Servlet is a part of Spring Web MVC and works like a front controller

### Business Layer/Service
- intermediary between controller and repository
- contains business logics and validation 

### Persistence Layer/ Repository
- handles the interactions with Data sources

### Database Layer/ Model
- Represents the domain entities/entity classes or data structures used in the application.

## Spring Cloud
- provides solution to some challenges of microservices.
- Configuration Management: In Spring Cloud Config Server we can save all the configurations of all services, can expose all those services
- Dynamic scale up and scale down: Eureka provides naming server and service discovery options
- Dynamic load balancing: Ribbon can be used to ensure that the load is distributed
- Visibility and Monitoring: Spring Cloud Slout can be used to assign ID to requests across multiple components. Zipkin can trace request across components. To avoid implementing common solutions in every service, we can use API Gateway.
- To handle fault tolerence, we can use hystrix