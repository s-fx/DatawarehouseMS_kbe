package com.futureSheep.DatawarehouseMS_kbe.csvImporter;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.extern.apachecommons.CommonsLog;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

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
