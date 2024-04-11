package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT u FROM Product u WHERE u.prdStatus = ?1 AND LOWER(u.prdName) LIKE %?2%")
    List<Product> findProductsByStatusAndKeyword(int status, String keyword);

}
