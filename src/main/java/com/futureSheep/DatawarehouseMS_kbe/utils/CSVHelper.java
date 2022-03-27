package com.futureSheep.DatawarehouseMS_kbe.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.futureSheep.DatawarehouseMS_kbe.model.Laptop;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;
public class CSVHelper {

    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Laptop> csvToLaptops(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withIgnoreHeaderCase().withTrim());) {
            List<Laptop> laptops = new ArrayList<Laptop>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                Laptop laptop = new Laptop(
                        UUID.fromString((csvRecord.get(0))),
                        csvRecord.get(1),
                        BigDecimal.valueOf(Double.parseDouble(csvRecord.get(2))),
                        Double.parseDouble(csvRecord.get(3)),
                        Double.valueOf(csvRecord.get(4))
                );
                laptops.add(laptop);
                System.out.println(laptops);
            }
            return laptops;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
