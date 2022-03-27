package com.futureSheep.DatawarehouseMS_kbe.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Document
public class Laptop {
    @Id
    private UUID id;
    private String brand;
    private BigDecimal price;
    private double weight;
    private double mehrwertsteuer;

    public Laptop(UUID id, String brand, BigDecimal price, double weight, double mehrwertsteuer) {
        this.id = id;
        this.brand = brand;
        this.price = price;
        this.weight = weight;
        this.mehrwertsteuer = 0.0;
    }


    public Laptop() {
    }

}
