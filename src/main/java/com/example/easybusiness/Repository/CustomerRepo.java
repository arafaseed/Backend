package com.example.easybusiness.Repository;

import com.example.easybusiness.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {
    @Query(value = "select * from customer c where c.cus_id =?1  ", nativeQuery = true)
    List<Customer> findByCustId(Long custId);

    Optional<Customer> findByUserId(Long userId);
}
