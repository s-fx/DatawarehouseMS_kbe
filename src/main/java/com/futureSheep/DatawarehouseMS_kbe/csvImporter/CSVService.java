package com.futureSheep.DatawarehouseMS_kbe.csvImporter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.futureSheep.DatawarehouseMS_kbe.LaptopRepository;
import com.futureSheep.DatawarehouseMS_kbe.model.Laptop;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CSVService {


    private final LaptopRepository repository;


    public void save(String filename) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            List<Laptop> laptops = CSVHelper.csvToLaptops(fileInputStream);
            System.out.println(repository);
            System.out.println("laptop"+laptops);
            repository.saveAll(laptops);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


//    public List<Laptop> getAllTutorials() {
//        return repository.findAll();
//    }
}
