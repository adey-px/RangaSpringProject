package com.codera.gamerunner.game;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.codera.gamerunner.ream.ConsoleHub;

// @Component enables Spring to auto create the Class instance & its Bean
// Refer GameMainOutlet to see how it works
// @Primary allows SoniaGame to be chosen ahead of SuperGame in the consoleHub
// - both games have @Component which allows Spring to auto create their Beans and instances

@Component
@Primary
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
