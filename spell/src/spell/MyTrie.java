/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spell;

/**
 *
 * @author dallasnorton
 */
public class MyTrie implements Trie{

    private int wordCount = 0; // increase word count after word insertion
    private int nodeCount = 0; // increase node count after node insertion
    private Node parentNode = null;
    
    public MyTrie (Node node) {
        parentNode = node;
    }
    public MyTrie () {
        parentNode = new MyNode();
    }
    
    @Override
    public void add(String word) {
       
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node find(String word) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getWordCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNodeCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
