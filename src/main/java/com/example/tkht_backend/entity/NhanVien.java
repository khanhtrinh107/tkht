package com.example.tkht_backend.entity;



import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class NhanVien extends User{
    private String vaiTro;
}
