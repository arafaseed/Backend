package com.example.easybusiness.DTO;

import com.example.easybusiness.model.Customer;
import lombok.Data;

@Data
public class CustomerResponse {
    private int Cus_id;
    private String name;
    private String zan_id;
    private String gender;
    private String phone;
    private String address;

    private Long userId;


    public CustomerResponse(Customer customer) {
    }
}
