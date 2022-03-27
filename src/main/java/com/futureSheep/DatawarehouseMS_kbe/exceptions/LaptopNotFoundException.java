package com.futureSheep.DatawarehouseMS_kbe.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.UUID;


public class LaptopNotFoundException extends RuntimeException {

    public LaptopNotFoundException(UUID id) {
        super("Could not find Laptop " + id);
    }
}
