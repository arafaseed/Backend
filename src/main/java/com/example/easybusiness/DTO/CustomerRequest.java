package com.example.easybusiness.DTO;

import lombok.Data;

@Data
public class CustomerRequest {
    private String name;
    private String zan_id;
    private String gender;
    private String phone;
    private Long userId;
    private String address;
}
