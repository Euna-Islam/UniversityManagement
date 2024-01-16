# SpringBootApp
A simple Rest endpoint using Java, Springboot, JPA, Swagger, JUnit, and Mockito.

**Feature**
-Simple CRUD App for courses

**Tech Stack:**
Java,
Springboot,
JPA,
Rest Endpoint,
Junit-Mockito

**Main Directory Structure:**

In the source directory, the java classes have been organized based on the functionality of the classes. The controller has the controller class which contains the rest endpoints, the repository folder holds the repository class which extends the JPA repository, and so on. Here is a snapshot of the directory structure:

![image](https://user-images.githubusercontent.com/15722492/154832755-072c411d-8c01-411b-b191-77ac1fef2d97.png)


**Workflow**
1. Request is received in the rest endpoint, and the request is validated with annotations in the model class. 
2. If a valid request is received, it will be forwarded to the service class. 
3. The service class calls the repository class to perform DB action, based on the DB response it will either give you a status-200 response or a "NoRecordFound" exception.

![image](https://user-images.githubusercontent.com/15722492/154835757-2536f53a-5fd5-4de0-81d4-4926d527614e.png)

