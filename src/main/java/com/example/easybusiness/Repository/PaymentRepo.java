package com.example.easybusiness.Repository;

import com.example.easybusiness.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository <Payment,Integer> {
}
