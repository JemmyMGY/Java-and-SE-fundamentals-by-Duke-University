
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer logAnz = new LogAnalyzer();
        logAnz.readFile("short-test_log");
        logAnz.printAll();
    }
    
    public void testCountUniqueIPs(){
        LogAnalyzer logAnz = new LogAnalyzer();
        logAnz.readFile("weblog2_log");
        System.out.println("Unique IPs count : "
        + logAnz.countUniqueIPs());
    }
    
    public void testPrintAllHigherThanNum(){
        LogAnalyzer logAnz = new LogAnalyzer();
        logAnz.readFile("weblog1_log");
        int num = 400;
        System.out.println
        ("printing all records that its status code higher than: "
        + num);
        logAnz.printAllHigherThanNum(num);
    }
    
    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer logAnz = new LogAnalyzer();
        logAnz.readFile("weblog2_log");
        String day = "Sep 27";
        ArrayList<String> uniqueIPs =logAnz.uniqueIPVisitsOnDay(day);
        System.out.println("Unique IPs visitors: " + uniqueIPs.size());
        for(String visitor : uniqueIPs){
            System.out.println(visitor);
        }
    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer logAnz = new LogAnalyzer();
        logAnz.readFile("weblog2_log");
        int low = 400, high = 499;
        System.out.println("Number of Unique IPs for Status codes between " 
        + low + " And " +  high + " Are: " + logAnz.countUniqueIPsInRange(low, high));
    }
    
    public void testCountVisitsPerIP(){
        LogAnalyzer logAnz = new LogAnalyzer();
        logAnz.readFile("weblog3-short_log");
        HashMap<String, Integer> countIP = logAnz.countVisitsPerIP();
        System.out.println("Unique Visitors count: " + countIP.size());
        for(String ip : countIP.keySet()){
            System.out.println(ip + ": "+ countIP.get(ip));
        }
    }
    
    public void testMostNumberVisitsByIP(){
        LogAnalyzer logAnz = new LogAnalyzer();
        logAnz.readFile("weblog2_log");
        HashMap<String, Integer> countIP = logAnz.countVisitsPerIP();
        System.out.println("Most Number Visits: " 
        + logAnz.mostNumberVisitsByIP(countIP));
    }
    
    public void testIPsMostVisits(){
        LogAnalyzer logAnz = new LogAnalyzer();
        logAnz.readFile("weblog2_log");
        HashMap<String, Integer> countIP = logAnz.countVisitsPerIP();
        System.out.println("Most IP Visits: ");
        for(String ip : logAnz.iPsMostVisits(countIP)){
            System.out.println(ip);
        }
    }
    
    public void testIPsForDays(){
        LogAnalyzer logAnz = new LogAnalyzer();
        logAnz.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> dayIPs = logAnz.iPsForDays();
        for(String day : dayIPs.keySet()){
            System.out.println("visitors of " + day + " :" );
            for(String ip : dayIPs.get(day)){
                System.out.println(ip);
            }
            System.out.println();
        }
    }
    
    public void testDayWithMostIPVisits(){
        LogAnalyzer logAnz = new LogAnalyzer();
        logAnz.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> dayIPs = logAnz.iPsForDays();
        System.out.println("Day with most visits: "
        + logAnz.dayWithMostIPVisits(dayIPs));
    }
    
    public void testIPsWithMostVisitsOnDay(){
        LogAnalyzer logAnz = new LogAnalyzer();
        logAnz.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> dayIPs = logAnz.iPsForDays();
        String day = "Sep 30";
        System.out.println("Most visits IPs in " + day + " are: ");
        for(String ip : logAnz.iPsWithMostVisitsOnDay(dayIPs, day)){
            System.out.println(ip);
        }
        
    }
}
