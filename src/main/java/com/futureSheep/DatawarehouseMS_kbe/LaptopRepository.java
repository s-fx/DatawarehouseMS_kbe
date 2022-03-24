package com.futureSheep.DatawarehouseMS_kbe;

import com.futureSheep.DatawarehouseMS_kbe.model.Laptop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LaptopRepository extends MongoRepository<Laptop, UUID> {

}