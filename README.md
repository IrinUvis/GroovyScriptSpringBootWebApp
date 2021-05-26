<h1> Groovy Script Holder Web App</h1>

<h3>This is a web-app written in Java, that implements 
all CRUD functionalities and REST api.</h3>

### Technologies used:
* Spring Libraries:
  * Web
  * Boot
  * Data JPA
* Flyway Migration Tool
* H2 in-memory database
* Docker containerization
* Swagger-UI
* Maven
* JUnit

### How to use:
* There is a Dockerfile inside the project folder. Use it to create the image, 
  which then can be used to create a container and run the app.
* By default, the app will start on port 8080 of localhost. 
  Just typing localhost:{port} inside your web browser won't wield satisfying results though.
  The implemented api is accessible under localhost:{port}/api/groovyScripts, 
  and typing that, should return a json with two Groovy Scripts inside. 
  These scripts are there thanks to Flyway and migrations set up.
* In order to test the api, I suggest entering localhost:{port}/swagger-ui.
  Inside you will find all accessible RestController api methods with description 
  of what they are doing and how they should be used.
* The console of H2-database should be available under localhost:{port}/swagger-ui/console.
  In order to enter it use these settings:
  * Driver class: org.h2.Driver
  * JDBC URL: jdbc:h2:mem:testdb
  * User Name: sa
  * Password:
  
    
