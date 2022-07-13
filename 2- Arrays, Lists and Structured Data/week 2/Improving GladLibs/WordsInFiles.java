import java.util.*;
import edu.duke.*;
import java.io.File;
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordsMap;
    
    WordsInFiles(){
        wordsMap = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f){
            FileResource fr = new FileResource(f);
            for(String word : fr.words()){
                if(wordsMap.containsKey(word)){
                    ArrayList currList = wordsMap.get(word);
                    if(!currList.contains(f.getName()))
                        currList.add(f.getName());
                }
                else{
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(f.getName());
                    wordsMap.put(word, temp);
                }
            }
    }
    void buildWordFileMap(){
        wordsMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){ 
            addWordsFromFile(f);
        }
    }
    int maxNumber(){
        int max = 0;
        for(String key : wordsMap.keySet()){
            if(wordsMap.get(key).size() > max){
                max = wordsMap.get(key).size();
            }
        }
        return max;
    }
    ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> wordsList = new ArrayList<>();
        for(String key : wordsMap.keySet()){
            if(wordsMap.get(key).size() == number)
                wordsList.add(key);
        }
        return wordsList;
    }
    void printFilesIn(String word){
        for(String fileName : wordsMap.get(word)){
            System.out.println(fileName);
        }
    }
    void tester(){
        buildWordFileMap();
        // System.out.println("Max # of files is "+ maxNumber() 
         // + " for words: ");
        // ArrayList<String> myList = wordsInNumFiles(4);
        // System.out.println(myList.size());
        //System.out.println(wordsMap.size());
        printFilesIn("tree");
    }
}
