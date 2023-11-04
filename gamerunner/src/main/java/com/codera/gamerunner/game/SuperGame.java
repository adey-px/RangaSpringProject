package com.codera.gamerunner.game;

import com.codera.gamerunner.ream.ConsoleHub;

public class SuperGame implements ConsoleHub {
    public void upsky() {
        System.out.println("Super up");
    }

    public void down() {
        System.out.println("Super down");
    }

    public void left() {
        System.out.println("Super back");
    }

    public void right() {
        System.out.println("Super next");
    }
}
