package com.example.tkht_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {
    @GetMapping("/test")
    public ResponseEntity<?> test(){
        return new ResponseEntity<>("khanh test" , HttpStatus.OK);
    }
    @GetMapping("/test2")
    public ResponseEntity<?> test2(){
        return new ResponseEntity<>("khanh test cicd pipeline ok ko" , HttpStatus.OK);
    }
}
