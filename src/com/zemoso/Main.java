package com.zemoso;

import com.zemoso.Course2.week2.CodonCount;
import com.zemoso.Course2.week2.WordFrequencies;
import com.zemoso.Course2.week2.charactersInPlay;
import com.zemoso.Course2.week3.Tester;
import com.zemoso.Course2.week4.CaesarCipher;
import com.zemoso.Course2.week4.CaesarCracker;
import com.zemoso.Course2.week4.VigenereBreaker;
import com.zemoso.Course2.week4.VigenereCipher;
import edu.duke.FileResource;

import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
//        week2();
//        week3();
//        week4SetupTest();
//        testTryKeyLength();
        testBreakVigenere();
//        testBreakForLanguage();
//        mostFrequentLetterTest();
//        testLanguageCommonLetters();
    }

    private static void testLanguageCommonLetters() {
        VigenereBreaker vb = new VigenereBreaker();
        HashSet<String> DictContent = new HashSet<String>();
        String [] labels = {"Danish","Dutch","English","French","German","Italian","Spanish","Portuguese"};
        for(String s : labels) {
            FileResource dictResource = new FileResource(vb.Source+"/"+ s);
            DictContent = vb.readDictionary(dictResource);
            vb.languages.put(s,DictContent);
            System.out.println("Language: "+ s + "\t Total words in dictionary: "+ DictContent.size());
            char commonChar = vb.mostCommonCharIn(DictContent);
            System.out.println(" MostCommonChar in "+ s + " " +commonChar);
        }
    }

    private static void mostFrequentLetterTest() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource("/home/dhrkp/javaCourseraCourse/src/com/zemoso/Course2/week4/dictionaries/Portuguese");
        HashSet<String> dict = vb.readDictionary(fr);
        vb.mostCommonCharIn(dict);
    }

    private static void testBreakForLanguage() {
        FileResource fr = new FileResource("/home/dhrkp/javaCourseraCourse/src/com/zemoso/Course2/week4/dictionaries/English");
        VigenereBreaker vb = new VigenereBreaker();
        HashSet<String> dict = (vb.readDictionary(fr));
        fr = new FileResource("/home/dhrkp/javaCourseraCourse/src/com/zemoso/Course2/week4/athens_keyflute.txt");
        String output = vb.breakForLanguage(fr.asString(), dict);
        System.out.println(output);
    }

    private static void testBreakVigenere() {
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println(vb.breakVigenere());
    }

    private static void testTryKeyLength() {
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println(vb.sliceString("abcdefghijklm", 2, 5));
        FileResource file = new FileResource("/home/dhrkp/javaCourseraCourse/src/com/zemoso/Course2/week4/athens_keyflute.txt");
        int keyReturn[];
        keyReturn = vb.tryKeyLength(file.asString(), 5, 'e');
        for (int i =0 ;i < keyReturn.length;i+=1)
            System.out.println("Return Keys "+ keyReturn[i] + "\t");
    }

    private static void week4SetupTest() {
        CaesarCipher cc = new CaesarCipher(1);
        FileResource file = new FileResource("/home/dhrkp/javaCourseraCourse/src/com/zemoso/Course2/week4/titus-small.txt");
        String encrypted = (cc.encrypt(file.asString()));
        System.out.println(cc.decrypt(encrypted));
        System.out.println(cc.encrypt("B"));
        CaesarCracker cracker = new CaesarCracker();
        file = new FileResource("/home/dhrkp/javaCourseraCourse/src/com/zemoso/Course2/week4/titus-small_key5.txt");
        System.out.println(cracker.getKey(file.asString()));
        int[] key = new int[]{17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(key);
        file = new FileResource("/home/dhrkp/javaCourseraCourse/src/com/zemoso/Course2/week4/titus-small.txt");
        encrypted = (vc.encrypt(file.asString()));
        System.out.println(encrypted);
    }

    private static void week3() {
        Tester week3 = new Tester();
        week3.testLogAnalyzer();
        week3.testCountUniqueIPsInRange();
        week3.testPrintAllHigherthanNum();
        week3.testUniqIP();
        week3.testUniqueIPVisitsOnDay();
        week3.testCounts();
        week3.testMostNumberVisitsByIP();
        week3.testIPsMostVisits();
        week3.testIPsForDays();
        week3.testDayWithMostIPVisits();
        week3.testIPsWithMostVisitsOnDay();
    }

    private static void week2() {
        WordFrequencies wf = new WordFrequencies();
        wf.tester();
        charactersInPlay cp = new charactersInPlay();
        cp.tester();
        CodonCount cc = new CodonCount();
        cc.test();
    }
}
