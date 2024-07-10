package com.example.easybusiness.DTO;

import com.example.easybusiness.model.Customer;
import com.example.easybusiness.model.Staff;
import lombok.Data;

@Data
public class StaffResponse {
    private Long S_id;
    private String name;
    private String gender;
    private String address;
    private String phone;

    private Long userId;


    public StaffResponse(Staff staff) {
    }
}
