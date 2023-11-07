package com.codera.gamerunner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.codera.gamerunner.ream.ConsoleHub;
import com.codera.gamerunner.ream.RunHandler;

// Make spring Boot to Auto Generate Java Beans
// @Configuration allows creating of Beans thru using @Bean decorator
// @ComponentScan allows Spring to look for Class in specified directory
// - Spring picks the Class having @Component, and it auto creates its Beans & instance

@Configuration
@ComponentScan("com.codera.gamerunner.game")
public class GameMainOutlet {
	// Manual Bean and MarioGame instance
	// @Bean
	// ConsoleHub gameConsole() {
	// 	var game = new MarioGame();
	// 	return game;
	// }

	// Spring can also auto create this bean
	@Bean
	RunHandler runHandler(ConsoleHub gameConsole) {
		var runEngine = new RunHandler(gameConsole);
		return runEngine;
	}

	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(GameMainOutlet.class)) {
			context.getBean(RunHandler.class).run();
		}
	}
}
