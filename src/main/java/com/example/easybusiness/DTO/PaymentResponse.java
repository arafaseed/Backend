package com.example.easybusiness.DTO;

import lombok.Data;

@Data
public class PaymentResponse {
    private int P_id;
    private  String Status;
    private  String Amount;
    private  String Date;
}
