/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.Image;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
    private int nbrPlace ;
    private String erreur ;

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
      
    
    public String date_en_chaine(Date date){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date) ;
    }
    

    public int getHeure() {
        return this.heure;
    }

    public int getNumSalle() {
        return this.numSalle;
    }
    
    public String getErreur(){
        return this.erreur ;
    }
    
    public int getNbrPlace(){
        return this.nbrPlace ;
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
    
    public void setErreur(String mistake){
        this.erreur = mistake ;
    }
    
    public void setNbrPlace(int places){
        this.nbrPlace = places ;
    }
}
