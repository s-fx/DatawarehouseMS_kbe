package com.futureSheep.DatawarehouseMS_kbe;

import com.futureSheep.DatawarehouseMS_kbe.csvImporter.CSVImporter;
import com.futureSheep.DatawarehouseMS_kbe.csvImporter.CSVImporterImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatawarehouseMsKbeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatawarehouseMsKbeApplication.class, args);


		CSVImporter csvImporter = new CSVImporterImpl();
		csvImporter.importCSVFromFileSystem("./laptops.csv");

	}

}
