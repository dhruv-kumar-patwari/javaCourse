package com.zemoso.dna;

public class Week2PracticeExercise2Part2 {
    public static void main(String[] args) {
        System.out.println(findingMultipleOccurrence("AA", "ATAAAA"));
    }
    public static int findingMultipleOccurrence(String stringa, String stringb){
        int position = stringb.indexOf(stringa);
        if (position == -1) return 0;
        int count = 1;
        while (position != -1){
            position = stringb.indexOf(stringa, position+stringa.length()+1);
            count++;
        }
        return count;
    }
}
