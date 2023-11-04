package com.codera.gamerunner.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configuration File containing Spring Beans
// Note: Record is introduced in JDK 16 to eliminates verbosity in Java Beans
// It auto creates public accessor methods, constructor, equals, hashcode and toString 
// It auto creates getter and setter methods; note username is done without using record

record Name(String firstName, String lastName) {
};

record Age(int realAge) {
}

record Address(String location) {
}

record Student(String name, int age) {
}

// 
@Configuration
public class SampleBeanConfig {
    // Create Beans without using record objects
    @Bean
    public String username() {
        System.out.println("Username: adeysky");
        return "mike";
    }

    @Bean
    public int password() {
        System.out.println("Password: ******");
        return 25;
    }

    // Create Bean using existing beans, note their returned values printed
    @Bean
    public Student collegeStudent() {
        var student = new Student(username(), password());
        System.out.println("New student: " + student);
        return student;
    }

    // Create Beans using record objects which serves same as the constructor
    @Bean
    public Name customerName() {
        var name = new Name("Oreofe", "Stephen");
        System.out.println(name.firstName() + " " + name.lastName());
        return name;
    }

    @Bean
    public Age customerAge() {
        var age = new Age(100);
        System.out.println("Age is " + age.realAge());
        return age;
    }

    // assign custom bean name that can be optionally called
    @Bean(name = "custAddress")
    public Address customerAddress() {
        var address = new Address("20, Progressive Way");
        System.out.println(address.location());
        return address;
    }
}
