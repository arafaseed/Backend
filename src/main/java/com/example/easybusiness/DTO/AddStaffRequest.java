package com.example.easybusiness.DTO;

import lombok.Data;

@Data
public class AddStaffRequest {
    private LoginRequest loginRequest;
    private StaffRequest staffRequest;
}
