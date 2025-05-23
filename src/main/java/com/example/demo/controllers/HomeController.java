package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // Use @Controller to Handle API Calls
public class HomeController {
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "Arun"); // Correct method name: addAttribute
        return "index"; // Returns the Thymeleaf template name
    }
}