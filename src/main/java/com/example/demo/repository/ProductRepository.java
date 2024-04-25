package com.example.demo.repository;

import com.example.demo.model.GroupProduct;
import com.example.demo.model.Manufacture;
import com.example.demo.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT u FROM Product u WHERE u.prdStatus = ?1 AND u.store.id = ?2 AND LOWER(u.prdName) LIKE %?3%")
    List<Product> findProductsByStatusAndKeyword(int status, Long id, String keyword);

    @Query("SELECT u FROM Product u WHERE u.prdStatus = ?1 AND u.store.id = ?2 AND LOWER(u.prdName) LIKE %?3% AND u.groupProduct.id = ?4")
    List<Product> findProductsByStatusAndKeywordAndGroupProduct(int status, Long sId, String keyword, Long id);

    @Query("SELECT u FROM Product u WHERE u.prdStatus = ?1 AND u.store.id = ?2 AND LOWER(u.prdName) LIKE %?3% AND u.manufacture.id = ?4")
    List<Product> findProductsByStatusAndKeywordAndManu(int status, Long sId, String keyword, Long id);

    @Query("SELECT u FROM Product u WHERE u.prdStatus = ?1 AND u.store.id = ?2 AND LOWER(u.prdName) LIKE %?3% AND u.manufacture.id = ?4 AND u.groupProduct.id= ?5")
    List<Product> findProductsByStatusAndKeywordAndManuAndGroupProduct(int status, Long sId, String keyword, Long mId, Long gId);

    @Query(value = "SELECT COALESCE(MAX(id), 0) FROM Product", nativeQuery = true)
    Long getLastProductId();

    @Query("SELECT u from Product u WHERE u.id = ?1 and  u.store.id = ?2")
    Optional<Product> findByIdAndStore(Long id, Long sId);

    @Query("SELECT u from Product u WHERE u.prdName = ?1 and  u.store.id = ?2")
    Optional<Product> findByIdAndName(String name, Long sId);

    @Query("SELECT CASE WHEN EXISTS (SELECT 1 FROM Product u WHERE u.id = :id) THEN true ELSE false END")
    boolean existsById(Long id);

    //true neu ton tai va false neu khong ton tai
    @Query("SELECT CASE WHEN EXISTS (SELECT 1 FROM Product u WHERE u.prdName = :name) THEN true ELSE false END")
    boolean existsByName(String name);

    @Modifying
    @Query("UPDATE Product u set u.prdName = ?1,u.prdSls = ?2,u.prdOriginalPrice = ?3,u.prdSellPrice= ?4 , u.groupProduct = ?5,u.manufacture = ?6,u.prdStatus = ?7 WHERE u.id = ?8 ")
    void updateProductById(String prd_name, Float prd_sls, Float prdOriginalPrice,
                           Float prdSellPrice, GroupProduct groupProduct, Manufacture manufacture, int prdStatus, Long id);

    @Modifying
    @Query("UPDATE Product u set u.prdStatus = 0 WHERE u.id = ?1 ")
    void updateProduct(Long id);
    @Modifying
    @Query("UPDATE Product u set u.prdStatus = 1 WHERE u.id = ?1 ")
    void deleteProductWithStatus(Long id);
    @Modifying
    @Query("UPDATE Product u set u.prdSls = u.prdSls - ?1 WHERE u.id = ?2 ")
    void updateProductWithPRDSLS(Float sl,Long id);
    @Query("SELECT CASE WHEN (SELECT u.prdSls FROM Product u WHERE u.id = :id) > :quantity THEN false ELSE true END")
    boolean checkProductWithIdAndQuantity(Long id, int quantity);





}

