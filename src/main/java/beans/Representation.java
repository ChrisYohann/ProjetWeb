/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.Image;

/**
 *
 * @author igierm
 */
public class Representation {
    private int numSpect;
    private String jour;
    private int heure;
    private int numSalle;

    
    public int getNumSpect() {
        return this.numSpect;
    }
    public String getJour() {
        return this.jour;
    }
    
    public int getHeure() {
        return this.heure;
    }
   
    public int getNumSalle() {
        return this.numSalle;
    }
    
    public void setNumSpect(int numSpect) {
        this.numSpect=numSpect;
    }
    public void setJour(String jour) {
        this.jour=jour;
    }
    public void setHeure(int heure) {
        this.heure=heure;
    }  
    public void setNumSalle(int numSalle) {
        this.numSalle=numSalle;
    }
}