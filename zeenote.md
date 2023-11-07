### Project Source
https://github.com/in28minutes/master-spring-and-spring-boot

### Keyword Note
Create Project
- open https://start.spring.io follow guide to create new maven-spring project, download and open with vs code; project folder structure is as stated below:

Folder Layout
- artifact name is `gamerunner-01` which reps core application Class consisting of `main` method requires by JVM to run the project  
- source folder is `artifact-name/src/main/java`; package name is `com/codera/artifact-name/file`; group name is `codera` mentioned

POJO means plain old java object
- refers to any java object
- has no constraints

Java Bean 
- object with 3 constraints as below
- has public default with no argument constructors
- allows access to properties thru getter and setter methods
- implements java.io.Serializable

Spring Bean
- java object that is managed by spring framework inside a spring application
- spring uses ioc container (Bean factory or Application context) to manage it
