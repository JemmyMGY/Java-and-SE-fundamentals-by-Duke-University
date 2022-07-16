import edu.duke.*;
import java.util.*;
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tester {
    public void testCaesarCipher(){
        FileResource fr = new FileResource();
        String msg = fr.asString();
        CaesarCipher cc = new CaesarCipher(2);
        String encrypted = cc.encrypt(msg);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Encrypted msg: " + encrypted 
        + "\nDecrypted msg: " + decrypted);
    }
    public void testCaesarCracker(){
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        CaesarCracker ck = new CaesarCracker('a');
        int encKey = ck.getKey(encrypted);
        System.out.println("encryption key is: " + encKey);
    }
    public void testVigenerCipher(){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String keyWord = "rome";
        int[] keys  = new int[keyWord.length()];
        for(int i =0;  i< keyWord.length(); i++){
            keys[i] = alphabet.indexOf(keyWord.charAt(i));
        }
        FileResource fr = new FileResource();
        String msg = fr.asString();
        VigenereCipher vc = new VigenereCipher(keys);
        String encrypted = vc.encrypt(msg);
        System.out.println("Original msg: " + msg 
        + "\nencrypted msg: "+ encrypted);
    }
    
    public void testStringSliceVB(){
        String msg = "abcdefghijklm";
        int sliceNumber = 1;
        int totalSlices = 3;
        VigenereBreaker vb = new VigenereBreaker();
        for(int i =3; i<6; i++){
            for(int j =0; j<i; j++){
                System.out.println("j: "+ j +" and i: "+ i 
                + " = "+vb.sliceString(msg, j, i));

            }
        }
       
    }
    public void testTryKeyLengthVB(){
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        char mostCommon = 'e';
        int klength = 38;
        VigenereBreaker vb = new VigenereBreaker();
        int[] keyArray = vb.tryKeyLength(encrypted, klength, mostCommon);
        System.out.println("Keys used are: ");
        for(int k : keyArray){
            System.out.print(k + ", " );
        }
    }
    
    public void testBreakVigenere(){
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }
    
    void testCountWordsVB(){
        VigenereBreaker vb = new VigenereBreaker();
        String dictLang = "English";
        FileResource frDict = new FileResource("./dictionaries/"
        + dictLang);
        HashSet<String> engDict = vb.readDictionary(frDict);
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        char mostCommon = 'e';
        int klength = 38;
        int[] encKeys = vb.tryKeyLength(encrypted, klength, mostCommon);
        VigenereCipher vc = new VigenereCipher(encKeys);
        String decrypted = vc.decrypt(encrypted);
        System.out.println("Valid words count is: " 
        + vb.countWords(decrypted, engDict));
    }
    
    void testMostCommonCharInVB(){
        VigenereBreaker vb = new VigenereBreaker();
        String dictLang = "Dutch";
        FileResource frDict = new FileResource("./dictionaries/"
        + dictLang);
        HashSet<String> engDict = vb.readDictionary(frDict);
        System.out.println("Most common char in " + dictLang 
        + " " + vb.mostCommonCharIn(engDict) );
    }
}
