/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.Image;
import java.beans.*;
import java.util.ArrayList;


/**
 *
 * @author igierm
 */
public class Reservation {
    private String  login;
    private Representation repres;
    private int nbrPlace;
    private ArrayList<Rang> numRang;
    private ArrayList<Place> numPlace;
    
    public String getLogin() {
        return this.login;
    }
    public Representation getrespres() {
        return this.repres;
    }
    
    public int getNbrPlace() {
        return this.nbrPlace;
    }
    
    public ArrayList<Rang> getNumRang() {
        return this.numRang;
    }
    
    public ArrayList<Place> getNumPlace() {
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
    
    public void setNumRang(ArrayList<Rang> numRang) {
        this.numRang=numRang;
    }
    public void setNumPlace(ArrayList<Place> numPlace) {
        this.numPlace=numPlace;
    }
    
    
    
   
}