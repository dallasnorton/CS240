package spell;

import java.util.Arrays;
import java.util.Objects;

public class MyTrie implements Trie {
    private int wordCount;
    private int nodeCount;
    private MyNode root;
    
    public MyTrie(){
        wordCount = 0;
        nodeCount = 0;
        root = new MyNode();
    }
    
    public MyTrie(MyNode node, int wordCount, int nodeCount){
        this.root = node;
        this.wordCount = wordCount;
        this.nodeCount = nodeCount;
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
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.wordCount;
        hash = 67 * hash + this.nodeCount;
        hash = 67 * hash + Objects.hashCode(this.root);
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

    @Override
    public String toString() {
        return "MyTrie [" + "getNodeCount " + getNodeCount() + " " + "getWordCount " + getWordCount() + " " + "hashCode " + hashCode() + " " + "nodeCount " + nodeCount + " " + "root " + root + " " + "wordCount " + wordCount + "]";
    }
    
    
    public class MyNode implements Node {
        private int count;
        private MyNode[] alph;
                
        public MyNode(){
            count = 0;
            alph = new MyNode[26];
        }
        
        public void add(String word){
            if(!word.isEmpty()){
                StringBuilder sb = new StringBuilder(word);
                int index = sb.charAt(0) - 'a';
                if(alph[index] == null){
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
        
        public MyNode find(String word){
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
                if(this.getValue() > 0){
                    return this;
                }
                else{
                    return null;                    
                }
            }
            
        }
        
        @Override
        public int getValue() {
            return count;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 59 * hash + this.count;
            hash = 59 * hash + Arrays.deepHashCode(this.alph);
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
            if (!Arrays.deepEquals(this.alph, other.alph)) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return "MyNode{" + "count=" + count + ", alph=" + alph + '}';
        }
        
    }
}
