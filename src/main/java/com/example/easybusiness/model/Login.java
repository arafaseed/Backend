package com.example.easybusiness.model;


import com.example.easybusiness.model.Enums.Role;
import com.example.easybusiness.model.Enums.Status;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long userID;
    private String username;
    private String password;

    //
    @Enumerated (EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Login(Long userID){
        this.userID = userID;
    }

    public Login() {
    }
}
