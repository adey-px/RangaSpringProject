# Step by Step Lecture Guide
## Spring Framework

## Section 03: Using Spring Framework to Create & Manage Java Objects
Step 04: Three Types of Dependency Injection
- `Constructor-based Type` in which dependencies are set by creating Bean using its constructor, refer gamerunner 01 & 02
- `Setter-based Type` in which dependencies are set by calling setter methods on the Bean
- `Field Type` in which no setter or constructor is being used to set or inject dependencies, refer to code example below

```java
package com.in28minutes.learnspringframework.examples.a1;

import java.util.Arrays;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Dependency Injection using Field, note ComponentScan on the current file
@Configuration
@ComponentScan
public class DepInjectionLauncherApplication {
	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(DepInjectionLauncherApplication.class)) {
			// check Beans that are part of this context
			Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
			System.out.println(context.getBean(YourBusinessClass.class));
		}
	}
}
```
```java
package com.in28minutes.learnspringframework.examples.a0;

import java.util.Arrays;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class SimpleSpringContextLauncherApplication {
	public static void main(String[] args) {
		try (var context = 
				new AnnotationConfigApplicationContext
					(SimpleSpringContextLauncherApplication.class)) {
			Arrays.stream(context.getBeanDefinitionNames())
				.forEach(System.out::println);
		}
	}
}
```
```java
package com.in28minutes.learnspringframework.examples.a1;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
class SampleBusinessClass {
	Dependency1 dependency1;
	Dependency2 dependency2;

	//@Autowired
	public SampleBusinessClass(Dependency1 dependency1, Dependency2 dependency2) {
		super();
		System.out.println("Constructor Injection - SampleBusinessClass ");
		this.dependency1 = dependency1;
		this.dependency2 = dependency2;
	}

	//	@Autowired
	//	public void setDependency1(Dependency1 dependency1) {
	//		System.out.println("Setter Injection - setDependency1 ");
	//		this.dependency1 = dependency1;
	//	}

	//	@Autowired
	//	public void setDependency2(Dependency2 dependency2) {
	//		System.out.println("Setter Injection - setDependency2 ");
	//		this.dependency2 = dependency2;
	//	}

	public String toString() {
		return "Using " + dependency1 + " and " + dependency2;
	}
}

@Component
class Dependency1 {
}

@Component
class Dependency2 {
}
```

### Step 08
- Step 05 - Java Spring Framework - Understanding Important Terminology
- Step 06 - Java Spring Framework - Comparing @Component vs @Bean
- Step 07 - Why do we have dependencies in Java Spring Applications?
- Step 08 - Exercise/ Solution for Real World Java Spring Framework Example

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/c1/BusinessCalculationService.java New

```java
package com.in28minutes.learnspringframework.examples.c1;

import java.util.Arrays;
import org.springframework.stereotype.Component;

@Component
public class BusinessCalculationService {
	private DataService dataService;
	public BusinessCalculationService(DataService dataService) {
		super();
		this.dataService = dataService;
	}

	public int findMax() {
		return Arrays.stream(dataService.retrieveData()).max().orElse(0);
	}

}
```
---
#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/c1/DataService.java New

```java
package com.in28minutes.learnspringframework.examples.c1;

public interface DataService {
	int[] retrieveData();
}
```
---

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/c1/MongoDbDataService.java New
```java
package com.in28minutes.learnspringframework.examples.c1;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MongoDbDataService implements DataService {
	@Override
	public int[] retrieveData() {
		return new int[] { 11, 22, 33, 44, 55 };
	}
}
```
---

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/c1/MySqlDataService.java New
```java
package com.in28minutes.learnspringframework.examples.c1;

import org.springframework.stereotype.Component;

@Component
public class MySqlDataService implements DataService {
	@Override
	public int[] retrieveData() {
		return new int[] { 1, 2, 3, 4, 5 };
	}
}
```
---
#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/c1/RealWorldSpringContextLauncherApplication.java New

```java
package com.in28minutes.learnspringframework.examples.c1;

import java.util.Arrays;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class RealWorldSpringContextLauncherApplication {
	public static void main(String[] args) {
		try (var context = 
				new AnnotationConfigApplicationContext
					(RealWorldSpringContextLauncherApplication.class)) {
			Arrays.stream(context.getBeanDefinitionNames())
				.forEach(System.out::println);

			System.out.println(
					context.getBean(BusinessCalculationService.class).findMax());
		}
	}
}
```

## Section 04
## Exploring Spring Framework Advanced Features
### Step 03
- Step 01 - Exploring Lazy and Eager Initialization of Spring Framework Beans
- Step 02 - Comparing Lazy Initialization vs Eager Initialization
- Step 03 - Exploring Java Spring Framework Bean Scopes - Prototype and Singleton

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/d1/LazyInitializationLauncherApplication.java New
```java
package com.in28minutes.learnspringframework.examples.d1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
class ClassA {
}

@Component
@Lazy
class ClassB {
	private ClassA classA;
	public ClassB(ClassA classA) {
		//Logic
		System.out.println("Some Initialization logic");
		this.classA = classA;
	}
	
	public void doSomething() {
		System.out.println("Do Something");
	}
}


@Configuration
@ComponentScan
public class LazyInitializationLauncherApplication {
	public static void main(String[] args) {
		try (var context = 
				new AnnotationConfigApplicationContext
					(LazyInitializationLauncherApplication.class)) {
			
			System.out.println("Initialization of context is completed");
			context.getBean(ClassB.class).doSomething();
		}
	}
}
```
---
#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/e1/BeanScopesLauncherApplication.java New
```java
package com.in28minutes.learnspringframework.examples.e1;

import java.util.Arrays;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
class NormalClass {
}

@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class PrototypeClass {
}

@Configuration
@ComponentScan
public class BeanScopesLauncherApplication {
	public static void main(String[] args) {
		try (var context = 
				new AnnotationConfigApplicationContext
					(BeanScopesLauncherApplication.class)) {
			System.out.println(context.getBean(NormalClass.class));
			System.out.println(context.getBean(NormalClass.class));
			System.out.println(context.getBean(NormalClass.class));
			System.out.println(context.getBean(NormalClass.class));
			System.out.println(context.getBean(NormalClass.class));
			System.out.println(context.getBean(NormalClass.class));
			
			System.out.println(context.getBean(PrototypeClass.class));
			System.out.println(context.getBean(PrototypeClass.class));
			System.out.println(context.getBean(PrototypeClass.class));
			System.out.println(context.getBean(PrototypeClass.class));
		}
	}
}
```
---
### Step 07
- Step 04 - Comparing Prototype vs Singleton - Spring Framework Bean Scopes
- Step 05 - Exploring Spring Beans - PostConstruct and PreDestroy
- Step 06 - Evolution of Jakarta EE - Comparing with J2EE and Java EE
- Step 07 - Exploring Jakarta CDI with Spring Framework and Java

#### /learn-spring-framework-02/pom.xml Modified
```
<dependency>
	<groupId>jakarta.inject</groupId>
	<artifactId>jakarta.inject-api</artifactId>
	<version>2.0.1</version>
</dependency>
```
---
#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/f1/PrePostAnnotationsContextLauncherApplication.java New
```java
package com.in28minutes.learnspringframework.examples.f1;

import java.util.Arrays;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
class SomeClass {
	private SomeDependency someDependency;
	public SomeClass(SomeDependency someDependency) {
		super();
		this.someDependency = someDependency;
		System.out.println("All dependencies are ready!");
	}
	
	@PostConstruct
	public void initialize() {
		someDependency.getReady();
	}
	
	@PreDestroy
	public void cleanup() {
		System.out.println("Cleanup");
	}
}

@Component 
class SomeDependency {
	public void getReady() {
		System.out.println("Some logic using SomeDependency");
	}
}

@Configuration
@ComponentScan
public class PrePostAnnotationsContextLauncherApplication {
	public static void main(String[] args) {
		try (var context = 
				new AnnotationConfigApplicationContext
					(PrePostAnnotationsContextLauncherApplication.class)) {
			
			Arrays.stream(context.getBeanDefinitionNames())
				.forEach(System.out::println);
		}
	}
}
```
---
#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/g1/CdiContextLauncherApplication.java New
```java
package com.in28minutes.learnspringframework.examples.g1;

import java.util.Arrays;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import jakarta.inject.Inject;
import jakarta.inject.Named;


//@Component
@Named
class BusinessService {
	private DataService dataService;

	//@Autowired
	@Inject
	public void setDataService(DataService dataService) {
		System.out.println("Setter Injection");
		this.dataService = dataService;
	}

	public DataService getDataService() {
		return dataService;
	}
}

//@Component
@Named
class DataService {
}

@Configuration
@ComponentScan
public class CdiContextLauncherApplication {
	public static void main(String[] args) {
		try (var context = 
				new AnnotationConfigApplicationContext
					(CdiContextLauncherApplication.class)) {
			
			Arrays.stream(context.getBeanDefinitionNames())
				.forEach(System.out::println);
			
			System.out.println(context.getBean(BusinessService.class)
					.getDataService());
		}
	}
}
```
---
### Step 10
- Step 08 - Exploring Java Spring XML Configuration
- Step 09 - Exploring Java Annotations vs XML Configuration for Java Spring Framework
- Step 10 - Exploring Spring Framework Stereotype Annotations - Component and more

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/c1/BusinessCalculationService.java Modified
```
import org.springframework.stereotype.Service;
//@Component
@Service
```
---
#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/c1/MongoDbDataService.java Modified
```
import org.springframework.stereotype.Repository;
//@Component
@Repository
```
---
#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/c1/MySqlDataService.java Modified
```
import org.springframework.stereotype.Repository;
//@Component
@Repository
```
---
#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/d1/LazyInitializationLauncherApplication.java Modified
```
@SuppressWarnings("unused")
private ClassA classA;
```
---
#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/h1/XmlConfigurationContextLauncherApplication.java New
```java
package com.in28minutes.learnspringframework.examples.h1;

import java.util.Arrays;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.in28minutes.learnspringframework.game.GameRunner;

public class XmlConfigurationContextLauncherApplication {
	public static void main(String[] args) {
		try (var context = 
				new ClassPathXmlApplicationContext("contextConfiguration.xml")) {
			Arrays.stream(context.getBeanDefinitionNames())
				.forEach(System.out::println);
			
			System.out.println(context.getBean("name"));
			System.out.println(context.getBean("age"));
			context.getBean(GameRunner.class).run();
		}
	}
}
```
---

#### /learn-spring-framework-02/src/main/resources/contextConfiguration.xml New
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context" xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd"> <!-- bean definitions here -->
	
	<bean id="name" class="java.lang.String">
		<constructor-arg value="Ranga" />
	</bean>

	<bean id="age" class="java.lang.Integer">
		<constructor-arg value="35" />
	</bean>

	<!-- 	<context:component-scan 
		base-package="com.in28minutes.learnspringframework.game"/>
 	-->
 	<bean id="game" class="com.in28minutes.learnspringframework.game.PacmanGame"/>
 	
 	<bean id="gameRunner" 
 		class="com.in28minutes.learnspringframework.game.GameRunner">
 		<constructor-arg ref="game" />
 	</bean>
</beans>
```
---
