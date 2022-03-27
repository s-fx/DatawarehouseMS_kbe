package com.futureSheep.DatawarehouseMS_kbe.controller;

import com.futureSheep.DatawarehouseMS_kbe.services.ProductService;
import com.futureSheep.DatawarehouseMS_kbe.model.Laptop;
import lombok.AllArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
@CommonsLog
public class LaptopController {

    private final ProductService productService;


    @GetMapping("/laptops")
    public Laptop[] getAllLaptops() {
        Laptop[] laptops = productService.collectAllLaptops();
        return laptops;
    }

    @PostMapping("/laptops")
    public Laptop addLaptop(@RequestBody Laptop newLaptop) {
        Laptop laptop = productService.validateLaptopBeforeSavingIntoDB(newLaptop);
        return laptop;
    }

    @GetMapping("/laptops/{id}")
    public Laptop getLaptop(@PathVariable UUID id) {
        return productService.getSingleLaptop(id);
    }


    @DeleteMapping("/laptops/{id}")
    ResponseEntity<?> deleteLaptop(@PathVariable UUID id) {
        productService.deleteLaptop(id);
        return ResponseEntity.noContent().build();
    }


}
