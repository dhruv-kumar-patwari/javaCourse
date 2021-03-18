package com.zemoso.dna;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;

public class Week2PracticeExercise3Part2 {
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
        double resultPartOne = cgRatio(s);
        System.out.println("found " + resultPartOne + " in Part 1");
    }

    protected static double cgRatio(String s) {
        int count = 0;
        char[] characterList = s.toCharArray();
        for (char c : characterList){
            if (c == 'c' || c == 'g')
                count++;
        }
        return ((double)count/(double)s.length());
    }
}
