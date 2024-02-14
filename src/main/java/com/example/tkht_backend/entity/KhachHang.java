package com.example.tkht_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "btlKhachHang")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhachHang extends User{
    private String maKhachHang;
    @OneToMany(mappedBy = "khachHang" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> Orders;
}
