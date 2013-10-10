/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spell;

/**
 *
 * @author dallasnorton
 */
public class temp {
 
    
    public String[] deletions(String inputWord){
        String[] word = new String[inputWord.length()];
        for(int i = 0; i < word.length; i++){
            word[i] = inputWord.substring(0, i) + inputWord.substring(i+1);
        }
        return word;
    }
    
    public String[] transposistions(String inputWord){
        if(inputWord != ""){
            String[] word = new String[inputWord.length()-1];
            for(int i = 0; i < word.length; i++){
                word[i] = inputWord.substring(0, i) + inputWord.charAt(i+1) + inputWord.charAt(i) + inputWord.substring(i+2);
            }
            return word;        
        }
        else{
            return new String[0];
        }
    }
    
    public String[] alterations(String inputWord){
        String[] word = new String[inputWord.length() * 25];
        for(int i = 0; i < inputWord.length(); i++){
            int j = 0;
            String currentLetter = Character.toString(inputWord.charAt(i));
            for(letter l : letter.values()){
                String c = l.toString().toLowerCase();
                if(currentLetter.equals(c)){
                    continue;
                }
                word[i * 25 * j++] = inputWord.substring(0, i) + c + inputWord.substring(i+1);
            }
        }
        
        return word;
    }
    
    public String[] insertions(String inputWord){
        String[] word = new String[(inputWord.length()+1) * 26];
        for(int i = 0; i <= inputWord.length(); i++){
            int j = 0;
            for(letter l : letter.values()){
                String c = l.toString().toLowerCase();
                word[i * 26 * j++] = inputWord.substring(0, i) + c + inputWord.substring(i);
            }
        }
        
        return word;
    }
    
}
