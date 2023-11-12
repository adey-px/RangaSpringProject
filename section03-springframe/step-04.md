## Spring Framework
## Section 03: Using Spring Framework to Create & Manage Java Objects

Step 04: Three Types of Dependency Injection
- `Field Injection` in which no setter or constructor is being used to set or inject dependencies
- `Setter Injection` in which dependencies are set by using setter methods on the Bean
- `Constructor Injection` in which dependencies are set by creating Bean using its constructor

```java
package com.in28minutes.learnspringframework.examples.a1;
import java.util.Arrays;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// apply ComponentScan on current file, arrays.stream gets all Beans that are part of the context
// @ComponentScan allows Spring to look for Class in specified directory
// Spring picks Class having @Component, it auto creates Beans & instance for each lass
@Configuration
@ComponentScan
public class DependencyInjectionApplication {
	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(DependencyInjectionApplication.class)) {
			Arrays.stream(context.getBeanDefinitionNames())
				.forEach(System.out::println);
			System.out.println(context.getBean(SampleBusinessLogicDemo.class));
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

// Inject Both Dependencies into BusinessLogic Class, taken to DependencyInjectionApplication above
// @Component allows Spring to auto creates their Beans through being scanned by @ComponentScan above
// @Autowired to be added on top of each
@Component
class Dependency1 {}

@Component
class Dependency2 {}

@Component
class SampleBusinessLogicDemo {
	Dependency1 dependency1;
	Dependency2 dependency2;
	
	// @Autowire - on each to use Field Injection
	// Dependency1 dependency1;
	// Dependency2 dependency2;

	// @Autowire - on each to use Setter Injection
	// public void setDependency1(Dependency1 dependency1) {
	// 	System.out.println("Injection for dependency1");
	//	this.dependency1 = dependency1;
	// }

	// public void setDependency2(Dependency2 dependency2) {
	//	System.out.println("Injection for dependency2");
	//	this.dependency2 = dependency2;
	// }

	// Constructor Injection - @Autowire not required
	public SampleBusinessLogicDemo(Dependency1 dependency1, Dependency2 dependency2) {
		super();
		System.out.println("Injection for dependencies 1 & 2");
		this.dependency1 = dependency1;
		this.dependency2 = dependency2;
	}

	public String toString() {
		return "Injection is working using " + dependency1 + " and " + dependency2;
	}
}
```