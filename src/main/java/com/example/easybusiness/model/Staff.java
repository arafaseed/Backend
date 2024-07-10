package com.example.easybusiness.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long S_id;
    private String name;
    private String gender;
    private String address;
    private String phone;

    //    //create user role
    private Long userId;
   public Staff(Long s_id){
       S_id = s_id;
   }


    public Staff() {

    }
}
