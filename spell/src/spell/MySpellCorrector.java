package spell;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MySpellCorrector implements SpellCorrector {

    private MyTrie tri = new MyTrie();
    
    public MySpellCorrector() {
        // create trie
        tri = new MyTrie();
    }
    
    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {
        try {        
            Scanner sc = new Scanner(new File(dictionaryFileName));
            while(sc.hasNext()){
                tri.add(sc.next());
                System.out.println(tri.toString());
            }
            sc.close();
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("USAGE 2: java SpellCorrector " + e);
        }
    }

    @Override
    public String suggestSimilarWord(String inputWord) throws NoSimilarWordFoundException {
        try {
            if(inputWord == ""){
                return null;
            }
            if(tri.find(inputWord) != null){
                return inputWord;
            }
            Set<String> words = oneEditDistance(inputWord);
            String suggest = checkAlteredWords(words);
            if(suggest != null){
                return suggest;
            }
            
            for (String word : words) {
                words = oneEditDistance(word);
            }
            suggest = checkAlteredWords(words);
            if(suggest != null){
                return suggest;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println("USAGE 2: java SpellCorrector " + e);            
        }
//                throw new NoSimilarWordFoundException;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String checkAlteredWords(Set<String> words) {
        for (String str : words) {
            if(tri.find(str) != null){
                return str;
            }
        }
        return null;
    }
    
    public Set<String> oneEditDistance(String inputWord) {
        Set<String> words = new HashSet<String>();

        for (String str : deletions(inputWord)){
            words.add(str);
        }
        for (String str : transpositions(inputWord)){
            words.add(str);
        }			
        for (String str : alterations(inputWord)){
            words.add(str);
        }
        for (String str : insertions(inputWord)){
            words.add(str);
        }			

        return words;
    }
    
    public String[] deletions(String inputWord) {
        String[] words = new String[inputWord.length()];
        for (int i = 0; i < words.length; i++) {
            words[i] = inputWord.substring(0, i) + inputWord.substring(i+1);
//            System.out.println(inputWord.substring(0, i));
//            System.out.println(inputWord.substring(i+1));
//            System.out.println("New Word");
        }
        return words;
    }

    public String[] transpositions(String inputWord) {
        String[] words = new String[inputWord.length() - 1];
        for (int i = 0; i < words.length; i++) {
            words[i] = inputWord.substring(0, i) + inputWord.charAt(i+1) + inputWord.charAt(i) + inputWord.substring(i+2);
        }
        return words;
    }

    public String[] alterations(String inputWord) {
        String[] words = new String[inputWord.length() * 25];
        for (int i = 0; i < inputWord.length(); i++) {
            String currentLetter = Character.toString(inputWord.charAt(i));
            int j = 0;
            for (letter l : letter.values()) {
                String c = l.toString().toLowerCase();
                if (currentLetter.equalsIgnoreCase(c)){
                    continue;
                }
                words[i * 25 + j++] = inputWord.substring(0, i) + c + inputWord.substring(i+1);
            }
        }
        return words;
    }

    public String[] insertions(String inputWord) {
        String[] words = new String[(inputWord.length() + 1) * 26];
        System.out.println(inputWord.length());
        for (int i = 0; i <= inputWord.length(); i++) {
            int j = 0;
            for (letter l : letter.values()) {
                String c = l.toString().toLowerCase();
                words[i * 26 + j++] = inputWord.substring(0, i) + c + inputWord.substring(i);
            }
        }
        return words;
    }
}
