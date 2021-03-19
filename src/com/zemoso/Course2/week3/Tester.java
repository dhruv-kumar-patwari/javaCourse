package com.zemoso.Course2.week3;
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    private ArrayList<String> maximumIPs;
    private ArrayList<String> mostAccessesDay;

    public Tester(){
        maximumIPs = new ArrayList<String>();
        mostAccessesDay = new ArrayList<String>();
    }

    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("/home/dhrkp/javaCourseraCourse/src/com/zemoso/Course2/week3/short-test_log");
        logAnalyzer.printAll();
    }

    public void testUniqIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("/home/dhrkp/javaCourseraCourse/src/com/zemoso/Course2/week3/short-test_log");
        int uniqueIPS = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPS + " IPs");
    }

    public void testPrintAllHigherthanNum() {
        LogAnalyzer MyLogAnalyser = new LogAnalyzer();
        MyLogAnalyser.readFile("/home/dhrkp/javaCourseraCourse/src/com/zemoso/Course2/week3/weblog1_log");
        MyLogAnalyser.printAllHigherThanNum(400);
    }

    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer MyAnalyser = new LogAnalyzer();
        MyAnalyser.readFile("/home/dhrkp/javaCourseraCourse/src/com/zemoso/Course2/week3/weblog1_log");
        MyAnalyser.uniqueIPVisitsOnDay("Mar 24");

    }

    public void testCountUniqueIPsInRange() {
        LogAnalyzer Analyser = new LogAnalyzer();
        Analyser.readFile("/home/dhrkp/javaCourseraCourse/src/com/zemoso/Course2/week3/weblog1_log");
        int countinRange_first = Analyser.countUniqueIPsInRange(200, 299);
        System.out.println("There are " + countinRange_first + " IPs at the beginning.");
    }

    public HashMap<String,Integer> testCounts() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("/home/dhrkp/javaCourseraCourse/src/com/zemoso/Course2/week3/weblog3-short_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
        return counts;
    }

    public void testMostNumberVisitsByIP() {
        LogAnalyzer le = new LogAnalyzer();
        le.readFile("/home/dhrkp/javaCourseraCourse/src/com/zemoso/Course2/week3/weblog2_log");
        int max_value = le.mostNumberVisitsByIP(testCounts());
        System.out.println("max value in the HashMap "+ max_value);
    }

    public void testIPsMostVisits() {
        LogAnalyzer Sol = new LogAnalyzer();
        Sol.readFile("/home/dhrkp/javaCourseraCourse/src/com/zemoso/Course2/week3/weblog2_log");
        HashMap<String,Integer> myCounts = Sol.countVisitsPerIP();
        maximumIPs = Sol.iPsMostVisits(myCounts);
        for (int k=0;k<maximumIPs.size();k++) {
            System.out.println(" IP addresses that all have the maximum number of visits "+ maximumIPs.get(k));
        }
    }

    public void testIPsForDays() {
        LogAnalyzer LA = new LogAnalyzer();
        LA.readFile("/home/dhrkp/javaCourseraCourse/src/com/zemoso/Course2/week3/weblog3-short_log");
        HashMap<String,ArrayList<String>> mapDayIPAddresses = LA.iPsForDays();
        for (String s: mapDayIPAddresses.keySet()) {
            System.out.println(s+" maps to"+"\t"+mapDayIPAddresses.get(s));
        }
    }

    public void testDayWithMostIPVisits() {
        String dayMostIP;
        LogAnalyzer LogA = new LogAnalyzer();
        LogA.readFile("/home/dhrkp/javaCourseraCourse/src/com/zemoso/Course2/week3/weblog2_log");
        HashMap<String,ArrayList<String>> mapDayAndIP = LogA.iPsForDays();
        dayMostIP = LogA.dayWithMostIPVisits(mapDayAndIP);
        System.out.println("The day that has the most IP address"+ dayMostIP);
    }

    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer myLog = new LogAnalyzer();
        myLog.readFile("/home/dhrkp/javaCourseraCourse/src/com/zemoso/Course2/week3/weblog2_log");
        HashMap<String,ArrayList<String>> day_and_ipaddress = myLog.iPsForDays();
        mostAccessesDay = myLog.iPsWithMostVisitsOnDay(day_and_ipaddress,"Sep 29");
        for (int k=0;k<mostAccessesDay.size();k++) {
            System.out.println(" IP addresses that all have the maximum number of visits on Sep 29 "
                    + mostAccessesDay.get(k));
        }
    }

}
