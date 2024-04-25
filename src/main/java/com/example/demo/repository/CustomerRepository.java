package com.example.demo.repository;

import com.example.demo.model.Customer;
import com.example.demo.model.GroupProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("SELECT u FROM Customer u WHERE u.store.id = ?1 AND LOWER(u.customerName) LIKE %?2%")
    List<Customer> getCustomerByStoreIdAndKeyquord(Long storeId,String keyword);
    @Query(value = "SELECT COALESCE(MAX(id), 0) FROM Customer", nativeQuery = true)
    Long getLastCustomerId();
}
