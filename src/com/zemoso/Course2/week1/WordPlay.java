package com.zemoso.Course2.week1;

import java.util.Locale;

public class WordPlay {

    public static void main(String[] args) {
        System.out.println(replaceVowels("Hello World", '*'));
        System.out.println(emphasize("Hello World", 'o'));
    }

    public static String replaceVowels(String phrase, char ch){
        char[] wordInLetters = phrase.toCharArray();
        for (int i = 0; i < phrase.length(); i++){
            if (isVowel(wordInLetters[i]))
                wordInLetters[i] = ch;
        }
        return String.valueOf(wordInLetters);
    }

    public static String emphasize(String phrase, char ch){
        char[] wordInLetters = phrase.toCharArray();
        for (int i = 0; i < phrase.length(); i++){
            if (ch == wordInLetters[i]){
                if(i%2 == 0)
                    wordInLetters[i] = '*';
                else
                    wordInLetters[i] = '+';
            }
        }
        return String.valueOf(wordInLetters);
    }

    public static boolean isVowel(char ch){
        String vowels = "aeiou";
        String charac = String.valueOf(ch);
        if(vowels.contains(charac.toLowerCase()))
            return true;
        return false;
    }
}
