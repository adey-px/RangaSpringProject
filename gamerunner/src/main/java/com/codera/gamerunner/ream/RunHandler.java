package com.codera.gamerunner.ream;

// Create Game instance that Calls all the Game methods
public class RunHandler {
    ConsoleHub game;

    // constructore
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
