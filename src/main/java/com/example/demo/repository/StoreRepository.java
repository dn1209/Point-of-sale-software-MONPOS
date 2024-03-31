package com.example.demo.repository;

import com.example.demo.model.Store;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {
    @Query("SELECT CASE WHEN COUNT(u.store) > 0 THEN true ELSE false END FROM User u WHERE u.store.userName = :storeUserName")
    boolean existsByStoreUserName(String storeUserName);

    @Query("SELECT u FROM Store u WHERE u.userName = ?1")
    Optional<Store> findStoreByUserName(String userName);
}
