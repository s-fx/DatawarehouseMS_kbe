
package com.futureSheep.DatawarehouseMS_kbe.utils;

import com.futureSheep.DatawarehouseMS_kbe.repositories.LaptopRepository;
import com.futureSheep.DatawarehouseMS_kbe.services.CSVService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbLoader implements CommandLineRunner {

    private LaptopRepository laptopRepository;
    private CSVService csvService;

    public DbLoader(LaptopRepository laptopRepository,
                    CSVService csvService) {
        this.laptopRepository = laptopRepository;
        this.csvService = csvService;
    }

    @Override
    public void run(String... args) throws Exception {
        laptopRepository.deleteAll();
        csvService.save("./laptops.csv");
    }
}

