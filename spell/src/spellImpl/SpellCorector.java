/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spellImpl;

import java.io.IOException;

/**
 *
 * @author nortond
 */
public class SpellCorector implements spell.SpellCorrector{

    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String suggestSimilarWord(String inputWord) throws NoSimilarWordFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
