import java.util.*;
import edu.duke.*;
import java.io.File;
public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder slice = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i+=totalSlices){
            slice.append(message.charAt(i));
        }
        return slice.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        for(int i =0; i < klength; i++){
            String encSlice = sliceString(encrypted, i, klength);
            CaesarCracker cb = new CaesarCracker(mostCommon);
            key[i] = cb.getKey(encSlice); 
        }
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource msgFR = new FileResource();
        String encrypted = msgFR.asString();
        File langsFolder = new File("./dictionaries/");
        File[] filesList = langsFolder.listFiles();
        HashMap<String, HashSet<String>> langsDict = new HashMap<>();
        for(File f : filesList){
            String lang = f.getName();
            FileResource fr = new FileResource(f);
            HashSet<String> dict = readDictionary(fr);
            langsDict.put(lang, dict);
        }
        
        breakForAllLangs(encrypted, langsDict);
        
    }
    
    public HashSet<String> readDictionary (FileResource fr){
        HashSet<String> dict = new HashSet<>();
        for(String s : fr.lines()){
            dict.add(s.toLowerCase());
        }
        return dict;
    }
    
    public int countWords(String msg, HashSet<String> dict){
        int validWord = 0;
        for(String s : msg.split("\\W+")){
            if(dict.contains(s.toLowerCase()))
                validWord++;
        }
        return validWord;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dict){
        char mostCommon = mostCommonCharIn(dict);
        int validWordsCount = 0;
        String decFinal = "";
        int[] finalKeys = {};
        for(int keyLen=1; keyLen <= 100; keyLen++){
            int[] encKeys = tryKeyLength(encrypted, keyLen, mostCommon);
            VigenereCipher vc = new VigenereCipher(encKeys);
            String decrypted = vc.decrypt(encrypted);
            if(countWords(decrypted, dict) > validWordsCount){
                decFinal = decrypted;
                validWordsCount = countWords(decrypted, dict);
                finalKeys = encKeys;
            }
        }
        // System.out.println("Total valid words: " + validWordsCount
        // + "\nencryption Keys size: " + finalKeys.length);
        // for(int k : finalKeys)
            // System.out.print(k + ", ");
        return decFinal;
    }
    
    public char mostCommonCharIn(HashSet<String> dict){
        HashMap<Character, Integer> charCount = new HashMap<>();
        for(String word : dict){
            for(int i=0; i <word.length(); i++){
                if(charCount.containsKey(word.charAt(i))){
                    charCount.put(word.charAt(i),
                    charCount.get(word.charAt(i))+1);
                }
                else{
                    charCount.put(word.charAt(i), 1);
                }
            }
        }
        int maxCount = 0;
        char mostCommonChar = 0;
        for(char c : charCount.keySet()){
            if(charCount.get(c) > maxCount){
                mostCommonChar = c;
                maxCount= charCount.get(c);
            }
        }
        return mostCommonChar;
    }
    
    public void breakForAllLangs(String encrypted, 
    HashMap<String, HashSet<String>> langsDict){
        int finalValidCount =0;
        String detectedLang ="None";
        String finalDecrypted = "";
        for(String lang : langsDict.keySet()){
            String decrypted = breakForLanguage(encrypted, langsDict.get(lang));
            int validCount = countWords(decrypted, langsDict.get(lang));
            if( validCount > finalValidCount){
                finalValidCount = validCount;
                detectedLang = lang;
                finalDecrypted = decrypted;
            }
        }
        
        System.out.println("Detected Language: " + detectedLang + "\nTotal Valid words count: " + finalValidCount  + "\nDecrypted Msg: " + finalDecrypted);
        
    }
}
