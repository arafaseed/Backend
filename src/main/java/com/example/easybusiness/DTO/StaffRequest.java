package com.example.easybusiness.DTO;

import lombok.Data;

@Data
public class StaffRequest {
    private String name;
    private String gender;
    private String address;
    private String phone;
    private Long userId;


}
