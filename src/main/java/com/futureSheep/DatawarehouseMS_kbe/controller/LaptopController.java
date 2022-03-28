package com.futureSheep.DatawarehouseMS_kbe.controller;

import com.futureSheep.DatawarehouseMS_kbe.services.ProductService;
import com.futureSheep.DatawarehouseMS_kbe.model.Laptop;
import lombok.AllArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/laptops")
@AllArgsConstructor
@CommonsLog
public class LaptopController {

    private final ProductService productService;


    @GetMapping
    public Laptop[] getAllLaptops() {
        Laptop[] laptops = productService.collectAllLaptops();
        log.info("[LaptopController] get all Laptops (Length): " + laptops.length);
        return laptops;
    }

    @PostMapping
    public Laptop addLaptop(@RequestBody Laptop newLaptop) {
        Laptop laptop = productService.validateLaptopBeforeSavingIntoDB(newLaptop);
        log.info("[LaptopController] add Laptops: " + newLaptop);
        return laptop;
    }

    @GetMapping("/{id}")
    public Laptop getLaptop(@PathVariable UUID id) {
        log.info("[LaptopController] get Laptop with id: " + id);
        return productService.getSingleLaptop(id);
    }


    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteLaptop(@PathVariable UUID id) {
        productService.deleteLaptop(id);
        log.info("[LaptopController] delete Laptop with id: " + id);
        return ResponseEntity.noContent().build();
    }


}
