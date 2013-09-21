/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listem;

import java.io.File;

/**
 *
 * @author dallasnorton
 */
public abstract class Listem {

    int count = 0;
    
    public Listem (){
        count = 0;
    }
    
    public abstract void scanFile();
    
    public void searchDir(File current, String filePattern, String selectionPattern, Boolean isRecursive){
//        create Pattern for filenames
//        for(File childFile : current.listFiles()){
//            is childFile a directory?
//                if it is a directory && isRecursive --> searchDir(childFile, filePatter, selectionPattern, isRecursive);
        
//            is childFile a file?
//                does this file match my file pattern?
//                scanFile();
//        }
        
    }
}


//Patern p = Pattern.compile(Regexp string goes here);
//Matcher m = p.matcher(fileName || fileLine **what it should be searching through**);
//m.matches(); if the regexp matches the string exactly **file name matching**
//m.find(); searches for any substring of that string that matches the string  ****