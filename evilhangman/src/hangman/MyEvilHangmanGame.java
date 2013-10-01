package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyEvilHangmanGame implements EvilHangmanGame{

    private String tempDictionaryWord;
    private Set<String> dictionary;
    private UsedLetters usedLetters;
    private Map<String, Set<String>> wordMap;
    
    public MyEvilHangmanGame (){
        tempDictionaryWord = null;
        dictionary = new TreeSet<String>();
        usedLetters = new UsedLetters();
        wordMap = new TreeMap(); 
    }
    
    public UsedLetters getUsedLetters() {
        return usedLetters;
    }
    
    @Override
    public void startGame(File dictionaryFile, int wordLength) {
//        dictionary = null;
        Scanner sc;
        try {
            sc = new Scanner(dictionaryFile).useDelimiter("\\s+");
            while(sc.hasNext()){
                tempDictionaryWord = sc.next();
                if(tempDictionaryWord.length() == wordLength){
                    dictionary.add(tempDictionaryWord);
                }
            }
            sc.close();
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(MyEvilHangmanGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException {
        Set<String> bucket = new TreeSet<String>();
        wordMap = new TreeMap();
        if (usedLetters.contains(guess)) {
            throw new GuessAlreadyMadeException();
        }
        usedLetters.add(guess);
        
        String key = "";
        for(String str : dictionary){
            key = convertToDashedString(str, guess);
            bucket = wordMap.get(key);

            if(bucket == null){
                Set<String> tempSet = new TreeSet();
                tempSet.add(str);
                wordMap.put(key, tempSet);
            }
            else{            
                bucket.add(str);
                wordMap.put(key, bucket);
            }
        }
        
        int biggest = 0;
        for(Map.Entry<String, Set<String>> entry : wordMap.entrySet()){
            if(entry.getValue().size() > biggest){
                biggest = entry.getValue().size();
                bucket =  entry.getValue();
            }
        }
        dictionary = bucket;
        return bucket;
    }
    
    public String convertToDashedString(String str, char guess){
        StringBuilder dashStr = new StringBuilder();
        for(char c : str.toCharArray()){
            if(c == guess){
                dashStr.append(c);
            } 
            else {
                dashStr.append('-');
            }   
        }      
        return dashStr.toString();
    }
    
    class UsedLetters {
        Set<Character> used = new HashSet<Character>();

        public void add(char c) {
            used.add(c);
        }

        public boolean contains(char c) {
            return used.contains(c);
        }
            
        @Override
        public String toString() {
            String str = "";
            Iterator<Character> iter = used.iterator();
            boolean firstChar = true;
            while (iter.hasNext()) {
                if (!firstChar){ 
                    str += " ";
                }
                str += iter.next();
                firstChar = false;
            }
            return str;
        }
    }
    
}
