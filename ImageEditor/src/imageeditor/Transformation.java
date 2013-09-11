package imageeditor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class Transformation {
 
    public static void pixelate(String[] args) {
        try (Scanner sc = new Scanner(new File(args[0])).useDelimiter("(\\s+)(#[^\\n]*\\n)?(\\s*)|(#[^\\n]*\\n)(\\s+)|(#[^\\n]*\\n)");)//("\\s|#[^\n]*\\n")+
        {
            String srcFileName = args[0];
            String destFileName = args[1];
            String magicNumber = sc.next();
            String transLength = "";
            int imgWidth = sc.nextInt();
            int imgHeight = sc.nextInt();
            int max = sc.nextInt();
            int red = 0;
            int blue = 0;
            int green = 0;
            String trans = args[2];
            if(trans.equalsIgnoreCase("motionblur")){
                transLength = args[3];    
            }
            
            Pixel[][] img = new Pixel[imgHeight][imgWidth];
            while(sc.hasNext()){                
                for(int i = 0; i < img.length; i++){
                    for(int j = 0; j < img[0].length; j++){
                        Pixel p = new Pixel(sc.nextInt(), sc.nextInt(), sc.nextInt());     
                        img[i][j] = p;
                    }   
                }                
            }
            System.out.println(args[0]);
            System.out.println(args[1]);
            System.out.println(args[2]);
            switch (args[2])
            {
                default :
                case "invert":
                    Transformation.invert(img, destFileName, magicNumber, max);    
                    break;
                case "grayscale":
                    Transformation.grayscale(img, destFileName, magicNumber, max); 
                    break;
                case "emboss":
                    Transformation.emboss(img, destFileName, magicNumber, max, imgHeight, imgWidth);
                    break;
                case "motionblur":
                    Transformation.motionblur(img, transLength, destFileName, magicNumber, max); 
                    break;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("USAGE 3: java ImageEditor " + args[0] + " " + args[1] + " " + args[2]);
        }
    }
    
    public static void invert (Pixel[][] pixels, String fileName, String magicNumber, int max) throws Exception {        
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));)
        {
        out.write(magicNumber + "\n");
        out.write(pixels[0].length + " ");
        out.write(pixels.length + "\n");
        out.write(max + "\n");
        for(int i = 0; i < pixels.length; i++){
            for(int j = 0; j < pixels[0].length; j++){
                if(pixels[i][j] != null)
                {
                    out.write(255 - pixels[i][j].getRed() + " ");
                    out.write(255 - pixels[i][j].getGreen() + " ");
                    out.write(255 - pixels[i][j].getBlue() + " ");
                }
            }     
        }
        out.close();   
        }
    }
    
    public static void grayscale (Pixel[][] pixels, String fileName, String magicNumber, int max) throws Exception {
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));)
        {
        out.write(magicNumber + "\n");
        out.write(pixels[0].length + " ");
        out.write(pixels.length + "\n");
        out.write(max + "\n");
        for(int i = 0; i < pixels.length; i++){
            for(int j = 0; j < pixels[0].length; j++){
                int aveRed = ((pixels[i][j].getRed() + pixels[i][j].getBlue() + pixels[i][j].getGreen()) / 3);
                int aveGreen = ((pixels[i][j].getRed() + pixels[i][j].getBlue() + pixels[i][j].getGreen()) / 3);
                int aveBlue = ((pixels[i][j].getRed() + pixels[i][j].getBlue() + pixels[i][j].getGreen()) / 3);

                pixels[i][j].setRed(aveRed);
                pixels[i][j].setGreen(aveGreen);
                pixels[i][j].setBlue(aveBlue);
                out.write(pixels[i][j].getRed() + " ");
                out.write(pixels[i][j].getGreen() + " ");
                out.write(pixels[i][j].getBlue() + " ");
            }     
        }
        out.close();   
        }
    }
    
    public static void emboss (Pixel[][] pixels, String fileName, String magicNumber, int max, int imgHeight, int imgWidth) throws Exception {
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));) {
        out.write(magicNumber + "\n");
        out.write(pixels[0].length + " ");
        out.write(pixels.length + "\n");
        out.write(max + "\n");
        for(int i = pixels.length-1; i >= 0; i--){
            for(int j = pixels[0].length-1; j >= 0; j--){
                    //check if in first row or column
//                    if(i == 0 || j == 0){
//                                                pixels[i][j].setRed(128);
//                        pixels[i][j].setGreen(128);
//                        pixels[i][j].setBlue(128);
//                    }
                    if(i > 0 && j > 0) {
                        int redDiff = pixels[i][j].getRed() - pixels[i-1][j-1].getRed();
                        int blueDiff = pixels[i][j].getBlue() - pixels[i-1][j-1].getBlue();
                        int greenDiff = pixels[i][j].getGreen() - pixels[i-1][j-1].getGreen();
                        int largeDiff = redDiff;

                        if(Math.abs(greenDiff) > Math.abs(largeDiff)){
                            largeDiff = greenDiff;
                        }
                        if(Math.abs(blueDiff) > Math.abs(largeDiff)){
                            largeDiff = blueDiff;
                        }

                        // add 128
                        largeDiff += 128;                    
                        //scale < 0 goes to 0 greater than 255 goes to 255
                        if(largeDiff < 0){
                            largeDiff = 0;
                        }
                        else if (largeDiff > 255){
                            largeDiff = 255;
                        }
                        //set r g b to scale value
                        pixels[i][j].setRed(largeDiff);
                        pixels[i][j].setBlue(largeDiff);
                        pixels[i][j].setGreen(largeDiff);
                    }
                    else {
                        pixels[i][j].setRed(128);
                        pixels[i][j].setGreen(128);
                        pixels[i][j].setBlue(128);
                    }
            }     
        }
        for(int i = 0; i < pixels.length; i++){
            for(int j = 0; j < pixels[0].length; j++){
                out.write(pixels[i][j].getRed() + " ");
                out.write(pixels[i][j].getGreen() + " ");
                out.write(pixels[i][j].getBlue() + " ");
            }
        }
        out.close();   
        }        
    }
    
    public static void motionblur (Pixel[][] pixels, String transLength, String fileName, String magicNumber, int max) throws Exception {
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));)
        {
        out.write(magicNumber + "\n");
        out.write(pixels[0].length + " ");
        out.write(pixels.length + "\n");
        out.write(max + "\n");
        for(int i = 0; i < pixels.length; i++){
            for(int j = 0; j < pixels[0].length; j++){
//                int aveRed = 0;
//                int aveBlue = 0;
//                int aveGreen = 0;
                int aveRed = pixels[i][j].getRed();
                int aveBlue = pixels[i][j].getBlue();
                int aveGreen = pixels[i][j].getGreen();
                int edge = Integer.parseInt(transLength);
                if(edge < 1){
                    return;
                }
                for(int k = 1; k < Integer.parseInt(transLength); k++) {                        
                    if(j + k < pixels[0].length) {
                        //add to average
                        aveRed += pixels[i][j+k].getRed();
                        aveBlue += pixels[i][j+k].getBlue();
                        aveGreen += pixels[i][j+k].getGreen();
                    }
                    else {
                        edge = k;
                        break;
                    }
                }
                aveRed /= edge;
                aveBlue /= edge;
                aveGreen /= edge;
                        
                pixels[i][j].setRed(aveRed);
                pixels[i][j].setBlue(aveBlue);
                pixels[i][j].setGreen(aveGreen);   
                out.write(pixels[i][j].getRed() + " ");
                out.write(pixels[i][j].getGreen() + " ");
                out.write(pixels[i][j].getBlue() + " ");
            }     
        }
        out.close();   
        }
    }
}