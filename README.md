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
  * Use 'docker run -dp {hostport}:8080 {image name of choice}'
* Now the application is working with use of {hostport}.
  Just typing localhost:{hostport} inside your web browser won't wield satisfying results though.
  That's because implemented api is accessible under localhost:{hostport}/api/groovyScripts, 
  and typing that, should return a json with two Groovy Scripts inside. 
  These scripts are there thanks to Flyway and migrations set up.
* In order to test the api, I suggest entering localhost:{hostport}/swagger-ui/.
  Inside you will find all accessible RestController api methods with description 
  of what they are doing and how they should be used.
  
### About the scripts
* They are stored as Strings in the database
* Subsequent arguments used in the script have to be called arg0, arg1 and so on.
* There is no limit to how many arguments can be passed to the script.
* Arguments are always passed as strings. For that reason if you want to use integer you need to include the conversion in the script.
* The result of the script has to be string. After first start of the application there are two scripts present. Take them as example:
  * The first one is a simple ["Hello " + arg0]. Because passed arguments arguments are strings by default and we want to perform concatenation in this script, no argument conversions are required. The result of this script is a string as well, so no result conversion is needed either.
  * The second script is more interesting. It's supposed to perform an add mathematical operation. The script's text is [(arg0.toInteger() + arg1.toInteger()).toString()]. As you can see arguments' conversion to integer are required and because the result of the addition is a number we need to convert the result to String, because that's the type in which the result has to be.
* If the script can't compile, not enough arguments are provided or type of return value isn't String, the server will respond with an error message.
  
    
