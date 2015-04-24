/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.Image;
import java.text.DateFormat;
import java.util.* ;

/**
 *
 * @author igierm
 */
public class Representation {
    private Spectacle spect;
    private Date jour;
    private int heure;
    private int numSalle;

    
    public Spectacle getSpect() {
        return this.spect;
    }
    public Date getJour() {
        return this.jour;
    }
    
    public String afficherDate(){
        String belle_date = null ;
        DateFormat dateformatFR = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL, new Locale("FR","fr"));
        belle_date = dateformatFR.format(jour);
        belle_date = belle_date.substring(0,belle_date.length()-12);
        return belle_date ;

    }
    
    public int getHeure() {
        return this.heure;
    }
   
    public int getNumSalle() {
        return this.numSalle;
    }
    
    public void setSpect(Spectacle spect) {
        this.spect=spect;
    }
    public void setJour(Date jour) {
        this.jour=jour;
    }
    public void setHeure(int heure) {
        this.heure=heure;
    }  
    public void setNumSalle(int numSalle) {
        this.numSalle=numSalle;
    }
}
