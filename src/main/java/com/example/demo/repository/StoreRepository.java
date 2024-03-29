package com.example.demo.repository;

import com.example.demo.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {
    @Query("SELECT CASE WHEN COUNT(u.store) > 0 THEN false ELSE true END FROM User u WHERE u.store.userName = :storeUserName")
    boolean existsByStoreUserName(String storeUserName);

}
