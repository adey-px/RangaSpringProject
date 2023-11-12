package com.codera.gamerunner.ream;

// Create Game instance that Calls all the Game methods
// Note: add @Component on top of the Class
// - if you want Spring to auto create its Bean inside GameMainOutlet.java

public class RunHandler {
    ConsoleHub game;

    // constructor
    public RunHandler(ConsoleHub game) {
        this.game = game;
    }

    public void run() {
        System.out.println("Running Game: " + game);
        game.upsky();
        game.down();
        game.left();
        game.right();
    }
}
