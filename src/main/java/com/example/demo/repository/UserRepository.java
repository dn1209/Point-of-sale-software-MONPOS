package com.example.demo.repository;

import com.example.demo.model.Student;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.userName = ?1")
    Optional<User> findUserByUserName(String userName);

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    Optional<User> findUserById(Long id);


    @Query("SELECT u.store.userName FROM User u WHERE u.id = :userId")
    Optional<String> findUserNameByUserId( Long userId);


    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.userName = :userName")
    boolean existsByUserName(String userName);

    @Query("SELECT CASE WHEN COUNT(u.store) > 0 THEN false ELSE true END FROM User u WHERE u.store.userName = :storeUserName")
    boolean existsByStoreUserName(String storeUserName);







}
