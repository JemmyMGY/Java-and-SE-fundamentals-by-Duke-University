
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        mainKey = key;
    }
    
    public String encrypt(String input){
        StringBuilder result = new StringBuilder(input);
        for(int i=0; i < input.length(); i++){
            int charIndex  = alphabet.indexOf(Character.toUpperCase(input.charAt(i)));
            if(charIndex != -1){
                if(Character.isLowerCase(input.charAt(i)))
                    result.setCharAt(i, Character.toLowerCase(shiftedAlphabet.charAt(charIndex)));
                else
                    result.setCharAt(i, shiftedAlphabet.charAt(charIndex));
            }
        }
        return result.toString();
    }
    
        public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher(26-mainKey);
        return cc.encrypt(encrypted);
    }
    
}
