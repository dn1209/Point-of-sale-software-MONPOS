package com.example.demo.service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Store;
import com.example.demo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreService  {

    @Autowired
    private StoreRepository storeRepository;

    public Store saveStore(String nameStore) {
        Store store = new Store(nameStore);
        return storeRepository.save(store);
    }
    public boolean checkStoreName(String userName){
        return storeRepository.existsByStoreUserName(userName);
    }
    public Store findStoreByUserName(String userName){
        Optional<Store> storeOptional = storeRepository.findStoreByUserName(userName);
        if( storeOptional.isEmpty()){
            throw new UserNotFoundException("Store not found with username: " + userName);
        }
        Store store = storeOptional.get();
        return store;
    }


}
