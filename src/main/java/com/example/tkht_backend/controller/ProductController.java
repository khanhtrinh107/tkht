package com.example.tkht_backend.controller;

import com.example.tkht_backend.entity.MonAn;
import com.example.tkht_backend.repository.MonAnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {
    private final MonAnRepository monAnRepository;
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody MonAn monAn){
        return new ResponseEntity<>(monAnRepository.save(monAn),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editProduct(@RequestBody MonAn monAn , @PathVariable(name = "id") int id){
        MonAn monAn1 = monAnRepository.findById(id).orElse(null);
        if(monAn1 != null){
            monAn1.setDescription(monAn.getDescription());
            monAn1.setName(monAn.getName());
            monAn1.setPrice(monAn.getPrice());
            monAnRepository.save(monAn1);
        }
        return new ResponseEntity<>( monAn1, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable(name = "id") int id){
        return new ResponseEntity<>(monAnRepository.findById(id) , HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getProducts(@RequestParam(name = "keyword") String keyword){
        return new ResponseEntity<>(monAnRepository.searchMonAn(keyword) , HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") int id){
        monAnRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
