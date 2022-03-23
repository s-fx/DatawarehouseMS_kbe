package com.futureSheep.DatawarehouseMS_kbe.controller;

import java.util.UUID;


public class LaptopNotFoundException extends RuntimeException {

    public LaptopNotFoundException(UUID id) {
        super("Could not find Laptop " + id);
    }
}
