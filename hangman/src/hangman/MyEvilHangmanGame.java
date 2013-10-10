package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyEvilHangmanGame implements EvilHangmanGame{
    private Set<String> dictionary;
    private Map<String, Set<String>> wordMap;
    private usedLetters used;
    
    public MyEvilHangmanGame(){
        dictionary = new TreeSet();
        wordMap = new TreeMap();
        used = new usedLetters();
    }
    
    public usedLetters getUsedLetters(){
        return used;
    }
    
    @Override
    public void startGame(File dictionary, int wordLength) {
        try {
            String tempStr = new String();
            Scanner sc = new Scanner(dictionary);
            while(sc.hasNext()){
                tempStr = sc.next();
                if(tempStr.length() == wordLength){
                    this.dictionary.add(tempStr);
                }
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyEvilHangmanGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException {
        Set<String> set = new TreeSet();
        String key = "";
        if(used.contains(guess)){
            throw new GuessAlreadyMadeException();
        }
        
        used.add(guess);
        
        for(String str : dictionary){
            key = changeToDashedString(str, guess);
            wordMap.get(key);
            if(key == null){
                Set<String> tempSet = new TreeSet();
                tempSet.add(str);
                wordMap.put(key, tempSet);
            }
            else{
                set.add(str);
                wordMap.put(key, set);
            }
        }

        int biggest = 0;
        for(Map.Entry<String, Set<String>> entry : wordMap.entrySet()){
            if(entry.getValue().size() > biggest){
                biggest = entry.getValue().size();
                set = entry.getValue();
            }
        }
        dictionary = set;
        return set;
    }
    
    public String changeToDashedString(String word, char guess){
        StringBuilder sb = new StringBuilder();
        for(char c : word.toCharArray()){
            if(c == guess){
                sb.append(c);
            }
            else{
               sb.append("-"); 
            }
        }
        return sb.toString();
    }
    
    public class usedLetters{
        Set<Character> used = new HashSet<Character>();
        
        public void add(char c){
            used.add(c);
        }
        
        public boolean contains(char c){
            return used.contains(c);
        }
        
        @Override
        public String toString(){
            String str = "";
            Iterator<Character> iter = used.iterator();
            boolean firstChar = true;
            while(iter.hasNext()){
                if(!firstChar){
                    str += " ";
                }
                str += iter.next();
                firstChar = false;
            }
            return str;
        }
    }
}
