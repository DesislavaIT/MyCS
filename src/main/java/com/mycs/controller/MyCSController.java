package com.mycs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/score")
public class MyCSController {

    @GetMapping("getHello")
    public ResponseEntity<String> getHelloEndpoint() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

    @PostMapping("postSomething")
    public ResponseEntity<String> postStringEndpoint() {
        return new ResponseEntity("Something...........", HttpStatus.CREATED);
    }


}
