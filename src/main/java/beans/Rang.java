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
public class Rang{

    private int numRang;
    private int numSalle;
    private String catTarif;

  
    public int getNumRang() {
        return this.numRang;
    }
    
    public String getCatTarif() {
        return this.catTarif;
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

public void setCatTarif(String catTarif) {
        this.catTarif=catTarif;
    }
}

