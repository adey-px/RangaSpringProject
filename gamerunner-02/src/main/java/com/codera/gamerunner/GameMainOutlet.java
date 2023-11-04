package com.codera.gamerunner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.codera.gamerunner.ream.RunHandler;

//
public class GameMainOutlet {
	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(GameBeanConfig.class)) {
			context.getBean(RunHandler.class).run();
		}
	}
}
