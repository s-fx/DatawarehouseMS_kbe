package com.futureSheep.DatawarehouseMS_kbe.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.futureSheep.DatawarehouseMS_kbe.exceptions.LaptopNotFoundException;
import com.futureSheep.DatawarehouseMS_kbe.model.Laptop;
import com.futureSheep.DatawarehouseMS_kbe.repositories.LaptopRepository;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductService.class})
@ExtendWith(SpringExtension.class)
class ProductServiceTest {
    @MockBean
    private LaptopRepository laptopRepository;

    @MockBean(name = "LaptopValidationService")
    private LaptopValidationService laptopValidationService;

    @Autowired
    private ProductService productService;

    @Test
    void testCollectAllLaptops() {
        when(this.laptopRepository.findAll()).thenReturn(new ArrayList<>());
        assertEquals(0, this.productService.collectAllLaptops().length);
        verify(this.laptopRepository).findAll();
    }

    @Test
    void testCollectAllLaptops2() {
        when(this.laptopRepository.findAll()).thenThrow(new LaptopNotFoundException(UUID.randomUUID()));
        assertThrows(LaptopNotFoundException.class, () -> this.productService.collectAllLaptops());
        verify(this.laptopRepository).findAll();
    }

    @Test
    void testValidateLaptopBeforeSavingIntoDB() {
        when(this.laptopValidationService.addLaptop((Laptop) any())).thenReturn("Add Laptop");

        Laptop laptop = new Laptop();
        laptop.setBrand("Brand");
        laptop.setId(UUID.randomUUID());
        laptop.setMehrwertsteuer(10.0d);
        laptop.setPrice(BigDecimal.valueOf(42L));
        laptop.setWeight(10.0d);
        Laptop actualValidateLaptopBeforeSavingIntoDBResult = this.productService.validateLaptopBeforeSavingIntoDB(laptop);
        assertSame(laptop, actualValidateLaptopBeforeSavingIntoDBResult);
        assertEquals("42", actualValidateLaptopBeforeSavingIntoDBResult.getPrice().toString());
        verify(this.laptopValidationService).addLaptop((Laptop) any());
    }

    @Test
    void testValidateLaptopBeforeSavingIntoDB2() {
        when(this.laptopValidationService.addLaptop((Laptop) any()))
                .thenThrow(new LaptopNotFoundException(UUID.randomUUID()));

        Laptop laptop = new Laptop();
        laptop.setBrand("Brand");
        laptop.setId(UUID.randomUUID());
        laptop.setMehrwertsteuer(10.0d);
        laptop.setPrice(BigDecimal.valueOf(42L));
        laptop.setWeight(10.0d);
        assertThrows(LaptopNotFoundException.class, () -> this.productService.validateLaptopBeforeSavingIntoDB(laptop));
        verify(this.laptopValidationService).addLaptop((Laptop) any());
    }

    @Test
    void testSaveLaptopIntoDB() {
        Laptop laptop = new Laptop();
        laptop.setBrand("Brand");
        laptop.setId(UUID.randomUUID());
        laptop.setMehrwertsteuer(10.0d);
        BigDecimal valueOfResult = BigDecimal.valueOf(42L);
        laptop.setPrice(valueOfResult);
        laptop.setWeight(10.0d);
        when(this.laptopRepository.save((Laptop) any())).thenReturn(laptop);

        Laptop laptop1 = new Laptop();
        laptop1.setBrand("Brand");
        UUID randomUUIDResult = UUID.randomUUID();
        laptop1.setId(randomUUIDResult);
        laptop1.setMehrwertsteuer(10.0d);
        laptop1.setPrice(BigDecimal.valueOf(42L));
        laptop1.setWeight(10.0d);
        this.productService.saveLaptopIntoDB(laptop1);
        verify(this.laptopRepository).save((Laptop) any());
        assertEquals("Brand", laptop1.getBrand());
        assertEquals(10.0d, laptop1.getWeight());
        assertEquals(valueOfResult, laptop1.getPrice());
        assertEquals(10.0d, laptop1.getMehrwertsteuer());
        assertSame(randomUUIDResult, laptop1.getId());
    }

    @Test
    void testSaveLaptopIntoDB2() {
        when(this.laptopRepository.save((Laptop) any())).thenThrow(new LaptopNotFoundException(UUID.randomUUID()));

        Laptop laptop = new Laptop();
        laptop.setBrand("Brand");
        laptop.setId(UUID.randomUUID());
        laptop.setMehrwertsteuer(10.0d);
        laptop.setPrice(BigDecimal.valueOf(42L));
        laptop.setWeight(10.0d);
        assertThrows(LaptopNotFoundException.class, () -> this.productService.saveLaptopIntoDB(laptop));
        verify(this.laptopRepository).save((Laptop) any());
    }

    @Test
    void testGetSingleLaptop() {
        Laptop laptop = new Laptop();
        laptop.setBrand("Brand");
        laptop.setId(UUID.randomUUID());
        laptop.setMehrwertsteuer(10.0d);
        laptop.setPrice(BigDecimal.valueOf(42L));
        laptop.setWeight(10.0d);
        Optional<Laptop> ofResult = Optional.of(laptop);
        when(this.laptopRepository.findById((UUID) any())).thenReturn(ofResult);
        Laptop actualSingleLaptop = this.productService.getSingleLaptop(UUID.randomUUID());
        assertSame(laptop, actualSingleLaptop);
        assertEquals("42", actualSingleLaptop.getPrice().toString());
        verify(this.laptopRepository).findById((UUID) any());
    }

    @Test
    void testGetSingleLaptop2() {
        when(this.laptopRepository.findById((UUID) any())).thenReturn(Optional.empty());
        assertThrows(LaptopNotFoundException.class, () -> this.productService.getSingleLaptop(UUID.randomUUID()));
        verify(this.laptopRepository).findById((UUID) any());
    }

    @Test
    void testGetSingleLaptop3() {
        when(this.laptopRepository.findById((UUID) any())).thenThrow(new LaptopNotFoundException(UUID.randomUUID()));
        assertThrows(LaptopNotFoundException.class, () -> this.productService.getSingleLaptop(UUID.randomUUID()));
        verify(this.laptopRepository).findById((UUID) any());
    }

    @Test
    void testDeleteLaptop() {
        Laptop laptop = new Laptop();
        laptop.setBrand("Brand");
        laptop.setId(UUID.randomUUID());
        laptop.setMehrwertsteuer(10.0d);
        laptop.setPrice(BigDecimal.valueOf(42L));
        laptop.setWeight(10.0d);
        Optional<Laptop> ofResult = Optional.of(laptop);
        doNothing().when(this.laptopRepository).deleteById((UUID) any());
        when(this.laptopRepository.findById((UUID) any())).thenReturn(ofResult);
        this.productService.deleteLaptop(UUID.randomUUID());
        verify(this.laptopRepository).findById((UUID) any());
        verify(this.laptopRepository).deleteById((UUID) any());
    }

    @Test
    void testDeleteLaptop2() {
        Laptop laptop = new Laptop();
        laptop.setBrand("Brand");
        laptop.setId(UUID.randomUUID());
        laptop.setMehrwertsteuer(10.0d);
        laptop.setPrice(BigDecimal.valueOf(42L));
        laptop.setWeight(10.0d);
        Optional<Laptop> ofResult = Optional.of(laptop);
        doThrow(new LaptopNotFoundException(UUID.randomUUID())).when(this.laptopRepository).deleteById((UUID) any());
        when(this.laptopRepository.findById((UUID) any())).thenReturn(ofResult);
        assertThrows(LaptopNotFoundException.class, () -> this.productService.deleteLaptop(UUID.randomUUID()));
        verify(this.laptopRepository).findById((UUID) any());
        verify(this.laptopRepository).deleteById((UUID) any());
    }

    @Test
    void testDeleteLaptop3() {
        doNothing().when(this.laptopRepository).deleteById((UUID) any());
        when(this.laptopRepository.findById((UUID) any())).thenReturn(Optional.empty());
        assertThrows(LaptopNotFoundException.class, () -> this.productService.deleteLaptop(UUID.randomUUID()));
        verify(this.laptopRepository).findById((UUID) any());
    }

    @Test
    void getSingleLaptopThrowsException(){
        UUID id = UUID.randomUUID();
        Exception exception = assertThrows(LaptopNotFoundException.class, () -> {
            productService.getSingleLaptop(id);
        });

        String expectedMessage = "Could not find Laptop " + id;
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage,actualMessage);
    }
}

