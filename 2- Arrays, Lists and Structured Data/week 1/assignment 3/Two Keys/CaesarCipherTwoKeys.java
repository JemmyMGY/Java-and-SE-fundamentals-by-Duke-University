
/**
 * Write a description of CaesarCipherTwoKeys here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwoKeys {
    
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int firstKey;
    private int secondKey;
    public CaesarCipherTwoKeys(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        System.out.println(shiftedAlphabet1 + "\n" + shiftedAlphabet2);
        firstKey = key1;
        secondKey = key2;
    }
    
    public String encrypt(String input){
        StringBuilder result = new StringBuilder(input);
        for(int i=0; i < input.length(); i++){
            int charIndex  = alphabet.indexOf(Character.toUpperCase(input.charAt(i)));
            if(charIndex != -1){
                if(i%2 == 0){
                    if(Character.isLowerCase(input.charAt(i)))
                        result.setCharAt(i, Character.toLowerCase(shiftedAlphabet1.charAt(charIndex)));
                    else
                        result.setCharAt(i, shiftedAlphabet1.charAt(charIndex));
                }
                else{
                    if(Character.isLowerCase(input.charAt(i)))
                        result.setCharAt(i, Character.toLowerCase(shiftedAlphabet2.charAt(charIndex)));
                    else
                        result.setCharAt(i, shiftedAlphabet2.charAt(charIndex));
                }
                
            }
        }
        return result.toString();
    }
    
    public String decrypt(String encrypted){
        CaesarCipherTwoKeys cc = new CaesarCipherTwoKeys(26-firstKey, 26-secondKey);
        return cc.encrypt(encrypted);
    }

}
