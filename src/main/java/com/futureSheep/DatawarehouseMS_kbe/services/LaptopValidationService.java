package com.futureSheep.DatawarehouseMS_kbe.services;

import com.futureSheep.DatawarehouseMS_kbe.model.Laptop;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@CommonsLog
@Service("LaptopValidationService")
public class LaptopValidationService {

    private Validator validator;
    private ProductService productService;


    @Autowired
    public LaptopValidationService(Validator validator, @Lazy ProductService productService) {
        this.validator = validator;
        this.productService = productService;
    }


    public String addLaptop(Laptop laptop) {
        Set<ConstraintViolation<Laptop>> violations = validator.validate(laptop);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Laptop> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            log.warn("[LaptopValidationService] Error occurred: " + sb);
            throw new ConstraintViolationException("Error occurred: " + sb, violations);
        }
        productService.saveLaptopIntoDB(laptop);
        log.info("[LaptopValidationService] laptop added: " + laptop);
        return "Laptop: " + laptop.getBrand() + " was added";
    }
}
