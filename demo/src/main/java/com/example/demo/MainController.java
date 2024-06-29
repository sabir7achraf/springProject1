package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.processing.Generated;

@Controller

public class MainController {
    @GetMapping("")
    public String showHomePage() {
        return "Home Page";
    }
}
