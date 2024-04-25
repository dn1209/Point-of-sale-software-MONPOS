package com.example.demo.service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Manufacture;
import com.example.demo.model.Store;
import com.example.demo.model.User;
import com.example.demo.model.payload.Response.manufacture.ManufactureMapped;
import com.example.demo.repository.ManufactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ManufactureService {
    @Autowired
    ManufactureRepository manufactureRepository;
    @Autowired
    StoreService storeService;
    @Autowired
    UserServices userServices;
    public Manufacture saveWithService(String name, Long id,Long userId){
        Manufacture gr = new Manufacture();
        gr.setPrdManufName(name);
        Store store = storeService.findStoreById(id);
        User user = userServices.getUserById(userId);
        gr.setUser(user);
        gr.setStore(store);
        return manufactureRepository.save(gr);
    }
    public Manufacture findById(Long id){
        Optional<Manufacture> gr = manufactureRepository.findByManuId(id);
        if( gr.isEmpty()){
            throw new UserNotFoundException("Store not found with username: " + id);
        }
        Manufacture grD = gr.get();
        return grD;
    }
    public List<ManufactureMapped> getManufacture(Long storeId){
        List<Manufacture> manufactureList = manufactureRepository.getManufactureByByStoreId(storeId);
        List<ManufactureMapped> manufactureMappedList = manufactureList.stream().map(
                manufacture -> new ManufactureMapped(
                        String.valueOf(manufacture.getId()),
                        manufacture.getPrdManufName(),

                        manufacture.getCreated(),
                        String.valueOf(manufacture.getUser().getId()),
                        String.valueOf(manufacture.getUser().getId()),
                        String.valueOf(manufacture.getStore().getId())
                )).collect(Collectors.toList());
        return manufactureMappedList;
    }
    public boolean checkName(String name){
        return manufactureRepository.existsByName(name);
    }
    public boolean checkId(Long id){
        return manufactureRepository.existsById(id);
    }
    public void deleteManufacture(Long id){ manufactureRepository.updateGroupProductById(id); }
}
