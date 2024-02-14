package com.example.tkht_backend;

import com.example.tkht_backend.entity.KhachHang;
import com.example.tkht_backend.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TkhtBackendApplication {
    public static void main(String[] args) {


        SpringApplication.run(TkhtBackendApplication.class, args);
    }

}
