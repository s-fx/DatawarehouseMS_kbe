package com.futureSheep.DatawarehouseMS_kbe;

import com.futureSheep.DatawarehouseMS_kbe.model.Laptop;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class LaptopController {

    private final ProductService productService;


    /**
     * CollectionModel = HATEOAS container, encapsulate collection of laptop resources
     * why all these links? => makes it possible to evolve REST services over time
     * curl localhost:8080/api/laptops
     */
    @GetMapping("/laptops")
    public List<Laptop> getAllLaptops() {
        List<Laptop> laptops = productService.collectAllLaptops();
        return laptops;
    }

    /**
     * curl -v -X POST localhost:8080/api/laptops -H 'Content-Type:application/json' -d '{"brand": "JAJAJAJA", "price": "229.99", "weight": "12.1"}'
     * why wildcard <?> ???
     */
    @PostMapping("/laptops")
    public Laptop addLaptop(@RequestBody Laptop newLaptop) {
        Laptop laptop = productService.validateLaptopBeforeSavingIntoDB(newLaptop);
        return laptop;
    }

    /**
     * Get a single Laptop by id
     * EntityModel<T> = HATEOAS container, containing data and collection of links
     * curl command: curl -v localhost:8080/api/laptops/UUID | json_pp
     * Link: includes a URI and a relation (see assembler)
     */
    @GetMapping("/laptops/{id}")
    public Laptop getLaptop(@PathVariable String id) {
        return productService.getSingleLaptop(id);
    }



    /**
     * curl -v -X DELETE localhost:8080/api/laptops/UUID
     */
    @DeleteMapping("/laptops/{id}")
    ResponseEntity<?> deleteLaptop(@PathVariable String id) {
        productService.deleteLaptop(id);
        return ResponseEntity.noContent().build();
    }



}
