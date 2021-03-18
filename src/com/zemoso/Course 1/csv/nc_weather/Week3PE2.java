package com.zemoso.csv.nc_weather;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class Week3PE2 {
    public static void main(String[] args) {
        tester();
    }
    public static void tester(){
        String filename = fileWithColdestTemperature();
        System.out.println(filename);
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = coldestHourInFile(parser);
        System.out.println(record.get("TemperatureF") + "  " + record.get("DateUTC"));
        filename = fileWithLowestHumidity();
        fr = new FileResource(filename);
        record = lowestHumidityInFile(fr.getCSVParser());
        System.out.println(record.get("Humidity") + "  " + record.get("DateUTC"));
    }

    public static CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord lowestTempRecord = null;
        for(CSVRecord record: parser){
            if(lowestTempRecord == null)
                lowestTempRecord = record;
            else{
                double currentTemp = Double.parseDouble(record.get("TemperatureF"));
                double lowestSoFar = Double.parseDouble(lowestTempRecord.get("TemperatureF"));
                if(currentTemp <= -9000) continue;
                else if(currentTemp < lowestSoFar)
                    lowestTempRecord = record;
            }
        }
        return lowestTempRecord;
    }

    public static String fileWithColdestTemperature(){
        DirectoryResource dir = new DirectoryResource();
        CSVRecord currTemp;
        CSVRecord minTemp = null;
        String fileName = "";
        for (File f: dir.selectedFiles()){
            FileResource fr = new FileResource(f);
            currTemp = coldestHourInFile(fr.getCSVParser());
            if (minTemp == null){
                minTemp = currTemp;
                fileName = f.getName();
            }
            else if(isSmallerThanMinValue(currTemp, minTemp)){
                minTemp = currTemp;
                fileName = f.getAbsolutePath();
            }
        }
        return fileName;
    }

    public static CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumisity = null;
        for(CSVRecord record: parser){
            if(lowestHumisity == null)
                lowestHumisity = record;
            else{
                String currHumidity = record.get("Humidity");
                if(currHumidity == "N/A") continue;
                else {
                    double currHumidityValue = Double.parseDouble(record.get("Humidity"));
                    double lowestSoFar = Double.parseDouble(lowestHumisity.get("Humidity"));
                    if(currHumidityValue < lowestSoFar) {
                        lowestHumisity = record;
                    }

                }
            }
        }
        return lowestHumisity;
    }

    public static String fileWithLowestHumidity(){
        DirectoryResource dir = new DirectoryResource();
        CSVRecord currHumidity;
        CSVRecord minHumidity = null;
        String fileName = "";
        for (File f: dir.selectedFiles()){
            FileResource fr = new FileResource(f);
            currHumidity = lowestHumidityInFile(fr.getCSVParser());
            if (minHumidity == null){
                minHumidity = currHumidity;
                fileName = f.getName();
            }
            else if(isSmallerThanMinValue(currHumidity, minHumidity)){
                minHumidity = currHumidity;
                fileName = f.getAbsolutePath();
            }
        }
        return fileName;
    }

    private static boolean isSmallerThanMinValue(CSVRecord currTemp, CSVRecord minTemp) {
        return Double.parseDouble(currTemp.get("TemperatureF")) < Double.parseDouble(minTemp.get("TemperatureF"));
    }
}
