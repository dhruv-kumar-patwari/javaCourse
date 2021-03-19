package com.zemoso.Course2.week2;

import edu.duke.*;
import java.util.HashMap;

public class CodonCount {

    private HashMap<String, Integer> DNA_count;

    public CodonCount() {
        DNA_count = new HashMap<String, Integer>();
    }

    public void buildCodonMap(int start, String dna) {
        DNA_count.clear();
        int k = 0; //number of codons
        int num = 0; //number of iterations
        k = (dna.length()-start)/3;
        String current;
        while (num <= k-1) {
            current = dna.substring(num*3+start, num*3+start+3);
            if (!DNA_count.containsKey(current))
            {DNA_count.put(current, 1);}
            else
            {DNA_count.put(current, DNA_count.get(current)+1);}
            num = num + 1;
        }

    }

    public String getMostCommonCodon() {

        int largest = 0;
        int current = 0;
        String largest_count = null;
        for (String index : DNA_count.keySet()) {
            current = DNA_count.get(index);
            if (largest < current) {
                largest = current;
                largest_count = index;
            }
        }
        return largest_count;
    }

    public void printCodonCounts(int start, int end) {
        int current = 0;
        for (String index : DNA_count.keySet()) {
            current = DNA_count.get(index);
            if (current >= start && current <= end)
                System.out.println(index+": "+current+"\t");
        }

    }

    public void test() {
        FileResource DNA = new FileResource("/home/dhrkp/javaCourseraCourse/src/com/zemoso/Course2/week2/data/dnaMystery1.txt");
        String dna = DNA.asString();
        int start = 1;
        int end = 5;

        buildCodonMap(0, dna);
        System.out.println("Reading frame starting with 0 results in "+DNA_count.size()+" unique codons"+"\t");
        String the_largest_count = getMostCommonCodon();
        System.out.println("and most common codon is "+the_largest_count+" with count "+DNA_count.get(the_largest_count)+"\t");
        System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:"+"\t");
        printCodonCounts(start, end);

        buildCodonMap(1, dna);
        System.out.println("Reading frame starting with 1 results in "+DNA_count.size()+" unique codons"+"\t");
        the_largest_count = getMostCommonCodon();
        System.out.println("and most common codon is "+the_largest_count+" with count "+DNA_count.get(the_largest_count)+"\t");
        System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:"+"\t");
        printCodonCounts(start, end);

        buildCodonMap(2, dna);
        System.out.println("Reading frame starting with 2 results in "+DNA_count.size()+" unique codons"+"\t");
        the_largest_count = getMostCommonCodon();
        System.out.println("and most common codon is "+the_largest_count+" with count "+DNA_count.get(the_largest_count)+"\t");
        System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:"+"\t");
        printCodonCounts(start, end);
    }
}
