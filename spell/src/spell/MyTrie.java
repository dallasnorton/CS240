package spell;

import java.util.Arrays;

public class MyTrie implements Trie{

    private int wordCount = 0; // increase word count after word insertion
    private int nodeCount = 1; // increase node count after node insertion
    private MyNode root = null;
    private MyNode pointer = null;
    
    public MyTrie (MyNode node) {
        root = node;
        pointer = node;
        nodeCount = 1;
        wordCount = 0;
        
    }
    public MyTrie () {
        root = new MyNode();
        pointer = new MyNode();
        nodeCount = 1;
        wordCount = 0;
    }
    
    @Override
    public void add(String word) {
        root.add(word.toLowerCase());
    }

    @Override
    public Node find(String word) {
        return root.find(word.toLowerCase());
    }

    @Override
    public int getWordCount() {
        return wordCount;
    }

    @Override
    public int getNodeCount() {
        return wordCount;
    }

    public MyNode getPointer() {
        return pointer;
    }
    
    @Override
    public String toString() {
        return "MyTrie{" + "wordCount=" + wordCount + ", nodeCount=" + nodeCount + ", root=" + root + ", pointer=" + pointer + '}';
    }

    public class MyNode implements Node {

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
            if(!word.isEmpty()){
                StringBuilder sb = new StringBuilder(word);
                int index = sb.charAt(0) - 'a';
                if (alph[index] == null){
                    alph[index] = new MyNode();
                    nodeCount++;
                }
                sb.deleteCharAt(0);
                alph[index].add(sb.toString());
            }
            else{
                if(count == 0){
                    wordCount++;
                }
                count++;
            }
        }

        public MyNode find(String word) {
            if(!word.isEmpty()){
                StringBuilder sb = new StringBuilder(word);
                int index = sb.charAt(0) - 'a';
                if(alph[index] != null){
                    sb.deleteCharAt(0);
                    return alph[index].find(sb.toString());
                }
                else{
                    return null;
                }
            }
            else{
                if(this.getCount() > 0){
                    return this;
                }
                else{
                    return null;
                }
            }
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

        @Override
        public int getValue() {
            return count;
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
        
        @Override
        public String toString() {
            return "MyNode{" + "count=" + count + ", word=" + word + ", alph=" + Arrays.toString(alph) + '}';
        }
    }
}
