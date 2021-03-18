package com.zemoso.dna;

import edu.duke.URLResource;

public class Week2PracticeExercise1PartFour {

    public static void main(String[] args) {
        readLink();
    }

    public static void readLink(){
        URLResource url = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        Iterable<String> links = url.words();
        for(String link : links)
            if(link.indexOf("youtube.com") != -1){
                int startIndex = link.indexOf("\"");
                int endIndex = link.indexOf("\"", startIndex+1);
                System.out.println(link.substring(startIndex, endIndex+1));
            }
    }
}
