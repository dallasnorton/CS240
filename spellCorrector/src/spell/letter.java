/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spell;

/**
 *
 * @author dallasnorton
 */
public enum letter {
    a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z;
    
    public letter valueOf(char c){
        return letter.valueOf(Character.toString(Character.toLowerCase(c)));
    }
}
