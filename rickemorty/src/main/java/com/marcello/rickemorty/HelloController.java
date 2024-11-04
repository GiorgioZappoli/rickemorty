package com.marcello.rickemorty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "hello World";
    }
    
}
