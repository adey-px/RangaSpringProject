package com.codera.gamerunner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codera.gamerunner.ream.ConsoleHub;
import com.codera.gamerunner.game.MarioGame;
import com.codera.gamerunner.ream.RunHandler;

// Create java Beans manually, Compare gamerunner-02
@Configuration
public class GameBeanConfig {
	@Bean
	public ConsoleHub gameConsole() {
		var game = new MarioGame();
		return game;
	}

    @Bean
	public RunHandler runHandler(ConsoleHub gameConsole) {
		var runEngine = new RunHandler(gameConsole);
		return runEngine;
	}
}
