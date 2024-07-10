package com.example.easybusiness.Repository;

import com.example.easybusiness.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepo extends JpaRepository <Staff,Long> {
}
