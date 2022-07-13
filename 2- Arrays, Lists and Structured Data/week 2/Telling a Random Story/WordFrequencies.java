import java.util.*;
import edu.duke.*;
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for(String word : fr.words()){
            String lowerWord = word.toLowerCase();
            int wordIndex = myWords.indexOf(lowerWord); 
            if(wordIndex == -1){
                myWords.add(lowerWord);
                myFreqs.add(1);
            }
            else{
               int value = myFreqs.get(wordIndex);
               myFreqs.set(wordIndex, value+1);
            }
        }
    }
    
    int findIndexOfMax(){
        int maxIndex=0;
        for(int i=0; i < myFreqs.size(); i++){
            if(myFreqs.get(i) > myFreqs.get(maxIndex)){
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    void tester(){
        findUnique();
        int maxIndex = findIndexOfMax();
        System.out.println("# of Unique words: "+ myWords.size() 
        + "\nMost frequent word: " 
        + myWords.get(maxIndex) + "=>" + myFreqs.get(maxIndex));
        // for(int i =0; i < myWords.size(); i++){
            // System.out.println(myWords.get(i) + " => " + myFreqs.get(i));
        // }
    }
}
