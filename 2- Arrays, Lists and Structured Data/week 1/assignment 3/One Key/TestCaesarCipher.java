import edu.duke.*;
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipher {
    int[] countLetters (String encrypted){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] freqs = new int[alphabet.length()];
        for(int i=0; i < encrypted.length(); i++){
            int index = alphabet.indexOf(Character.toLowerCase(encrypted.charAt(i)));
            if(index != -1)
                freqs[index]++;
        }
        
        return freqs;
    }
    
    int maxIndex(int[] vals){
        int maxIndex=0;
        for(int i=0; i < vals.length; i++){
            if(vals[i] > vals[maxIndex]){
                maxIndex =i;
            }
        }
        return maxIndex;
    }
    
     int getKey(String s){
        int[] vals = countLetters(s);
        int maxDex = maxIndex(vals);
        int dKey = maxDex - 4;
        if(maxDex < 4)
            dKey = 26-(4-maxDex);
        return dKey;
    }
    
    String breakCaesarCipher(String input){
        int encryptKey = getKey(input);
        CaesarCipher cc = new CaesarCipher(encryptKey);
        return cc.decrypt(input);
    }
    
    void simpleTests(){
        FileResource fr = new FileResource();
        String msg = fr.asString();
        CaesarCipher cc = new CaesarCipher(18);
        String encrypted = cc.encrypt(msg);
        String decrypted = breakCaesarCipher(encrypted);
        System.out.println("Message: " + msg + 
        "Encrypted: "+ encrypted + "Decrypted: " + decrypted);
    }
}