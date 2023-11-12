## Spring Framework
## Section 03: Using Spring Framework to Create & Manage Java Objects

Step 08 - Example & Solution for a Real World Java Spring Framework 
- create required classes and interfaces as needed
- use constructor injection to inject dependencies
- make mongodb service as the primary service
- create Spring context, use annotations where possible
- retrieve Business logic from Spring context, run findMax() method

```java
package com.in28minutes.learnspringframework.examples.c1;

// Data Connection service
public interface DataService {
	int[] retrieveData();
}
```

```java
package com.in28minutes.learnspringframework.examples.c1;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// Mongodb Database service
@Component
@Primary
public class MongoDbDataService implements DataService {
	@Override
	public int[] retrieveData() {
		return new int[] { 11, 22, 33, 44, 55 };
	}
}
```

```java
package com.in28minutes.learnspringframework.examples.c1;
import org.springframework.stereotype.Component;

// MySQL Database service
@Component
public class MySqlDataService implements DataService {
	@Override
	public int[] retrieveData() {
		return new int[] { 1, 2, 3, 4, 5 };
	}
}
```

```java
package com.in28minutes.learnspringframework.examples.c1;
import java.util.Arrays;
import org.springframework.stereotype.Component;

// Business calculation service
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

```java
package com.in28minutes.learnspringframework.examples.c1;
import java.util.Arrays;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Application Main class
@Configuration
@ComponentScan
public class RealWorldSpringContextLauncherApplication {
	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(RealWorldSpringContextLauncherApplication.class)) {
			Arrays.stream(context.getBeanDefinitionNames())
				.forEach(System.out::println);

			System.out.println(context.getBean(BusinessCalculationService.class).findMax());
		}
	}
}
```
