package com.example.easybusiness.Repository;

import com.example.easybusiness.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LoginRepo extends JpaRepository<Login,Long> {

    //Customer function
    @Query(value = "select * from login where username = :username",nativeQuery = true)
    Optional<Login> findByUsername (String username);
}
