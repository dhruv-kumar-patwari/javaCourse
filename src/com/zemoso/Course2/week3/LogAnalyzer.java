package com.zemoso.Course2.week3;

import java.util.*;

import com.zemoso.Course2.week3.LogEntry;
import com.zemoso.Course2.week3.WebLogParser;
import edu.duke.*;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;
    private ArrayList<Integer> myFreqs;
    private ArrayList<String> maxDate;
    private ArrayList<String> maxIPs;
    WebLogParser webLogParser = new WebLogParser();

    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
        maxDate = new ArrayList<String>();
        maxIPs = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
        
     public void readFile(String filename) {
         FileResource resource = new FileResource(filename);
         for(String line: resource.lines()){
             webLogParser.parseEntry(line);
             records.add(WebLogParser.parseEntry(line));
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }

    public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<String>();

        for(LogEntry le: records) {
            String ipAddr = le.getIpAddress();
            if(!uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int Num) {
        for(LogEntry le: records) {
            int statusCode = le.getStatusCode();
            if(statusCode > Num) {
                System.out.println("StatusCode greater than "+Num+": "+statusCode);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> myIPs = new ArrayList<String>();
        String myString = null;
        for(LogEntry le: records) {
            Date d = le.getAccessTime();
            String ipAddr = le.getIpAddress();
            myString = d.toString();
            int index = myIPs.indexOf(ipAddr);
            if((myString.contains(someday)) && (!myIPs.contains(ipAddr))){
                myIPs.add(ipAddr);
                myFreqs.add(1);
            }

            for (int k =0; k < myIPs.size();k++) {
                System.out.println(myIPs.get(k)+"\t");
            }
        }
        return myIPs;
    }


    public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for(LogEntry New: records) {
            int statusCode = New.getStatusCode();
            String ipAddr = New.getIpAddress();
            if((statusCode >= low) && (statusCode <= high)) {
                if(!uniqueIPs.contains(ipAddr)) {
                    uniqueIPs.add(ipAddr);
                }
            }
        }
        return uniqueIPs.size();
    }

    public HashMap<String,Integer> countVisitsPerIP() {
        HashMap<String,Integer> ipFrequencyCounter = new HashMap<String, Integer>();
        for (LogEntry le: records) {
            String ip = le.getIpAddress();
            if (!ipFrequencyCounter.containsKey(ip))
                ipFrequencyCounter.put(ip,1);
            else
                ipFrequencyCounter.put(ip,ipFrequencyCounter.get(ip) + 1);
        }
        return ipFrequencyCounter;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> addressNumberTime){
        ArrayList<String> maxIps = new ArrayList<String>();
        int greatest;
        greatest = mostNumberVisitsByIP(addressNumberTime);
        for (String s: addressNumberTime.keySet()) {
            if (addressNumberTime.get(s) == greatest)
                maxIps.add(s);
        }
        return maxIps;
    }

    public int mostNumberVisitsByIP(HashMap<String,Integer> myCounts){
        int max = 0;
        for (String s: myCounts.keySet()){
            int currentNum = myCounts.get(s);
            if (currentNum > max)
                max = currentNum;
        }
        return max;
    }

    public HashMap<String,ArrayList<String>> iPsForDays(){
        HashMap<String,ArrayList<String>> dayIpThatDay = new HashMap<>();
        ArrayList<String> myIPs;
        String myString;
        for (LogEntry le: records) {
            Date d = le.getAccessTime();
            myString = d.toString();
            myIPs = uniqueIPVisitsOnDay(myString);
            dayIpThatDay.put(myString,myIPs);
        }
        return dayIpThatDay;
    }

    public int findMax(){
        int theMax = myFreqs.get(0);
        int maxIndex = 0;
        for(int k=0; k < myFreqs.size(); k++){
            if (myFreqs.get(k) > theMax){
                theMax = myFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }

    public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> dayIPs){
        for (String s : dayIPs.keySet()) {
            int index = maxDate.indexOf(s);
            if (index == -1) {
                maxDate.add(s);
                myFreqs.add(1);
            }
            else {
                int freq = myFreqs.get(index);
                myFreqs.set(index,freq+1);
            }
        }

        int max = findMax();
        System.out.println("max Date: "+ maxDate.get(max)+" max Freq: "+ myFreqs.get(max));
        return maxDate.get(max);
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> dayAndIPs, String aDay){
        myFreqs.clear();
        ArrayList<String> mostIPs = new ArrayList<String>();
        mostIPs = uniqueIPVisitsOnDay(aDay);
        HashMap<String,Integer> counts = new HashMap<String,Integer>();

        for (int k=0;k<mostIPs.size();k++) {
            String s = mostIPs.get(k) ;
            if (!counts.containsKey(s)) {
                counts.put(s,1);
            }
            else {
                int freq = counts.get(s);
                counts.put(s,freq+1);
            }
        }
        return iPsMostVisits(counts);
    }
     
}
