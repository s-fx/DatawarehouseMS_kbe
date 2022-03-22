package com.futureSheep.DatawarehouseMS_kbe.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document
public class Laptop {
    @Id
    private String id;
    private String brand;
    private BigDecimal price;
    private double weight;
    private Location location;
    private BigDecimal mehrwertsteuer;

    public Laptop(String brand, BigDecimal price, double weight, Location location) {
        this.brand = brand;
        this.price = price;
        this.weight = weight;
        this.location = location;
        this.mehrwertsteuer = BigDecimal.valueOf(0.00);
    }

    public Laptop() {
    }
}
