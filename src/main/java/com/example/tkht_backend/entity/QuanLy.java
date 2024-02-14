package com.example.tkht_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tblQuanLy")
public class QuanLy extends NhanVien{
    @OneToMany(mappedBy = "quanLy" , cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> Orders;
}
