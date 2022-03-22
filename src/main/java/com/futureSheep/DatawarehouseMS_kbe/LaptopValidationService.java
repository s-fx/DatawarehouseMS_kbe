package com.futureSheep.DatawarehouseMS_kbe;

import com.futureSheep.DatawarehouseMS_kbe.ProductService;
import com.futureSheep.DatawarehouseMS_kbe.model.Laptop;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Service("LaptopValidationService")
public class LaptopValidationService {

    //@Autowired
    private Validator validator;


/*   @Autowired
    private DataStorageEntryPoint wasAuchImmer
    -> das hier ist das interface um die laptops zu
    unserer data storage zu adden, glaube wir benutzen hier
    hibernate aber kann auch sein dass wir hier noch
    ne dao klasse schreiben müssen.*/

    //@Autowired
    private ProductService productService;

    public String addLaptop(Laptop laptop) {

        Set<ConstraintViolation<Laptop>> violations = validator.validate(laptop);

        if (!violations.isEmpty()) {

            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Laptop> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }

            throw new ConstraintViolationException("Error occured: " + sb, violations);

        }

        productService.saveLaptopIntoDB(laptop);

        return "Laptop: " + laptop.getBrand() + "was added";

    }
}
