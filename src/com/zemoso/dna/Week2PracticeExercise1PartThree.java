package com.zemoso.dna;

public class Week2PracticeExercise1PartThree {

    public static void main(String[] args) {
        testingTwoOccurrences();
        testLastPart();
    }

    public static boolean twoOccurrences(String stringa, String stringb){
        int indexOfStringA = stringb.indexOf(stringa);
        if (stringb.indexOf(stringa, indexOfStringA + stringa.length()) != -1)
            return true;
        return false;
    }

    public static void testingTwoOccurrences(){
        System.out.println(twoOccurrences("a", "banana"));
        System.out.println(twoOccurrences("atg", "ctgtatgta"));
    }

    public static void lastPart(String stringa, String stringb){
        int indexOfStringA = stringb.indexOf(stringa);
        if(indexOfStringA == -1)
            System.out.println(stringb);
        else
            System.out.println(stringb.substring(indexOfStringA+stringa.length()));
    }

    public static void testLastPart(){
        lastPart("a", "banana");
        lastPart("atg", "ctgtatgta");

    }
}
