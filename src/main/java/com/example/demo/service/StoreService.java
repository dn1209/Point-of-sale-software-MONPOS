package com.example.demo.service;

import com.example.demo.model.Store;
import com.example.demo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class StoreService  {

    @Autowired
    private StoreRepository storeRepository;

    public Store saveStore(Store store) {
        return storeRepository.save(store);
    }
}
