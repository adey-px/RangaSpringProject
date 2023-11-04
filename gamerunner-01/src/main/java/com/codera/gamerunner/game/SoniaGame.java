package com.codera.gamerunner.game;

import com.codera.gamerunner.ream.ConsoleHub;

public class SoniaGame implements ConsoleHub {
    public void upsky() {
        System.out.println("Sonia up");
    }

    public void down() {
        System.out.println("Sonia down");
    }

    public void left() {
        System.out.println("Sonia back");
    }

    public void right() {
        System.out.println("Sonia next");
    }
}
