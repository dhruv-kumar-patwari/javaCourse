package com.zemoso.Course2.week1;

public class CeaserCipher {
    private static final String capitalLetter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        String encrypted = (encrypt("Hello World", 1));
        String encryptedTwoKey = encryptTwoKeys("Hello World", 1, 2);
        System.out.println(decrypt(encrypted, 1));
        System.out.println(decryptTwoKeys(encryptedTwoKey, 1, 2));

    }

    public static String encrypt(String input, int key){
        char[] wordInLetters = input.toCharArray();
        for (int i = 0; i < input.length(); i++){
            if(wordInLetters[i] != ' ')
                wordInLetters[i] = findShiftedLetter(wordInLetters[i], key);
        }
        return String.valueOf(wordInLetters);
    }

    private static char findShiftedLetter(char letter, int key) {
        int val = letter;
        if(capitalLetter.contains(String.valueOf(letter)))
            return (char)((((val-64) + key) % 26) + 64);
        else
            return (char)((((val-97) + key) % 26) + 97);
    }

    public static String encryptTwoKeys(String input, int key1, int key2){
        char[] wordInLetters = input.toCharArray();
        for (int i = 0; i < input.length(); i = i+2){
            if(wordInLetters[i] != ' '){
                wordInLetters[i] = findShiftedLetter(wordInLetters[i], key1);
                if(i+1 != input.length()){
                    wordInLetters[i] = findShiftedLetter(wordInLetters[i], key2);
                }
            }
        }
        return String.valueOf(wordInLetters);
    }

    public static String decrypt(String input, int key) {
        String message = encrypt(input, 26 - key);
        return message;
    }

    public static String decryptTwoKeys(String input, int key1, int key2){
        String message = encryptTwoKeys(input, (26 - key1), 26-key2);
        return message;
    }
}
