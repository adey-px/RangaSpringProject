### Project Source
https://github.com/in28minutes/master-spring-and-spring-boot

### Keyword Note
Create Project
- open https://start.spring.io follow guide to create new maven-spring project, download and open with vs code; project folder structure is as stated below:
- Artifact is `gamerunner` which reps core application Class consisting of `main` method requires by JVM to run the project  
- Source folder is `artifact/src/main/java`, Package name is `com/codera/artifact/file-name`; Group name is `codera` specified

POJO: means plain old java object
- refers to any java object
- has no constraints

Java Bean: object with 3 constraints
- has public default with no argument constructors
- allows access to properties thru getter and setter methods
- implements java.io.Serializable

Spring Bean:
- java object that is managed by spring framework inside a spring application
- spring uses ioc container (Bean factory or Application context) to manage it
