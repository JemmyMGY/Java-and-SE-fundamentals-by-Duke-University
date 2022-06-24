import edu.duke.*;
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarBreaker {
    String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int dKey = getKey(encrypted);
        System.out.println("Key: "+ dKey);
        return cc.encrypt(encrypted, 26-dKey);
    }
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
    String decryptTwoKeys(String encrypted){
        String msg1 = halfOfString(encrypted, 0);
        String msg2 = halfOfString(encrypted,1);
        int key1 = getKey(msg1);
        int key2 = getKey(msg2);
        System.out.println("Key 1: "+ key1+ "\n" + "Key 2: " + key2 +"\n");
        CaesarCipher cc = new CaesarCipher();
        return cc.encryptTwoKeys(encrypted, 26-key1, 26-key2);
    }
    
    void testDecrypt(){
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        String decrypted = decrypt(encrypted);
        int key = getKey(encrypted);
        System.out.println("Encrypted: " + encrypted + "Key is: " + key + 
        "\nDecrypted: " + decrypted);
    }
    void testDecryptTwoKeys(){
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        String decrypted = decryptTwoKeys(encrypted);
        System.out.println("Encrypted: " + encrypted + 
        "\nDecrypted: " + decrypted);
    }
    void decryptSpecificKeys(String encrypted, int key1, int key2){
        String msg1 = halfOfString(encrypted, 0);
        String msg2 = halfOfString(encrypted,1);
        CaesarCipher cc = new CaesarCipher();
        System.out.println(cc.encryptTwoKeys(encrypted, 26-key1, 26-key2));
    }
}
