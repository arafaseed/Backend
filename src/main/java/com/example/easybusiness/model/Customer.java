package com.example.easybusiness.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long Cust_id;
    private String name;
    private String zan_id;
    private String gender;
    private String phone;
    private String address;

    //create user role
    private Long userId;

    public Customer(Long cust_id) {
        Cust_id = cust_id;
    }


    public Customer() {

    }
}

