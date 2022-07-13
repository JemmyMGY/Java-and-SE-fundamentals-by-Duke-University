
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    
    boolean isVowel(char c){
        return c == 'a' || c == 'e' || c == 'i' 
        || c == 'o' || c == 'u' ? true : false;  
    }
    
    StringBuilder replaceVowels(String s, char c){
        StringBuilder result = new StringBuilder(s);
        for(int i =0; i < result.length(); i++){
            if( isVowel( Character.toLowerCase(result.charAt(i)) )){
                result.setCharAt(i, c);
            }
        }
        return result;
    }
    
    StringBuilder emphasize(String s, char c){
        StringBuilder result = new StringBuilder(s);
        for(int i =0; i < result.length(); i++){
            if( Character.toLowerCase(result.charAt(i) ) == c){
                if(i%2 == 0)
                    result.setCharAt(i,'*');
                else
                    result.setCharAt(i,'+');
            }
        }
        return result;
    }
}
