/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.Image;
import java.beans.*;
import java.util.ArrayList;
import java.util.*;


/**
 *
 * @author igierm
 */
public class Reservation {
    private int id;
    private String  login;
    private Representation repres;
    private Date jour;
    private int heure;
    private int nbrPlace;
    private int numDossier;
    private int numRang;
    private int numPlace;
    
    public Date getJour(){
        return this.jour;
    }
    
    public int getHeure(){
        return this.heure;
    }
    public int getId(){
        return this.id;
    }

    public String getLogin() {
        return this.login;
    }
    public Representation getRepres() {
        return this.repres;
    }
    
    public int getNumDossier(){
        return this.numDossier;
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
    
    public void setJour(Date jour){
        this.jour=jour;
    }
    
    public void setHeure(int heure){
        this.heure=heure;
    }
    
    public void setNumDossier(int numDossier) {
        this.numDossier=numDossier;
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
