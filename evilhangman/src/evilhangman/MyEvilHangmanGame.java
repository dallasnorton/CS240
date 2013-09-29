package evilhangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyEvilHangmanGame implements EvilHangmanGame{

    String tempDictionaryWord = null;
    Set<String> dictionary = null;
    UsedLetters usedLetters = null;
    Map<String, Set<String>> wordMap = null;
    
    public MyEvilHangmanGame (){
        tempDictionaryWord = null;
        dictionary = new HashSet<String>();
        usedLetters = new UsedLetters();
        wordMap = new HashMap(); 
    }
    
    @Override
    public void startGame(File dictionaryFile, int wordLength) {
        dictionary = null;
        Scanner sc;
        try {
            sc = new Scanner(dictionaryFile).useDelimiter("\\s+");
            while(sc.hasNext()){
                tempDictionaryWord = sc.next();
                if(tempDictionaryWord.length() == wordLength){
                    dictionary.add(tempDictionaryWord);
                }
            }
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(MyEvilHangmanGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException {
        Set<String> bucket = new HashSet();
        if (usedLetters.contains(guess)) {
//            throw(GuessAlreadyMadeException);
            System.out.println("You already used that letter. Please try again.\n");
        }
        usedLetters.add(guess);
        
        String key = null;
        for(String str : dictionary){
            key = convertToDashedString(str, guess);
            
            bucket = wordMap.get(key);
            bucket.add(str);
            wordMap.put(key, bucket);
        }
        
        setNewDictionary();
        
        return bucket;
    }
    
    public void setNewDictionary(){
        Set<String> newDictionary = new HashSet<String>();
        for (Map.Entry<String, Set<String>> entry : wordMap.entrySet())
        {
            newDictionary = entry.getValue();
            if(newDictionary.size() > dictionary.size()){
                dictionary = newDictionary;
            }
//            System.out.println(entry.getKey() + "/" + entry.getValue());
        }
    
//        dictionary = newDictionary;
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
