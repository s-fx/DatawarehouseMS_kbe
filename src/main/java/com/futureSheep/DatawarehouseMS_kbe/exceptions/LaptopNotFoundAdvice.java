package com.futureSheep.DatawarehouseMS_kbe.exceptions;

import com.futureSheep.DatawarehouseMS_kbe.exceptions.LaptopNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Bad request")
public class LaptopNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(LaptopNotFoundException.class)
    String laptopNotFoundHandler(LaptopNotFoundException ex) {
        return ex.getMessage();
    }
}
