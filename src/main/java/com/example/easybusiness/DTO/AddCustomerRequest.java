package com.example.easybusiness.DTO;



import lombok.Data;

@Data
public class AddCustomerRequest {
    private LoginRequest loginRequest;
    private CustomerRequest customerRequest;
}
