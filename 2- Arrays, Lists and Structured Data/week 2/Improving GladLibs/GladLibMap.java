import java.util.*;
import edu.duke.*;
import java.io.File;
/**
 * Write a description of GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> categoryUsed;
    private ArrayList<String> seenList;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        seenList = new ArrayList<>(); 
        categoryUsed = new ArrayList<>();
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<>();
        initializeFromSource(source);
        myRandom = new Random();
        seenList = new ArrayList<String>();
        categoryUsed = new ArrayList<>();
    }
    
    private void initializeFromSource(String source) {
        File directoryPath = new File(source);
        String[] filesName  =  directoryPath.list();
        for(String fileName : filesName){
            ArrayList<String> tempList = readIt(source+"/"+fileName);
            String s = fileName.substring(0, fileName.indexOf('.'));
            myMap.put(s, tempList);
        }   
    }
    
    private String randomFrom(ArrayList<String> source){
            int index = myRandom.nextInt(source.size());
            return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if(myMap.containsKey(label)){ 
            if(!categoryUsed.contains(label))
                categoryUsed.add(label);
            return randomFrom(myMap.get(label));
        }
        
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        if(seenList.contains(sub)){
            do{
                sub = getSubstitute(w.substring(first+1,last)); 
            }
            while(seenList.contains(sub));
        }
        seenList.add(sub);
        
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public int totalWordsInMap(){
        int totalNum=0;
        for(String key : myMap.keySet()){
            totalNum += myMap.get(key).size();
        }
        return totalNum;
    }
    
    public int totalWordsConsidered(){
        int totalNum = 0;
        for(String category : categoryUsed){
            totalNum += myMap.get(category).size();
        }
        return totalNum;
    }
    
    public void makeStory(){
        String story = fromTemplate("examples/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\nTotal used words are: " + seenList.size() );
        System.out.println("Total possible words to be used from all lists are : "
        +  totalWordsInMap());
        System.out.println("Total considered words are: " 
        + totalWordsConsidered() );
    }
}
