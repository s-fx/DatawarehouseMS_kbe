package com.futureSheep.DatawarehouseMS_kbe.services;

import com.futureSheep.DatawarehouseMS_kbe.exceptions.LaptopNotFoundException;
import com.futureSheep.DatawarehouseMS_kbe.model.Laptop;
import com.futureSheep.DatawarehouseMS_kbe.repositories.LaptopRepository;
import lombok.AllArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@CommonsLog
@Service
@AllArgsConstructor
public class ProductService {

    private final LaptopRepository repository;

    private LaptopValidationService validationService;


    public Laptop[] collectAllLaptops() {
        List<Laptop> laptopList = repository.findAll();
        Laptop[] laptopArray = laptopList.toArray(new Laptop[laptopList.size()]);
        return laptopArray;
    }

    public Laptop validateLaptopBeforeSavingIntoDB(Laptop laptop) {
        String res = validationService.addLaptop(laptop);
        log.info("[ProductService] validate Laptop result: " + res);
        return laptop;
    }

    public void saveLaptopIntoDB(Laptop laptop) {
        repository.save(laptop);
        log.info("[ProductService] save Laptop into DB: " + laptop);
    }


    public Laptop getSingleLaptop(UUID id) {
        Laptop laptop = repository.findById(id).orElseThrow(() -> new LaptopNotFoundException(id));
        log.info("[ProductService] get Laptop with id: " + id);
        return laptop;
    }


    public void deleteLaptop(UUID id) {
        if (repository.findById(id).isEmpty()) {
            log.warn("[ProductService] Laptop with id: " + id + " not found");
            throw new LaptopNotFoundException(id);
        }
        repository.deleteById(id);
        log.info("[ProductService] delete Laptop with id: " + id);
    }

}

