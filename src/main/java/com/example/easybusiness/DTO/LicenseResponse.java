package com.example.easybusiness.DTO;

import lombok.Data;

@Data
public class LicenseResponse {
    private int lic_id;
    private String name;
    private String date;
    private String location;
    private String address;
    private String b_Type;
    private String region;
}
