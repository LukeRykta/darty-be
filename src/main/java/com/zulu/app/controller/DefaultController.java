package com.zulu.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    private static final Logger log = LoggerFactory.getLogger(DefaultController.class);

    @GetMapping("/hello")
    public String hello() {
        log.info("first log test");
        return "Hello, Spring Boot!";
    }
}
