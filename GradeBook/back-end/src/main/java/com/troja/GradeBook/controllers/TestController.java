package com.troja.GradeBook.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController{

    @GetMapping("/secured")
    public String securedEndpoint() {
        return "To jest zabezpieczona zawartość";
    }
}
