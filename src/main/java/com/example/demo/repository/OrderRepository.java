package com.example.demo.repository;

import com.example.demo.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {
    @Query("SELECT o FROM Orders o WHERE o.store.id = ?1 AND o.sellDate BETWEEN ?2 AND ?3")
    List<Orders> findOrdersBetweenDates(Long sId, LocalDate startDate, LocalDate endDate);
    @Query(value = "SELECT COALESCE(MAX(id), 0) FROM Orders", nativeQuery = true)
    Long getLastOrdersId();
}
