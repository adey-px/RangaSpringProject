package com.practice.section05springboot;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackendController {
    @GetMapping("/certifications")
    public List<Certfication> getALLcourses() {
        return Arrays.asList(
                new Certfication(1, "Amazon Cloud", "Amazon"),
                new Certfication(2, "Google Cloud", "Google"),
                new Certfication(3, "Micros Azure", "Micros"));
    }
}
