
package spell;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MySpellCorrector implements SpellCorrector{
    private MyTrie trie;
    
    public MySpellCorrector(){
        trie = new MyTrie();
    }
    
    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {
        Scanner sc = new Scanner(new File(dictionaryFileName));
        while(sc.hasNext()){
            trie.add(sc.next());
        }
        sc.close();
    }

    @Override
    public String suggestSimilarWord(String inputWord) throws NoSimilarWordFoundException {
        String suggest = "";
        if(trie.find(inputWord) != null){
            return inputWord;
        }

        Set<String> words = new HashSet();
        words = oneEditDistance(inputWord);
        suggest = checkIfWordIsInDictionary(words);
        if(suggest != null){
            return suggest;
        }
        
        Set<String> newWords = new HashSet();
        for(String str: words){
            for(String newWord: oneEditDistance(words)){
                newWords.add(newWord);
            }
        }
        suggest = checkIfWordIsInDictionary(newWords);
        if(suggest != null){
            return suggest;
        }
        
        throw new NoSimilarWordFoundException();
    }
    
    public Set<String> oneEditDistance(String inputWord){
        Set<String> words = new HashSet();
        
        for(String str: deletions(inputWord)){
            words.add(str);
        }
        for(String str: transpositions(inputWord)){
            words.add(str);
        }
        for(String str: alterations(inputWord)){
            words.add(str);
        }
        for(String str: insertions(inputWord)){
            words.add(str);
        }
        return words;
    }
    
    public Set<String> oneEditDistance(Set<String> set){
        Set<String> words = new HashSet();
        for(String str : set){
            if(str.equals("")){
                continue;
            }
            for(String s : oneEditDistance(str)){
                words.add(str);   
            }
        }
        return words;
    }
    
    public String[] deletions(String inputWord){
        String[] word = new String[inputWord.length()];
        for(int i = 0; i < word.length; i++){
            word[i] = inputWord.substring(0, i) + inputWord.substring(i+1);
        }
        return word;
    }
    public String[] transpositions(String inputWord){
        if(inputWord != ""){
            String[] word = new String[inputWord.length()-1];
            for(int i = 0; i < word.length; i++){
                word[i] = inputWord.substring(i, i) + inputWord.charAt(i+1) + inputWord.charAt(i) + inputWord.substring(i+2);
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
                if(currentLetter.equalsIgnoreCase(c)){
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
                word[i * 26 * j++] = inputWord.substring(0, i) + c + inputWord.substring(i);;
            }
        }        
        return word;
    }
        
    public String checkIfWordIsInDictionary(Set<String> inputWords){
        int currentCount = 0;
        String suggest =  null;
        MyTrie.MyNode node = null;
        for(String str: inputWords){
            node = trie.find(str);
            if(node != null){
                if(node.getValue() > currentCount){
                    suggest = str;
                    currentCount = node.getValue();
                }
                else if(node.getValue() == currentCount && suggest.compareTo(str) > 0){
                    suggest = str;
                    currentCount = node.getValue();
                }
            }
        }
        return suggest;
    }
}
