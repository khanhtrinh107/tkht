package com.example.tkht_backend.controller;

import com.example.tkht_backend.entity.*;
import com.example.tkht_backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {
    private final OrderRepository orderRepository;
    @GetMapping
    public ResponseEntity<?> searchOrder(@RequestParam(name = "keyword") String keyword , @RequestParam(name = "slug") String slug){
        String res = "";
        if(slug.equals("accept-order")) res = "Created";
        else res = "Accepted";
        return new ResponseEntity<>(orderRepository.searchOrder(keyword , res) , HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") int id){
        return new ResponseEntity<>(orderRepository.findById(id) , HttpStatus.OK);
    }
    @PostMapping("/accept/{id}")
    public ResponseEntity<?> acceptOrder(@PathVariable(name = "id") int id){
        Order order = orderRepository.findById(id).orElse(null);
        if(order != null){
            order.setStatus("Accepted");
        }
        return new ResponseEntity<>(orderRepository.save(order),HttpStatus.OK);
    }
    @PostMapping("/delivery/{id}")
    public ResponseEntity<?> deliveryOrder(@PathVariable(name = "id") int id){
        Order order = orderRepository.findById(id).orElse(null);
        if(order != null){
            order.setStatus("Delivery");
        }
        return new ResponseEntity<>(orderRepository.save(order),HttpStatus.OK);
    }
}
