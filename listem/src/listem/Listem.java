/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listem;

import java.io.File;
import java.util.regex.*;

public abstract class Listem {

    private int count = 0;
    
    public Listem (){
        count = 0;
    }
    
    public abstract void scanFile(File current);
    
    public void searchDir(File current, String filePattern, String selectionPattern, Boolean isRecursive){
        //Patern p = Pattern.compile(Regexp string goes here);
        //Matcher m = p.matcher(fileName || fileLine **what it should be searching through**);
        //m.matches(); if the regexp matches the string exactly **file name matching**
        //m.find(); searches for any substring of that string that matches the string  ****
        
        Pattern p = Pattern.compile(filePattern);
        System.out.println(filePattern);
        Matcher m = p.matcher(current.getName());
        System.out.println(current.getName());
        
        
        for(File childFile : current.listFiles()){
//            System.out.println(childFile.getName());
//            System.out.println(childFile.isFile());
            if(childFile.isDirectory() && isRecursive){
                searchDir(childFile, filePattern, selectionPattern, isRecursive);
            }
            if(childFile.isFile()){
//                does this file match my file pattern?    
                System.out.println("look here");
//                System.out.println(m.matches());
                if(m.matches()){
                    scanFile(current);
                }            
            }
        }        
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public void incrementCount() {
        count += 1;
    }
}