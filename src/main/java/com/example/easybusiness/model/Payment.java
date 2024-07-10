package com.example.easybusiness.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Setter
@Getter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int P_id;
    private  String Status;
    private  String Amount;
    private  String Date;

    @ManyToOne
    @JoinColumn(name = "Lic_id")
    private License license;

    @ManyToOne
    @JoinColumn(name = "S_id")
    private Staff staff;

}
