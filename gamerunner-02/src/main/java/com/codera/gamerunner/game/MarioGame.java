package com.codera.gamerunner.game;

import com.codera.gamerunner.ream.ConsoleHub;

public class MarioGame implements ConsoleHub {
    public void upsky() {
        System.out.println("Mario Up");
    }
    
    public void down() {
        System.out.println("Mario Down");
    }
    
    public void left() {
        System.out.println("Mario Back");
    }

    public void right() {
        System.out.println("Mario Next");
    } 
}
