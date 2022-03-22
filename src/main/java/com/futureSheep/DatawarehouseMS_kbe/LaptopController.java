package com.futureSheep.DatawarehouseMS_kbe;

import com.futureSheep.DatawarehouseMS_kbe.model.Laptop;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/laptops")
public class LaptopController {

    //private final LaptopService laptopService;

/*    @GetMapping
    public List<Laptop> fetchAllLaptops() {
        return laptopService.getAllLaptops();
    }*/


}
