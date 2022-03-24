package com.futureSheep.DatawarehouseMS_kbe.csvImporter;

import com.futureSheep.DatawarehouseMS_kbe.model.Laptop;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CommonsLog
public class CSVImporterImpl implements CSVImporter {

    @Override
    public void importCSVFromFileSystem(String file) {
        try {
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReader(fileReader);
            List<String[]> allData = csvReader.readAll();
            log.info("Read csv Data!");

            for (String[] row : allData) {
                for (String cell : row) {
                    System.out.print(cell + "\t");
                    log.debug(cell + "\t");
                }
                System.out.println();
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

}
