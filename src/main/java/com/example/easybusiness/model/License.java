package com.example.easybusiness.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class License {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lic_id;

    private String name;
    private String date;
    private String location;
    private String address;
    private String b_Type;
    private String region;

    @ManyToOne
    @JoinColumn (name = "Cus_id")
    private Customer customer;


}