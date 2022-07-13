import edu.duke.*;
/**
 * Write a description of TestCaesarCipherTwoKeys here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipherTwoKeys {
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
    
    String halfOfString(String msg, int start){
        StringBuilder halfMsg = new StringBuilder();
        for(int i = start; i < msg.length(); i+=2){
            halfMsg.append(msg.charAt(i));
        }
        return halfMsg.toString();
    }
    
    int getKey(String s){
        int[] vals = countLetters(s);
        int maxDex = maxIndex(vals);
        int dKey = maxDex - 4;
        if(maxDex < 4)
            dKey = 26-(4-maxDex);
        return dKey;
    }
    
    String breakCaesarCipherTwoKeys(String input){
        String encrypted1 =  halfOfString(input, 0);
        String encrypted2 =  halfOfString(input, 1);
        int encryptKey1 = getKey(encrypted1);
        int encryptKey2 = getKey(encrypted2);
        CaesarCipherTwoKeys cc = new CaesarCipherTwoKeys(encryptKey1, encryptKey2);
        return cc.decrypt(input);
    }
    
    void simpleTests(){
        FileResource fr = new FileResource();
        String msg = fr.asString();
        CaesarCipherTwoKeys cc = new CaesarCipherTwoKeys(17,3);
        String encrypted = cc.encrypt(msg);
        System.out.println(encrypted);
        // String decrypted = breakCaesarCipherTwoKeys(encrypted);
        // System.out.println("Message: " + msg + 
        // "Encrypted: "+ encrypted + "Decrypted: " + decrypted);
    }
}
