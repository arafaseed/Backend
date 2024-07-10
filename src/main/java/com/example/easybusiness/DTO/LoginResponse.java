package com.example.easybusiness.DTO;

import com.example.easybusiness.model.Enums.Role;
import com.example.easybusiness.model.Enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class LoginResponse {

    private  Integer userID;
    private String username;
    private String password;


    private String role;


    private String status;
}
