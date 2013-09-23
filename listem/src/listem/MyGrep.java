/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listem;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author nortond
 */
public class MyGrep extends Listem implements Grep{

    Map<File, List<String>> grepMap = null;
    
    public MyGrep(){
        super();
        grepMap = new TreeMap();
    }
    
    @Override
    public Map<File, List<String>> grep(File directory, String fileSelectionPattern, String substringSelectionPattern, boolean recursive) {
        setCount(0);
        Map<File, List<String>> grepMap = new TreeMap();
        
        searchDir(directory, fileSelectionPattern, substringSelectionPattern, recursive);
        return grepMap;
    }

    @Override
    public void scanFile(File currentFile, String filePattern, String selectionPattern) {
        System.out.println("File: " + currentFile.getName());
//        System.out.println(selectionPattern);
        try {
            Pattern p = Pattern.compile(selectionPattern);

            List<String> myList = new ArrayList<String>();
            Scanner sc = new Scanner(currentFile);
            String tempStr = null;
            while(sc.hasNext()){
                tempStr = sc.nextLine();
                Matcher m = p.matcher(tempStr);
                System.out.println(tempStr);
                if(m.find()){
                    System.out.println("found a word match");
                    myList.add(tempStr);
                }
            }
            System.out.println(myList.toString());
            if(!myList.isEmpty()){
              grepMap.put(currentFile, myList);  
            }
//            grepMap.put(currentFile, myList);
            System.out.println(grepMap.toString());
        }
        catch (Exception e){
            System.out.println("scanFile method LineCounter");
            e.printStackTrace();
        }
    }
}
