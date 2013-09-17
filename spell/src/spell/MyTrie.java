package spell;

import java.util.Arrays;
import java.util.Objects;

public class MyTrie implements Trie{

    private int wordCount = 0; // increase word count after word insertion
    private int nodeCount = 1; // increase node count after node insertion
    private MyNode root = null;
    
    public MyTrie (MyNode node) {
        root = node;
        nodeCount = 1;
        wordCount = 0;
        
    }
    public MyTrie () {
        root = new MyNode();
        nodeCount = 1;
        wordCount = 0;
    }
    
    @Override
    public void add(String word) {
        root.add(word.toLowerCase());
    }

    @Override
    public MyNode find(String word) {
        return root.find(word.toLowerCase());
    }

    @Override
    public int getWordCount() {
        return wordCount;
    }

    @Override
    public int getNodeCount() {
        return nodeCount;
    }
    
    @Override
    public String toString() {
        return "MyTrie{" + "wordCount=" + wordCount + ", nodeCount=" + nodeCount + ", root=" + root + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.wordCount;
        hash = 11 * hash + this.nodeCount;
        hash = 11 * hash + Objects.hashCode(this.root);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MyTrie other = (MyTrie) obj;
        if (this.wordCount != other.wordCount) {
            return false;
        }
        if (this.nodeCount != other.nodeCount) {
            return false;
        }
        if (!Objects.equals(this.root, other.root)) {
            return false;
        }
        return true;
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
//            System.out.println(word);
            if(!word.isEmpty()){
                StringBuilder sb = new StringBuilder(word);
                int index = sb.charAt(0) - 'a';
                if (alph[index] == null){
                    alph[index] = new MyNode();
                    nodeCount++;
//                    System.out.println(nodeCount);
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

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 47 * hash + this.count;
            hash = 47 * hash + Objects.hashCode(this.word);
            hash = 47 * hash + Arrays.deepHashCode(this.alph);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final MyNode other = (MyNode) obj;
            if (this.count != other.count) {
                return false;
            }
            if (!Objects.equals(this.word, other.word)) {
                return false;
            }
            if (!Arrays.deepEquals(this.alph, other.alph)) {
                return false;
            }
            return true;
        }
        
    }
}
