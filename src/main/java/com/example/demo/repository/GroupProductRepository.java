package com.example.demo.repository;

import com.example.demo.model.GroupProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupProductRepository extends JpaRepository<GroupProduct,Long> {

    @Query("SELECT u FROM GroupProduct u WHERE u.store.id = ?1")
    List<GroupProduct> getGroupProductByStoreId(Long storeId);

    @Query("SELECT u from  GroupProduct u where u.id = ?1")
    Optional<GroupProduct> findByGroupProductId(Long id);
    @Query("SELECT CASE WHEN EXISTS (SELECT 1 FROM GroupProduct u WHERE u.prdGroupName = :userName) THEN true ELSE false END")
    boolean existsByName(String userName);
    //true neu da ton tai va false neu chua ton tai

    @Query("SELECT CASE WHEN EXISTS (SELECT 1 FROM GroupProduct u WHERE u.id = :id) THEN true ELSE false END")
    boolean existsById(Long id);
    @Modifying
    @Query("UPDATE GroupProduct u set u.status = 1 WHERE u.id = :id")
    void updateGroupProductById( Long id);
}
