package com.futureSheep.DatawarehouseMS_kbe.services;

import com.futureSheep.DatawarehouseMS_kbe.exceptions.LaptopNotFoundException;
import com.futureSheep.DatawarehouseMS_kbe.model.Laptop;
import com.futureSheep.DatawarehouseMS_kbe.repositories.LaptopRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

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
        return laptop;
    }

    public void saveLaptopIntoDB(Laptop laptop) {
        repository.save(laptop);
    }


    public Laptop getSingleLaptop(UUID id) {
        Laptop laptop = repository.findById(id).orElseThrow(() -> new LaptopNotFoundException(id));
        return laptop;
    }


    public void deleteLaptop(UUID id) {
        if (repository.findById(id).isEmpty()) {
            throw new LaptopNotFoundException(id);
        }
        repository.deleteById(id);
    }


}

