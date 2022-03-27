package com.futureSheep.DatawarehouseMS_kbe.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.futureSheep.DatawarehouseMS_kbe.model.Laptop;

import com.futureSheep.DatawarehouseMS_kbe.utils.CSVHelper;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CSVService {


    private MongoOperations mongoOperations;


    public void save(String filename) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            List<Laptop> laptops = CSVHelper.csvToLaptops(fileInputStream);
            for (Laptop laptop : laptops) {
                mongoOperations.save(laptop, "laptopCSV");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
