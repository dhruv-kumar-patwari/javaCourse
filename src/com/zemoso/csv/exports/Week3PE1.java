package com.zemoso.csv.exports;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLOutput;

public class Week3PE1 {
    public static void main(String[] args) {
        tester();
    }
    public static void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Germany"));
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "gold", "diamond");
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "gold"));
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999");
    }
    public static String countryInfo(CSVParser parser, String country){
        for (CSVRecord record : parser){
            String countryRow = (record.get("Country"));
            if(countryRow.contains(country))
                return getRecordInFormat(record);
        }
        return "NOT FOUND";
    }

    @NotNull
    private static String getRecordInFormat(CSVRecord record) {
        return record.get("Country") + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
    }

    public static void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record: parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2))
                System.out.println(record.get("Country"));
        }
    }

    public static int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for(CSVRecord record : parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem))
                count++;
        }
        return count;
    }

    public static void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record : parser){
            String recordAmount = record.get("Value (dollars)");
            if(recordAmount.length() > amount.length())
                System.out.println(record.get("Country") + ": " + recordAmount);
        }
    }
}
