package com.futureSheep.DatawarehouseMS_kbe;

import com.futureSheep.DatawarehouseMS_kbe.model.Laptop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
public interface LaptopRepository extends MongoRepository<Laptop, String> {

}
