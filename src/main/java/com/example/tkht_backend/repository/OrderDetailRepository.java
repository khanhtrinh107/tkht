package com.example.tkht_backend.repository;

import com.example.tkht_backend.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail , Integer> {
}
