This project is a demo for a micro-service.
Inside the micro-service, it contains:
  * Eureka Server
  * Book Service
  * Zuul Gateway


-------------------------------------------------------------------
h1. Precondition:
Install JDK1.8, and set environment variable: JAVA_HOME
Install Maven3 (3.5.0), and set environment variable: MAVEN_HOME


-------------------------------------------------------------------
h1. How to import the project into Eclipse
> cd demo-projects
> mvn eclipse:eclipse
Then open your Eclipse to import the project.


-------------------------------------------------------------------
1. Start Eureka Server
How to build:
> cd demo-projects\demo-eureka-server\
> mvn clean package

How to Startup:
> cd demo-projects\demo-eureka-server\target\
> java -jar demo-eureka-server-1.0-SNAPSHOT.jar

URL Links:
#Visit the Eureka Server website to view the micro services:
    http://localhost:8000/


-------------------------------------------------------------------
2. Start up the Book-Service
How to build:
> cd demo-projects\demo-microservice-bookstore
> mvn clean package

How to Startup:
> cd demo-projects\demo-microservice-bookstore\target\
> java -jar demo-microservice-bookstore-1.0-SNAPSHOT.jar

URL Links:
#View Swagger-UI to test the Rest api:
    http://localhost:8010/swagger-ui.html
#View Rest api docs:
    http://localhost:8010/v2/api-docs
#Get all the books:
    http://localhost:8010/books
#View a book by id:
    http://localhost:8010/books/2
#Find books by keywork "Java": http://localhost:8010/books/search?keyword=Java


-------------------------------------------------------------------
3. Start up the Zuul Gateway
How to build:
> cd demo-projects\demo-gateway
> mvn clean package

How to Startup:
> cd demo-projects\demo-gateway\target\
> java -jar demo-gateway-1.0-SNAPSHOT.jar

URL Links: (Visit book-service api via Zuul gateway)
http://localhost:8020/gateway-bookapi/books
http://localhost:8020/gateway-bookapi/book/1


-------------------------------------------------------------------