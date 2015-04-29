/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.Image;
import java.beans.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author igierm
 */
public class Reservation {
    
    private String login;
    private int numDossier ;
    private int nbrPlace ;
    private Representation repres;
    private int numRang;
    private int numPlace;
    
    public String getLogin() {
        return this.login;
    }
    public Representation getRepres() {
        return this.repres;
    }
    
    public int getNbrPlace() {
        return this.nbrPlace;
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
    public void setRepres(Representation repres) {
        this.repres=repres;
    }
    public void setNbrPlace(int nubrPlace) {
        this.nbrPlace=nbrPlace;
    }
    
    public void setNumRang(int numRang) {
        this.numRang=numRang;
    }
    public void setNumPlace(int numPlace) {
        this.numPlace=numPlace;
    }
    public boolean isPanier(){
        return false;
    }
    
   
}
