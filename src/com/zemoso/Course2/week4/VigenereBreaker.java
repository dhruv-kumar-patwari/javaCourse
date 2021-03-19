package com.zemoso.Course2.week4;

import java.nio.charset.Charset;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {

    ArrayList<Integer> frequencyOfLetters = new ArrayList<Integer>();
    public static String Source = "/home/dhrkp/javaCourseraCourse/src/com/zemoso/Course2/week4/dictionaries";
    public HashMap<String,HashSet<String>> languages = new HashMap<String,HashSet<String>>();

    public String sliceString(String message, int whichSlice, int totalSlices) {
        char[] letters = message.toCharArray();
        char[] sliced = new char[((message.length() - whichSlice) / totalSlices) + 1];
        int j = 0;

        for(int i = whichSlice; i < message.length(); i = i+totalSlices){
            sliced[j] = letters[i];
            j++;
        }
        return String.valueOf(sliced);
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cracker = new CaesarCracker();
        for(int i = 0; i < klength; i++){
            String slice = sliceString(encrypted, i, klength);
            key[i] = cracker.getKey(slice);
        }
        return key;
    }

    public String breakVigenere() {
        String MaxDecryption = new String();
        FileResource resource = new FileResource("/home/dhrkp/javaCourseraCourse/src/com/zemoso/Course2/week4/messages/secretmessage4.txt");
        String message = resource.asString();
        HashSet<String> DictContent = new HashSet<String>();
        String [] labels = {"Danish","Dutch","English","French","German","Italian","Portuguese","Spanish"};
        for(String s : labels) {
            FileResource dictResource = new FileResource(Source+"/"+ s);
            DictContent = readDictionary(dictResource);
            languages.put(s,DictContent);
            System.out.println("Language: "+ s + "\t Total words in dictionary: "+ DictContent.size());
            System.out.println(" Most Common Character in "+ s + " is "+mostCommonCharIn(DictContent));
        }
        MaxDecryption = breakForAllLangs(message, languages);
        return MaxDecryption;
    }

    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> wordsInDictionary = new HashSet<String>();
        for(String word: fr.lines())
            wordsInDictionary.add(word.toLowerCase());
        return wordsInDictionary;
    }

    public int countWords(String message, HashSet<String> dictionary){
        int countValidWords = 0;
        String[] words = message.split("\\W+");
        for (String word : words){
            if(dictionary.contains(word.toLowerCase()))
                countValidWords++;
            else
                System.out.println(word);
        }
        return countValidWords;
    }

    public String breakForLanguage(String message, HashSet<String> dictionary){
        int maxCorrectWords = 0;
        String maxCorrectWordsString = null;
        for(int keyLength = 1; keyLength < message.length(); keyLength++){
            int[] keyFound = tryKeyLength(message, keyLength,'e');
            VigenereCipher vc = new VigenereCipher(keyFound);
            String decrypted = vc.decrypt(message);
            int wordCount = countWords(decrypted, dictionary);
            if(wordCount > maxCorrectWords){
                maxCorrectWords = wordCount;
                maxCorrectWordsString = decrypted;
            }
        }
        return maxCorrectWordsString;
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        frequencyOfLetters.clear();
        String alphabets = "abcdefghijklmnñopqrstuvwxyz";
        int[] myCounts = new int[27];
        byte[] alphabet = alphabets.getBytes(Charset.forName("UTF-8"));
        String alphabetUT8 = new String(alphabet, Charset.forName("UTF-8"));
        for (String s : dictionary) {
            int Wordlength = s.length();
            for (int i = 0; i < Wordlength; i++) {
                int dex = alphabetUT8.indexOf(Character.toLowerCase(s.charAt(i)));
                if (dex != -1) {
                    myCounts[dex] += 1;
                }
            }
        }
        int maxDex = 0;
        for(int k=0; k < myCounts.length; k++){
            if (myCounts[k] > myCounts[maxDex]) {
                maxDex = k;
            }
        }

        char mostCommon = alphabetUT8.charAt(maxDex);
        return mostCommon;
    }

    public String breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> MyMap) {
        String largestDecryption = null;
        int max = 0 ;
        String identifyLanguage = null;
        HashSet<String> h = new HashSet<String>();
        for(String l : MyMap.keySet()) {
            HashSet<String> readingResult = MyMap.get(l);
            int counts = countWords(encrypted,readingResult);
            if(counts > max){
                max = counts;
                largestDecryption = breakForLanguage(encrypted, readingResult);
                identifyLanguage = l;
            }

        }
        System.out.println(" identified language " + identifyLanguage);
        return largestDecryption;
    }
}
