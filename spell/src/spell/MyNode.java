package spell;
 
public class MyNode implements Trie.Node {
    
    private int count = 0;
    private String word = null;
    private MyNode[] alph = new MyNode[26];
    
    public MyNode (MyNode[] letters, int count, String word) {
        setCount(count);
        setWord(word);
        alph = letters.clone();
    }
    public MyNode () {
        this.count = 0;
        alph = new MyNode[26];
        word = "";
    }

    
    public void add(String word) {
        
    }
    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

//    public void addChild() {
//        
//    }
    
    @Override
    public int getValue() {
        return count;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * @param word the word to set
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * @return the alph
     */
    public MyNode[] getAlph() {
        return alph;
    }

    /**
     * @param alph the alph to set
     */
    public void setAlph(MyNode[] alph) {
        this.alph = alph;
    }
}

// int index = letter - 'a';
