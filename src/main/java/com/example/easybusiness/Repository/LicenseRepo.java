package com.example.easybusiness.Repository;

import com.example.easybusiness.model.License;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRepo extends JpaRepository<License,Integer> {
}
