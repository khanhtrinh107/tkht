package com.example.tkht_backend.repository;

import com.example.tkht_backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o WHERE o.status = ?2 AND (o.khachHang.fullname LIKE %?1%  OR o.description LIKE %?1%)")
    List<Order> searchOrder(String keyword , String slug);
}

