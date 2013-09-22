/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listem;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nortond
 */
public class MyGrep extends Listem implements Grep{

    Map<File, List<String>> grepMap = null;
    
    public MyGrep(){
        super();
        grepMap = new HashMap();
    }
    
    @Override
    public Map<File, List<String>> grep(File directory, String fileSelectionPattern, String substringSelectionPattern, boolean recursive) {
        setCount(0);
        Map<File, List<String>> grepMap = new HashMap();
        
        
        searchDir(directory, fileSelectionPattern, substringSelectionPattern, recursive);
        
        return grepMap;
    }

    @Override
    public void scanFile(File current) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
