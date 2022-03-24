package com.futureSheep.DatawarehouseMS_kbe;

import com.futureSheep.DatawarehouseMS_kbe.csvImporter.CSVImporter;
import com.futureSheep.DatawarehouseMS_kbe.csvImporter.CSVImporterImpl;
import com.futureSheep.DatawarehouseMS_kbe.csvImporter.CSVService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DatawarehouseMsKbeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatawarehouseMsKbeApplication.class, args);

		CSVImporter csvImporter = new CSVImporterImpl();
		csvImporter.importCSVFromFileSystem("./laptops.csv");




	}
	@Bean
	CommandLineRunner initDatabase(LaptopRepository repository) {

		return args -> {
			CSVService csvService = new CSVService(repository);
			csvService.save("./laptops.csv");
		};
	}




}
