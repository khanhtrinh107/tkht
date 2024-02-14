package com.example.tkht_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tblDonHang")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String status;
    private String description;
    @OneToMany(mappedBy = "order" , cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tblKhachHang_id")
    private KhachHang khachHang ;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tblQuanLy_id")
    private QuanLy quanLy;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tblShipper_id")
    private Shipper shipper;
}
