package com.futureSheep.DatawarehouseMS_kbe;

import com.futureSheep.DatawarehouseMS_kbe.model.Laptop;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface LaptopRepository extends MongoRepository<Laptop, String> {

}