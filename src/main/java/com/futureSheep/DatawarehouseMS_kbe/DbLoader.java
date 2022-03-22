package com.futureSheep.DatawarehouseMS_kbe;

import com.futureSheep.DatawarehouseMS_kbe.model.Laptop;
import com.futureSheep.DatawarehouseMS_kbe.model.Location;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DbLoader implements CommandLineRunner {

    private LaptopRepository laptopRepository;

    public DbLoader(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Location location = new Location(52.521992, 13.413244);
        Laptop laptop = new Laptop("HP", BigDecimal.valueOf(999.99), 10, location);

        laptopRepository.insert(laptop);

    }
}
