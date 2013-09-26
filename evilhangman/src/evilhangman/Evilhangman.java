package evilhangman;

import java.io.File;
import java.util.*;

public class Evilhangman {

    public static void main(String[] args) {
        int remainingGuesses = -1;
        int wordLength = -1;
        String filePath = null;
        Set<String> dictionary = new HashSet<String>();
        String tempDictionaryWord = null;
        try {
            filePath = args[0];
            wordLength = Integer.parseInt(args[1]);
            remainingGuesses = Integer.parseInt(args[2]);

            if (wordLength < 2 || remainingGuesses < 1 || filePath == null){
                return;
            }
            
            Scanner sc = new Scanner(new File(filePath)).useDelimiter("\\s+");
            while(sc.hasNext()){
                tempDictionaryWord = sc.next();
                if(tempDictionaryWord.length() == wordLength){
                    dictionary.add(tempDictionaryWord);
                }
            }
            
            System.out.println(dictionary.size());
            
        } catch (Exception e) {
            System.out.println("USAGE: java EvilHangman wordLength or guesses incorrect values");
            return;
        }
    }
}
