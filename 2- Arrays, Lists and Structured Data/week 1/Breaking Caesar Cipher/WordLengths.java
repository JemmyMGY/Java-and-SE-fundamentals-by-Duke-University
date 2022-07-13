import edu.duke.*;
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {
    
    int[] countWordLengths(FileResource resource, int []counts){
        for(String word : resource.words()){
                int wordLength =0;
                for(int i =0; i <word.length(); i++){
                    if(Character.isLetter(word.charAt(i)))
                        wordLength++;
                }
                if(wordLength >= counts.length-1)
                    counts[counts.length-1]++;
                else
                    counts[wordLength]++;
            
        }
        return counts;
    }
    int indexOfMax(int[] values){
        int max = 0, maxIndex = -1;
        for(int i =0; i < values.length; i++){
            if(values[i] > max){
                max = values[i];
                maxIndex = i;
            }
            
        }
        return maxIndex;
    }
    void testCountWordLengths(){
        FileResource fr = new FileResource();
        int []counts = new int[31];
        counts = countWordLengths(fr, counts);
        
        System.out.println("Max Length of all words in the file: "
        + indexOfMax(counts));
    }
}
