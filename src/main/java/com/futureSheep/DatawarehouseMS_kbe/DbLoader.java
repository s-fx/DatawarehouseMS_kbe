
package com.futureSheep.DatawarehouseMS_kbe;

import com.futureSheep.DatawarehouseMS_kbe.model.Laptop;
import com.futureSheep.DatawarehouseMS_kbe.model.Location;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class DbLoader implements CommandLineRunner {

    private LaptopRepository laptopRepository;

    public DbLoader(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Location location = new Location(52.521992, 13.413244);
        Laptop laptop = new Laptop(UUID.randomUUID(), "HP_DATAWAREHOUSE", BigDecimal.valueOf(299.9), 3.3, location, BigDecimal.valueOf(0.0));
        Laptop laptop2 = new Laptop(UUID.randomUUID(), "DELL_DATAWAREHOUSE", BigDecimal.valueOf(599.9), 34.3, location, BigDecimal.valueOf(0.0));

        laptopRepository.save(laptop);
        laptopRepository.save(laptop2);

    }
}

