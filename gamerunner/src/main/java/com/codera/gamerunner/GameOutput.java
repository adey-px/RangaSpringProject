package com.codera.gamerunner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.codera.gamerunner.ream.RunHandler;
// import com.codera.gamerunner.game.MarioGame;
// import com.codera.gamerunner.game.SoniaGame;
// import com.codera.gamerunner.game.SuperGame;

//
public class GameOutput {
	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(GameConfig.class)) {
			context.getBean(RunHandler.class).run();
		}
	}
}
