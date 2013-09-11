/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imageeditor;

/**
 *
 * @author dallasnorton
 */
public class Pixel {
    private int red = 0;
    private int green = 0;
    private int blue = 0;
    
    public Pixel (int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    
    public Pixel () {
        this.red = 0;
        this.green = 0;
        this.blue = 0;
    }
    
    public int getRed () {
        return this.red;
    }
    
    public void setRed(int red) {
        this.red = red;
    }
    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }
    
    @Override
    public String toString() {
        String str = "";
        str = "dallas";
//        str = this.getBlue() + this.getGreen() + this.getRed();
        return str;   
    }
}
