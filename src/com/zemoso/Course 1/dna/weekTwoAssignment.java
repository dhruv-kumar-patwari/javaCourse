package com.zemoso.dna;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class weekTwoAssignment {

    private static String startOfProtein = "atg";
    private static String endOfProtein = "taa";
    private static int startIndexOfProtein;
    private static int endIndexOfProtein;

    public static void main(String[] args) {
        openFileWithDNAString();
    }

    public static void openFileWithDNAString() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            printOutput(fr);
        }
    }

    private static void printOutput(FileResource fr) {
        String s = fr.asString();
        System.out.println("read " + s.length() + " characters");
        String resultPartOne = partOne(s);
        String resultPartTwo = partTwo(s, "atg", "tag");
        System.out.println("found " + resultPartOne + " in Part 1");
        System.out.println("found " + resultPartTwo + " in Part 2");
    }

    public static String partOne(String dna) {
        getStartAndEndIndex(dna);
        if (startIndexOfProtein == -1) {
            return "";
        }
        if (isLengthOfProteinMultipleOfThree(startIndexOfProtein, endIndexOfProtein)) {
            return dna.substring(startIndexOfProtein, endIndexOfProtein);
        }
        else {
            return "";
        }
    }

    public static String partTwo(String dna, String startCodon, String endCodon){
        startIndexOfProtein = dna.indexOf(startCodon);
        if (startIndexOfProtein == -1)
            return "";
        endIndexOfProtein = dna.indexOf(endCodon, startIndexOfProtein +3) + 3;
        if(isStringLowerCase(startCodon))
            return (dna.substring(startIndexOfProtein, endIndexOfProtein).toLowerCase());
        else
            return (dna.substring(startIndexOfProtein, endIndexOfProtein).toUpperCase());
    }
    private static boolean isStringLowerCase(String str){
        char[] charArray = str.toCharArray();

        for(int i=0; i < charArray.length; i++){
            if( !Character.isLowerCase( charArray[i] ))
                return false;
        }
        return true;
    }
    private static void getStartAndEndIndex(@NotNull String dna) {
        startIndexOfProtein = dna.indexOf(startOfProtein);
        if(startIndexOfProtein != -1)
            endIndexOfProtein = dna.indexOf(endOfProtein, startIndexOfProtein +3) + 3;
    }

    private static boolean isLengthOfProteinMultipleOfThree(int startIndexOfProtein, int endIndexOfProtein) {
        return (endIndexOfProtein - startIndexOfProtein) % 3 == 0;
    }
}
