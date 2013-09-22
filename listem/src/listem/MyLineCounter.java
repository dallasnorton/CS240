/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listem;

import java.io.*;
import java.util.*;

/**
 *
 * @author nortond
 */
public class MyLineCounter extends Listem implements LineCounter{

    Map<File, Integer> lineCounterMap = null;
    
    public MyLineCounter(){
        super();
        lineCounterMap = new HashMap();
    }
    
    @Override
    public Map<File, Integer> countLines(File directory, String fileSelectionPattern, boolean recursive) {
        try{
            setCount(0);
            Map<File, Integer> lineCounterMap = new HashMap();

            searchDir(directory, fileSelectionPattern, null, recursive);
            lineCount();
        }
        catch(Exception e){
           System.out.println("countLines method error");
           e.printStackTrace();
        }
        return lineCounterMap;
    }
    
    public Map lineCount(){
        
//        searchDir(file, pattern, null, isRecursive);
        
        return lineCounterMap;
    }

    @Override
    public void scanFile(File currentFile) {
        try {
            Scanner sc = new Scanner(currentFile);

            while(sc.hasNext()){
                incrementCount();
                sc.nextLine();
            }

            lineCounterMap.put(currentFile, getCount());
        }
        catch (Exception e){
            System.out.println("scanFile method LineCounter");
            e.printStackTrace();
        }
    }
}