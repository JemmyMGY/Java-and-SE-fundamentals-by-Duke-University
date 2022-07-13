import java.util.*;
import edu.duke.*;
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CodonCount {
    private HashMap<String, Integer> codonCount;
    CodonCount(){
        codonCount = new HashMap<String, Integer>();
    }
    
    void buildCodonMap(int start, String dna){
        codonCount.clear();
        for(int i=start; i < dna.length() - 2; i+=3){
            String temp = dna.substring(i, i+3);
            if(codonCount.containsKey(temp)){
                codonCount.put(temp, codonCount.get(temp)+1);
            }
            else{
                codonCount.put(temp, 1);
            }
        }
    }
    
    String getMostCommonCodon(){
        int maxVal=0;
        String mostCommonCodon="";
        for(String key : codonCount.keySet()){
            if(codonCount.get(key) > maxVal){
                maxVal = codonCount.get(key);
                mostCommonCodon = key;
            }
        }
        return mostCommonCodon;
    }
    void printCodonCount(int start, int end){
        for(String key : codonCount.keySet()){
            int value = codonCount.get(key);
            if(value >= start || value <=end){
                System.out.println(key +" => " + value);
            }
        }
    }
    void tester(){
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase().trim();
        buildCodonMap(0,dna);
        System.out.println("Reading frame starting with 0 results in "
        +codonCount.size() + " unique codons");
        System.out.println("Most common codon is: "
        + getMostCommonCodon() + " with count " + codonCount.get(getMostCommonCodon()));
        System.out.println("Counts of codons between 1 and 5 inclusive are:");
        printCodonCount(1, 5);
        
        buildCodonMap(1,dna);
        System.out.println("\nReading frame starting with 1 results in "
        +codonCount.size() + " unique codons");
        System.out.println("Most common codon is: "
        + getMostCommonCodon() + " with count " + codonCount.get(getMostCommonCodon()));
        System.out.println("Counts of codons between 1 and 5 inclusive are:");
        printCodonCount(1, 5);
        
        
        buildCodonMap(2,dna);
        System.out.println("\nReading frame starting with 2 results in "
        +codonCount.size() + " unique codons");
        System.out.println("Most common codon is: "
        + getMostCommonCodon() + " with count " + codonCount.get(getMostCommonCodon()));
        System.out.println("Counts of codons between 1 and 5 inclusive are:");
        printCodonCount(1, 5);
    }
}
