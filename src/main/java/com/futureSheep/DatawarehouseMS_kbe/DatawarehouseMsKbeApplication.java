package com.futureSheep.DatawarehouseMS_kbe;

import com.futureSheep.DatawarehouseMS_kbe.csvImporter.CSVImporter;
import com.futureSheep.DatawarehouseMS_kbe.csvImporter.CSVImporterImpl;
import com.futureSheep.DatawarehouseMS_kbe.model.Laptop;
import com.futureSheep.DatawarehouseMS_kbe.model.Location;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.math.BigDecimal;

@SpringBootApplication
public class DatawarehouseMsKbeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatawarehouseMsKbeApplication.class, args);

		CSVImporter csvImporter = new CSVImporterImpl();
		csvImporter.importCSVFromFileSystem("./laptops.csv");



	}



}
