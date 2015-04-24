/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.beans.*;


/**
 *
 * @author igierm
 */
public class Place{

    private int numRang;
    private int numPlace;
    private int numSalle;

  
    public int getNumRang() {
        return this.numRang;
    }
    
    public int getNumPlace() {
        return this.numPlace;
    }
    
    public int getNumSalle() {
        return this.numSalle;
    }
    
     
    public void setNumSalle(int numSalle) {
        this.numSalle=numSalle;
    }
    public void setNumRang(int numRang) {
        this.numRang=numRang;
    }

public void setNumPlace(int numPlace) {
        this.numPlace=numPlace;
    }
}

