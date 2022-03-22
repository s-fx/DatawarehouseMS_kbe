package com.futureSheep.DatawarehouseMS_kbe.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.UUID;


@ControllerAdvice
public class LaptopNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(LaptopNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(LaptopNotFoundException ex) {
        return ex.getMessage();
    }
}
