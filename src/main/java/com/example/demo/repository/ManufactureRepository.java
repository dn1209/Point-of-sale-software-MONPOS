package com.example.demo.repository;

import com.example.demo.model.GroupProduct;
import com.example.demo.model.Manufacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManufactureRepository extends JpaRepository<Manufacture,Long> {
    @Query("SELECT u FROM Manufacture u WHERE u.store.id = ?1")
    List<Manufacture> getManufactureByByStoreId(Long storeId);
    @Query("SELECT u from  Manufacture u where u.id = ?1")
    Optional<Manufacture> findByManuId(Long id);
    @Query("SELECT CASE WHEN EXISTS (SELECT 1 FROM Manufacture u WHERE u.prdManufName = :userName) THEN true ELSE false END")
    boolean existsByName(String userName);
    //true neu da ton tai va false neu chua ton tai

    @Query("SELECT CASE WHEN EXISTS (SELECT 1 FROM Manufacture u WHERE u.id = :id) THEN true ELSE false END")
    boolean existsById(Long id);
    @Modifying
    @Query("UPDATE Manufacture u set u.status = 1 WHERE u.id = :id")
    void updateGroupProductById( Long id);

}
