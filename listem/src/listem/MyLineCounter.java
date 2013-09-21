/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listem;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nortond
 */
public class MyLineCounter implements LineCounter extends Listem {

    Map<File, Integer> lineMap = null;
    public MyLineCounter(){
        super();
    }
    @Override
    public Map<File, Integer> countLines(File directory, String fileSelectionPattern, boolean recursive) {
        // reset count and map
        Map<File, Integer> lineCounterMap = new HashMap();
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Map lineCount(){
        
        searchDir(file, pattern, null, isRecursive);
        
        return Map;
    }
    
    @Override
    public void scanFile(File currentFile){
//       Create scanner
//        while(sc.hasNext())
//            count++;
//            sc.nextLine();
        
        
        
//            map.put(currentFile.getName, count); ????
    }
}
