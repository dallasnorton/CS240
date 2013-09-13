package spell;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
