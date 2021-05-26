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
  In order to achieve this, perform these steps (you need to have Docker installed):
  * Enter current project's main directory (Dockerfile should be present there)
  * Use 'mvn clean package' (if you have maven installed)
  * If you don't have maven installed use 'mvnw.cmd clean package' (Windows) or 'mvnw clean package' (Linux/Mac)
  * Use 'docker build -t {image name of choice} .' ('.' stands for current directory)
  * Use 'docker run -dp {hostport}:8080'
* Now the application is working with use of {hostport}.
  Just typing localhost:{hostport} inside your web browser won't wield satisfying results though.
  That's because implemented api is accessible under localhost:{hostport}/api/groovyScripts, 
  and typing that, should return a json with two Groovy Scripts inside. 
  These scripts are there thanks to Flyway and migrations set up.
* In order to test the api, I suggest entering localhost:{hostport}/swagger-ui/.
  Inside you will find all accessible RestController api methods with description 
  of what they are doing and how they should be used.
  
    
