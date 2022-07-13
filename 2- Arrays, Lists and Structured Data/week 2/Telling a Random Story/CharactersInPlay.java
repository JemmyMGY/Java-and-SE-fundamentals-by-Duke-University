import java.util.*;
import edu.duke.*;
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay {
    private ArrayList<String> chars;
    private ArrayList<Integer> freqs;
    CharactersInPlay(){
        chars = new ArrayList<String>();
        freqs = new ArrayList<Integer>();
    }
    void update(String person){
        String personLower = person.toLowerCase();
        int personIndex = chars.indexOf(personLower);
        if(personIndex == -1){
            chars.add(personLower);
            freqs.add(1);
        }
        else{
            int value = freqs.get(personIndex);
            freqs.set(personIndex, value+1);
        }
    }
    void findAllCharacters(){
        FileResource fr = new FileResource();
        chars.clear();
        freqs.clear();
        for(String line : fr.lines()){
            int dotIndex = line.indexOf('.');
            if(dotIndex != -1){
                String charName = line.substring(0,dotIndex);
                update(charName);
            }
        }
    }
    
    int findIndexOfMax(){
        int maxIndex=0;
        for(int i=0; i < freqs.size(); i++){
            if(freqs.get(i) > freqs.get(maxIndex)){
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    void charactersWithNumParts(int num1, int num2){
        for(int i=0; i < chars.size(); i++){
            if(freqs.get(i) >= num1 && freqs.get(i)<= num2)
                System.out.println(chars.get(i)+" => "+
                freqs.get(i));
        }
    }
    void tester(){
        findAllCharacters();
        int maxIndex = findIndexOfMax();
        System.out.println("Most frequent Character: "+ 
        chars.get(maxIndex) + "=>" + freqs.get(maxIndex));
        charactersWithNumParts(20,200);
        
    }
}
