package imageeditor;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class ImageEditor {

    void run(String[] args) {
        try {
            Transformation.pixelate(args);
        }
        catch(Exception e){
            System.out.println("USAGE 2: java ImageEditor " + args[0] + " " + args[1] + " " + args[2]);
        }
    }
    
    public static void main(String[] args) {
        try{
            ImageEditor ie = new ImageEditor();
            ie.run(args);
//            Transformation.pixelate(args);
        }
        catch(Exception e){
            System.out.println("USAGE 1: java ImageEditor " + args[0] + " " + args[1] + " " + args[2]);
        }   
    }
}
