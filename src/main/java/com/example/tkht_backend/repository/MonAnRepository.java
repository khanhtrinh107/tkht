package com.example.tkht_backend.repository;

import com.example.tkht_backend.entity.MonAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MonAnRepository extends JpaRepository<MonAn, Integer> {
    @Query("SELECT m FROM MonAn m WHERE m.name LIKE %?1% OR m.description LIKE %?1%")
    List<MonAn> searchMonAn(String keyword);
}
