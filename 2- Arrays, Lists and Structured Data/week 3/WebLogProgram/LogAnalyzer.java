
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import java.io.File;
public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     public static String pathToData = "../UniqueIPData/";
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(pathToData+filename);
         for(String line : fr.lines()){
             records.add(WebLogParser.parseEntry(line));
         }
     }
     
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
        ArrayList<String> uniqueIP = new ArrayList<>();
        for(LogEntry le : records){
            String ip = le.getIpAddress(); 
            if(!uniqueIP.contains(ip)){
               uniqueIP.add(ip); 
            }
         }
        return uniqueIP.size();
     }
     
     public void printAllHigherThanNum(int num){
         
         for(LogEntry le : records){
             if(le.getStatusCode() > num){
                 System.out.println(le);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> uniqueVisitors = new ArrayList<>();
         for(LogEntry le : records){
            String dateString = le.getAccessTime().toString();
            int firstSpace = dateString.indexOf(" ");
            String monDay = dateString.substring(firstSpace+1, firstSpace+7);
            if(someday.equals(monDay)){
                String ipAddress = le.getIpAddress();
                if(!uniqueVisitors.contains(ipAddress)){
                    uniqueVisitors.add(ipAddress);
                }
            }
         }
         return uniqueVisitors;
     }
     
     public int countUniqueIPsInRange(int low, int high){
         int count = 0;
         ArrayList<String> uniqueIPs = new ArrayList<>(); 
         for(LogEntry le : records){
             int statusCode = le.getStatusCode();
             if(statusCode >= low && statusCode <= high){
                 String ipAddress = le.getIpAddress();
                 if(!uniqueIPs.contains(ipAddress)){
                     uniqueIPs.add(ipAddress);
                     count++;             
                 }
             }
         }
         return count;
     }
     
     public HashMap<String, Integer> countVisitsPerIP(){
         HashMap<String, Integer> countVisitor = new HashMap<>();
         for(LogEntry le : records){
             String ip = le.getIpAddress();
             if( countVisitor.containsKey(ip)){
                 countVisitor.put(ip, countVisitor.get(ip)+1);
             }
             else{
                 countVisitor.put(ip, 1);
             }
         }
         return countVisitor;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> countIP){
         int max =0;
         for(String ip : countIP.keySet()){
             if(countIP.get(ip) > max){
                 max = countIP.get(ip);
             }
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> countIP){
         int maxVisit = mostNumberVisitsByIP(countIP);
         ArrayList<String> ipsMost = new ArrayList<>();
         for(String ip : countIP.keySet()){
             if(countIP.get(ip) == maxVisit){
                 ipsMost.add(ip);
             }
         }
         return ipsMost;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays(){
         HashMap<String, ArrayList<String>> dayIPs = new HashMap<>();
         for(LogEntry le : records){
            String ip = le.getIpAddress();
            String dateString = le.getAccessTime().toString();
            int firstSpace = dateString.indexOf(" ");
            String monDay = dateString.substring(firstSpace+1, firstSpace+7);
            if(dayIPs.containsKey(monDay)){
                ArrayList<String> currList = dayIPs.get(monDay);
                currList.add(ip);
                dayIPs.put(monDay, currList);
            }
            else{
                ArrayList<String> newList = new ArrayList<>();
                newList.add(ip);
                dayIPs.put(monDay, newList);
            }
         }
         return dayIPs;
     }
     
     public String dayWithMostIPVisits (HashMap<String, ArrayList<String>> dayIPs){
         String maxVisitDay = "";
         int maxSize = 0;
         for(String day : dayIPs.keySet()){
             if(dayIPs.get(day).size() > maxSize){
                 maxSize = dayIPs.get(day).size();
                 maxVisitDay = day;
             }
         }
         return maxVisitDay;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dayIPs, String day){
         HashMap<String, Integer> ipCount = new HashMap<>();
         for(String ip : dayIPs.get(day)){
             if(ipCount.containsKey(ip)){
                 ipCount.put(ip, ipCount.get(ip)+1);
             }
             else{
                 ipCount.put(ip, 1);
             }
         }
         return iPsMostVisits(ipCount);
     }
}
