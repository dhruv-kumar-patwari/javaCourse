package com.zemoso.dna;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import edu.duke.StorageResource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.zemoso.dna.Week2PracticeExercise3Part2.cgRatio;

public class MultipleDNA {
    private static String startCodon = "atg";
    private static final List<String> stopCodons = new ArrayList<>();
    private static StorageResource storageResource = new StorageResource();

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
        System.out.println("found " + resultPartOne + " in Part 1");
        printAllGenesContainingAllStopCodons(s);
        System.out.println(storageResource.size());
        processGenes();
        System.out.println(storageResource.data());
    }

    private static void processGenes() {
        Iterable<String> genes = storageResource.data();
        StorageResource longerThanNine = new StorageResource();
        StorageResource greaterThanPointThreeFive = new StorageResource();
        int maxLength = 0;
        for(String gene : genes){
            if(gene.length() > maxLength)
                maxLength = gene.length();
            if(gene.length() > 9)
                longerThanNine.add(gene);
            if(cgRatio(gene) > 0.35)
                greaterThanPointThreeFive.add(gene);
        }
        System.out.println("Genes with length more than 9\n" + longerThanNine.data() + "\n" + longerThanNine.size());
        System.out.println("Genes with C-G Ratio greater than 0.35\n" + greaterThanPointThreeFive.data()+ "\n" + greaterThanPointThreeFive.size());
        System.out.println("Length of longest gene: " + maxLength);
    }

    private static String partOne(String dna){
        int startIndexOfProtein = dna.indexOf(startCodon);
        int dist;
        int minDist = dna.length();

        settingUpStopCodonList();

        if (startIndexOfProtein == -1) {
            return "";
        }

        for(String stopCodon : stopCodons){
            dist = findStopCodon(dna, startIndexOfProtein, stopCodon);
            if(minDist > dist) minDist = dist;
        }
        return (dna.substring(startIndexOfProtein, minDist + 3));
    }

    private static int findStopCodon(String dna, int startInteger, String stopCodon){
        int stopCodonIndex = 0;
        while(stopCodonIndex != -1) {
            stopCodonIndex = dna.indexOf(stopCodon, startInteger);
            if (stopCodonIndex == -1)
                break;
            if (isLengthOfProteinMultipleOfThree(startInteger, stopCodonIndex))
                return stopCodonIndex;
            else
                startInteger = stopCodonIndex + 3;
        }
        return dna.length();
    }
    private static boolean isLengthOfProteinMultipleOfThree(int startIndexOfProtein, int endIndexOfProtein) {
        return (endIndexOfProtein - startIndexOfProtein) % 3 == 0;
    }

    private static void printAllGenesContainingAllStopCodons(String dna){
        settingUpStopCodonList();
        for (String stopCodon : stopCodons) {
            printAllGenesWithAStopCodon(dna, stopCodon);
        }
    }

    private static void printAllGenesWithAStopCodon(String dna, String stopCodon) {
        int startIndexOfProtein = dna.indexOf(startCodon);

        if (startIndexOfProtein == -1){}

        while(true){
            int dist = findStopCodon(dna, startIndexOfProtein, stopCodon);

            if (dist == dna.length())
                break;

            storageResource.add(dna.substring(startIndexOfProtein, dist + 3));
            startIndexOfProtein = dna.indexOf(startCodon, dist+3);

            if (startIndexOfProtein == -1) break;
        }
    }

    private static void settingUpStopCodonList() {
        stopCodons.add("taa");
        stopCodons.add("tag");
        stopCodons.add("tga");
    }


}
