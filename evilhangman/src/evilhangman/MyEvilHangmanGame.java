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
        
        bucket = setNewDictionary(guess);
        
        return bucket;
    }
    
    public Set<String> setNewDictionary(char guess){
        Set tempSet = wordMap.entrySet();
        Map.Entry<String, Set<String>> largestEntry = null;                
        for(Iterator i = tempSet.iterator();i.hasNext();){
            largestEntry = (Map.Entry)i.next();
        }
                
        for(Map.Entry<String, Set<String>> entry : wordMap.entrySet()){
            if(entry.getValue().size() > largestEntry.getValue().size()){
                largestEntry = entry;
                continue;
            }
            if(entry.getValue().size() == largestEntry.getValue().size()){
                String largeStr = largestEntry.getKey();
                String entryStr = entry.getKey();
                
                if(largeStr.contains(Character.toString(guess)) && !entryStr.contains(Character.toString(guess))){
                    largestEntry = entry;
                    continue;
                }
                else{
                    if(largeStr.contains(Character.toString(guess)) && entryStr.contains(Character.toString(guess))){
                        //next check
                        int largeStrCount = getCountForNumbersOfTimesCharsInWord(largeStr, guess);
                        int entryStrCount = getCountForNumbersOfTimesCharsInWord(entryStr, guess);
                        if(largeStrCount > entryStrCount){
                            largestEntry = entry;
                        }
                        else{
                            if(largeStrCount == entryStrCount){
                                //next check - get rightmost guess letter
                                int largeRightIndex = getRightMostIndex(largeStr, guess);
                                int entryRightIndex = getRightMostIndex(entryStr, guess);
                                if(entryRightIndex > largeRightIndex){
                                    largestEntry = entry;
                                    continue;
                                }
                                else{
                                    if(entryRightIndex == largeRightIndex){
                                        //next check - get next rightmost guess letter
                                        int nextLargeRightIndex = getNextRightMostIndex(largeStr, guess, largeRightIndex);
                                        int nextEntryRightIndex = getNextRightMostIndex(entryStr, guess, entryRightIndex);
                                        if(nextEntryRightIndex > nextLargeRightIndex){
                                            largestEntry = entry;
                                            continue;
                                        }
                                        else if(nextLargeRightIndex > nextEntryRightIndex){
                                            continue;
                                        }
                                        else{
                                            keepChecking(largeStr, entryStr, largeRightIndex, entryRightIndex, nextLargeRightIndex, nextEntryRightIndex, guess);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return largestEntry.getValue();
    }
    
    public void keepChecking(String largeStr, String entryStr, int largeRightIndex, int entryRightIndex,int nextLargeRightIndex, int nextEntryRightIndex, char guess){
        largeRightIndex = nextLargeRightIndex;
        entryRightIndex = nextEntryRightIndex;
        nextLargeRightIndex = getNextRightMostIndex(largeStr, guess, largeRightIndex);
        nextEntryRightIndex = getNextRightMostIndex(entryStr, guess, entryRightIndex);
        
        if(true){
            
        }
        else{
            keepChecking(largeStr, entryStr, largeRightIndex, entryRightIndex, nextLargeRightIndex, nextEntryRightIndex, guess);
        }
    }
    
    public int getNextRightMostIndex(String str, char guess, int index){
        char[] ary = str.toCharArray();
        int rightIndex = -1;
        for(int i = ary.length; i > 0; i--){
            if(ary[i] == guess && rightIndex > -1 && i != index){
                rightIndex = i;
                break;
            }
        }
        return rightIndex;
    }
    public int getRightMostIndex(String str, char guess){
        char[] ary = str.toCharArray();
        int rightIndex = -1;
        for(int i = ary.length; i > 0; i--){
            if(ary[i] == guess && rightIndex > -1){
                rightIndex = i;
                break;
            }
        }
        return rightIndex;
    }
    
    public int getCountForNumbersOfTimesCharsInWord(String str, char guess){
        int count = 0;
        for(char c : str.toCharArray()){
            if(c == guess){
                count++;
            } 
        } 
        return count;
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
