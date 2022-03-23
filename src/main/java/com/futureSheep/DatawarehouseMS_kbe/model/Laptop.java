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
    private Location location;
    private BigDecimal mehrwertsteuer;

    public Laptop(UUID id, String brand, BigDecimal price, double weight, Location location, BigDecimal mehrwertsteuer) {
        this.id = id;
        this.brand = brand;
        this.price = price;
        this.weight = weight;
        this.location = location;
        this.mehrwertsteuer = BigDecimal.valueOf(0.00);
    }

    public Laptop(String brand, BigDecimal price, double weight, Location location) {
        this.brand = brand;
        this.price = price;
        this.weight = weight;
        this.location = location;
        this.mehrwertsteuer = BigDecimal.valueOf(0.00);
    }

    public Laptop() {
    }

    public Laptop(String brand, BigDecimal price, double weight) {
        this.brand = brand;
        this.price = price;
        this.weight = weight;
    }
}
