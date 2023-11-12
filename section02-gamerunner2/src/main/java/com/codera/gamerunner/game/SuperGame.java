package com.codera.gamerunner.game;

import org.springframework.stereotype.Component;

import com.codera.gamerunner.ream.ConsoleHub;

// @Component enables Spring to auto create the Class instance & its Bean
// Refer GameMainOutlet to see how it works

@Component
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
