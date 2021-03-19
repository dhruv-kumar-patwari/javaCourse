package com.zemoso.Course2.week2;

import edu.duke.*;
import java.util.ArrayList;

public class charactersInPlay {

    private ArrayList<String> characterName;
    private ArrayList<Integer> characterFrequency;

    public charactersInPlay() {
        characterName = new ArrayList<String>();
        characterFrequency = new ArrayList<Integer>();
    }

    public void updateFrequencyInList(String person){
        int index = characterName.indexOf(person);
        if (index == -1) {
            characterName.add(person);
            characterFrequency.add(1);
        }
        else {
            int freq = characterFrequency.get(index);
            characterFrequency.set(index,freq+1);
        }
    }

    public void findAllCharacters() {
        characterName.clear();
        characterFrequency.clear();

        FileResource Resource = new FileResource();
        for (String line: Resource.lines()){
            if (line.contains(".")) {
                int idx = line.indexOf(".");
                String possible_name = line.substring(0,idx);
                updateFrequencyInList(possible_name);
            }
        }
    }

    public void tester() {
        findAllCharacters();
        for (int k = 0; k < characterFrequency.size(); k++) {
            if (characterFrequency.get(k) > 1) {
                System.out.println("The main character is: "+ characterName.get(k) +"\t"
                        +"The number of speaking parts is: "+ characterFrequency.get(k));
            }
        }
        charactersWithNumParts(2, 3);
    }

    public void charactersWithNumParts(int num1, int num2) {
        for (int k = 0; k < characterFrequency.size(); k++) {
            if (characterFrequency.get(k) >= num1 && characterFrequency.get(k)<= num2) {
                System.out.println("The main character between : " + num1 + " and " + num2
                        + " is " + characterName.get(k) +"\t"
                        +"The number of speaking parts is: "+ characterFrequency.get(k));
            }
        }
    }
}
