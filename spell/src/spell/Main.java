package spell;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import spell.SpellCorrector.NoSimilarWordFoundException;

/**
 * A simple main class for running the spelling corrector
 */
public class Main {
	
	/**
	 * Give the dictionary file name as the first argument and the word to correct
	 * as the second argument.
	 */
	public static void main(String[] args) throws NoSimilarWordFoundException, IOException {
	    try{
                String dictionaryFileName = args[0];
                String inputWord = args[1];
                MyTrie trytry = new MyTrie();
                
                Scanner sc = new Scanner(new File(dictionaryFileName)).useDelimiter("\\s+\\d");
                while(sc.hasNext()){
                    trytry.add(sc.next());
                }
                //add words to Trie structure

                /**
                 * Create an instance of your corrector here
                 */
                SpellCorrector corrector = new MySpellCorrector();

                corrector.useDictionary(dictionaryFileName);
                String suggestion = corrector.suggestSimilarWord(inputWord);

                System.out.println("Suggestion is: " + suggestion);
            }
            catch(Exception e){
                System.out.println("USAGE 1: java SpellCorrector ");
            }
	}

}
