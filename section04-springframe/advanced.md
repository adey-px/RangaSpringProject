## Spring Framework
## Section 04: Exploring Spring Framework Advanced Features

Step 01 - Eager Initialization & Lazy Initialization of Spring Beans
```java
package com.in28minutes.learnspringframework.examples.d1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

// Eager Initialization is default, it loads beans created by the constructor first
// @Lazy value delays execution of constructor till after Application Class context
@Component
class ClassA {}

@Component
@Lazy(value='true')
class ClassB {
	private ClassA classA;

	public ClassB(ClassA classA) {
		System.out.println("Default execution of constructor first");
		this.classA = classA;
	}
	
	public void doSomething() {
		System.out.println("Do Something here after Bean is created");
	}
}

@ComponentScan
@Configuration
public class LazyInitializationLauncherApplication {
	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(LazyInitializationLauncherApplication.class)) {
			System.out.println("Execute this context first due to @Lazy annotaion in ClassB constructor");
			context.getBean(ClassB.class).doSomething();
		}
	}
}
```

Step 03 - Type of Speing Bean Scopes (Prototype & Singleton)
```java
package com.in28minutes.learnspringframework.examples.e1;
import java.util.Arrays;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// Each instance of RegularClass created  has same Hashcode, means same Bean is created
// @Scope value helps to create diff instances with diff Hashcode, means diff Beans
@Component
class RegularClass {}

@Component
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class PrototypeClass {}

@Configuration
@ComponentScan
public class BeanScopesLauncherApplication {
	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(BeanScopesLauncherApplication.class)) {
			System.out.println(context.getBean(RegularClass.class));
			System.out.println(context.getBean(RegularClass.class));
			System.out.println(context.getBean(RegularClass.class));
			
			System.out.println(context.getBean(PrototypeClass.class));
			System.out.println(context.getBean(PrototypeClass.class));
			System.out.println(context.getBean(PrototypeClass.class));
		}
	}
}
```

Step 05 - Spring Framework Beans (PostConstruct & PreDestroy)
```java
package com.in28minutes.learnspringframework.examples.f1;
import java.util.Arrays;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

// @PostConstruct is used for inital process like fetching data from db
// 	@PreDestroy is used for releasing any resources from the context
@Component 
class SomeDependency {
	public void getReady() {
		System.out.println("Some logic using SomeDependency");
	}
}

@Component
class SomeBusinessClass {
	private SomeDependency someDependency;

	public SomeBusinessClass(SomeDependency someDependency) {
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

@Configuration
@ComponentScan
public class PrePostAnnotationsContextLauncherApplication {
	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(PrePostAnnotationsContextLauncherApplication.class)) {
			Arrays.stream(context.getBeanDefinitionNames())
				.forEach(System.out::println);
		}
	}
}
```

Step 07 - Jakarta Context & Dependency Injection (CDI)
```java
package com.in28minutes.learnspringframework.examples.g1;
import java.util.Arrays;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
class BusinessService {
	private DataService dataService;

	@Inject
	public void setDataService(DataService dataService) {
		System.out.println("Setter Injection");
		this.dataService = dataService;
	}

	public DataService getDataService() {
		return dataService;
	}
}

@Named
class DataService {}

@Configuration
@ComponentScan
public class CdiContextLauncherApplication {
	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(CdiContextLauncherApplication.class)) {
			Arrays.stream(context.getBeanDefinitionNames())
				.forEach(System.out::println);
			
			System.out.println(context.getBean(BusinessService.class).getDataService());
		}
	}
}
```

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
		try (var context = new ClassPathXmlApplicationContext("contextConfiguration.xml")) {
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
    xmlns:context="http://www.springframework.org/schema/context" xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
			http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd"
	> 
	<!-- bean definitions here -->
	
	<bean id="name" class="java.lang.String">
		<constructor-arg value="Ranga" />
	</bean>

	<bean id="age" class="java.lang.Integer">
		<constructor-arg value="35" />
	</bean>

	<!-- <context:component-scan 
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
