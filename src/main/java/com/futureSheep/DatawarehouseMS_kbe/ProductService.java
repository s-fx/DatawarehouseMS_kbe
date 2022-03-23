package com.futureSheep.DatawarehouseMS_kbe;

import com.futureSheep.DatawarehouseMS_kbe.controller.LaptopNotFoundException;
import com.futureSheep.DatawarehouseMS_kbe.model.Laptop;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;


import javax.websocket.server.ServerEndpoint;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final LaptopRepository repository;

    private LaptopValidationService validationService;


    public Laptop[] collectAllLaptops() {
/*        List<Laptop> laptops = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());
        for (Laptop laptop : laptops) {
            Laptop lap = laptop;
        }*/
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
        if(repository.findById(id).isEmpty()) {
            throw new LaptopNotFoundException(id);
        }
        repository.deleteById(id);
    }


    public BigDecimal getPriceOfLaptop(UUID id) {
        Laptop laptop = repository.findById(id).orElseThrow(() -> new LaptopNotFoundException(id));
        return laptop.getPrice();
    }


}

