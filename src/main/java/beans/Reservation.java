/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.Image;
import java.beans.*;


/**
 *
 * @author igierm
 */
public class Reservation {
    private String  login;
    private int numSpect;
    private String jour;
    private int heure;
    private int numSalle;
    private int numRang;
    private int numPlace;
    
    public String getLogin() {
        return this.login;
    }
    public String getJour() {
        return this.jour;
    }
    
    public int getNumSpect() {
        return this.numSpect;
    }
    
    public int getHeure() {
        return this.heure;
    }
    
    public int getNumSalle() {
        return this.numSalle;
    } 
    
    public int getNumRang() {
        return this.numRang;
    } 
    
    public int getNumPlace() {
        return this.numPlace;
    }
    public void setLogin(String login) {
        this.login=login;
    }
    public void setJour(String jour) {
        this.jour=jour;
    }
    public void setNumSpect(int numSpect) {
        this.numSpect=numSpect;
    }
    
    public void setHeure(int heure) {
        this.heure=heure;
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
