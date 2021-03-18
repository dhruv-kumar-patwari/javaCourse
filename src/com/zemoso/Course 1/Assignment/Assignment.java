package com.zemoso.Assignment;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Assignment {

    private static int countFemale = 0;
    private static int countMale = 0;
    private static String filePath;

    public static void main(String[] args) {
        String name = "Dhruv";
        int year = 2000;
        int nameRank = getNameRank(year, name, "M");
        String nTnName = getnThName(2014, nameRank,"M");
        System.out.println(name + " born in " + year + " would be " + nTnName + " in 2014.");
        System.out.println(yearOfHighestRank("Dhruv", "M"));
        System.out.println(getAverageRank("Dhruv", "M"));
        System.out.println(getTotalBirthsRankedHigher(2000, "Dhruv", "M"));    }

    private static String getnThName(int year, int nameRank, String gender) {
        filePath = "/home/dhrkp/LAD/code/javaCourseraCourse/src/com/zemoso/Assignment/us_babynames_by_year/yob" + year +".csv";
        getNumberOfMaleAndFemale(filePath);
        FileResource fr = new FileResource(filePath);
        CSVParser parser = fr.getCSVParser(false);
        return findNameGivenRank(nameRank, parser, gender);

    }

    private static String findNameGivenRank(int nameRank, CSVParser parser, String gender) {
        int count = 0;
        String currGender;
        if (gender.equals("F")){
            for(CSVRecord record : parser){
                count++;
                if (count == nameRank)
                    return record.get(0);
            }
        }
        else{
            for(CSVRecord record : parser){
                currGender = record.get(1);
                if (currGender.equals("M")){
                    count++;
                    if (count == nameRank)
                        return record.get(0);
                }
            }
        }
        return "NO NAME";
    }

    public static int getNameRank(int year, String name,String gender){
        filePath = "/home/dhrkp/LAD/code/javaCourseraCourse/src/com/zemoso/Assignment/us_babynames_by_year/yob" + year +".csv";
        getNumberOfMaleAndFemale(filePath);
        FileResource fr = new FileResource(filePath);
        int rank = 0;
        CSVParser parser = fr.getCSVParser(false);
        for(CSVRecord record : parser){
            String currName = record.get(0);
            if( currName.equals(name)){
                String currGender = record.get(1);
                if(currGender.equals(gender)){
                    if(gender.equals("F"))
                        return ++rank;
                    return (++rank - countFemale);
                }
            }
            else
                rank++;
        }
        return -1;
    }

    private static void getNumberOfMaleAndFemale(String filePath) {
        countFemale = 0;
        countMale = 0;
        FileResource fr = new FileResource(filePath);
        CSVParser parser = fr.getCSVParser(false);
        for(CSVRecord record : parser){
            String gender = record.get(1);
            if(gender.equals("F")) {
                countFemale++;
            }
            else {
                countMale++;
            }
        }
    }

    public static int yearOfHighestRank(String name, String gender){
        int year = 2000;
        int minRank = 9999999;
        int currRank;
        for(; year < 2015; year++){
            currRank = getNameRank(year, name, gender);
            if(currRank < minRank)
                minRank = currRank;
        }
        return minRank;
    }

    public static double getAverageRank(String name, String gender){
        int year = 2000;
        int totalRank = 0;
        int currRank;
        int count = 0;
        for(; year < 2015; year++){
            currRank = getNameRank(year, name, gender);
            if(currRank == -1)
                return -1.0;
            totalRank+=currRank;
            count++;
        }
        return (double)totalRank/(double)count;
    }

    public static int getTotalBirthsRankedHigher(int year, String name, String gender){
        int count = 0;
        FileResource fr = new FileResource(filePath);
        CSVParser parser = fr.getCSVParser(false);
        for(CSVRecord record : parser){
            String currentGender = record.get(1);
            if(gender.equals("F")) {
                count += Integer.parseInt(record.get(2));
                if(record.get(0).equals(name))
                    return count;
            }
            else {
                count += Integer.parseInt(record.get(2));
                if(record.get(0).equals(name))
                    return count;
            }
        }
        return count;
    }
}
