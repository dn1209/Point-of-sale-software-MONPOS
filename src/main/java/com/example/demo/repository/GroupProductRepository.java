package com.example.demo.repository;

import com.example.demo.model.GroupProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupProductRepository extends JpaRepository<GroupProduct,Long> {

    @Query("SELECT u FROM GroupProduct u WHERE u.store.id = ?1")
    List<GroupProduct> getGroupProductByStoreId(Long storeId);

}
