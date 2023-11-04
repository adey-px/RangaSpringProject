package com.codera.gamerunner.bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// Create Context using GameBeanConfig, Note diff ways of calling the context
// Call by Bean name, Method name or by Classname.class, Bean Type
// Use try () {} - Try with Resources to rid close error

public class SampleBeanOutput {
	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(SampleBeanConfig.class)) {
			context.getBean("username");
			context.getBean("password");
			context.getBean(Student.class);
			context.getBean("customerName");
			context.getBean("customerAge");
			context.getBean(Address.class);
		}
	}
}
