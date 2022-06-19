import edu.duke.*;
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    String encrypt(String input, int key){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String encrypted = alphabet.substring(key) + alphabet.substring(0,key);
        StringBuilder result = new StringBuilder(input);
        for(int i=0; i < input.length(); i++){
            int charIndex  = alphabet.indexOf(Character.toUpperCase(input.charAt(i)));
            if(charIndex != -1){
                if(Character.isLowerCase(input.charAt(i)))
                    result.setCharAt(i, Character.toLowerCase(encrypted.charAt(charIndex)));
                else
                    result.setCharAt(i, encrypted.charAt(charIndex));
            }
        }
        return result.toString();
    }
    
    String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        
        for(int i = 0; i < input.length(); i++){
            if(i%2 == 0)
                s1.append(input.charAt(i));
            else    
                s2.append(input.charAt(i));
        }
        String encrypted1 = encrypt(s1.toString(), key1);
        String encrypted2 = encrypt(s2.toString(), key2);
        StringBuilder result = new StringBuilder(input);
        int j=0, k=0;
        for(int i =0; i < input.length(); i++ ){
            if(i %2 ==0)
                result.setCharAt(i, encrypted1.charAt(j++));
            else
                result.setCharAt(i, encrypted2.charAt(k++));
            
        }
        return result.toString();
    }
    void testCaesar(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 15;
        String encryptedMsg = encrypt(message, key);
        System.out.println("Msg is: " + message +"Key is: "+ key + "\n" 
        + "encrypted Msg is: " + encryptedMsg + "\n");
    }
    void testTwoKeysCaesar(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key1 = 8, key2 = 21;
        String encryptedMsg = encryptTwoKeys(message, key1, key2);
        System.out.println("Msg is: " + message +"Keys are: " + key1+ ","+key2 + "\n" 
        + "encrypted Msg is: " + encryptedMsg + "\n");
    }
}
