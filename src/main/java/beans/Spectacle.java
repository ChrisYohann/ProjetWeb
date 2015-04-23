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
public class Spectacle {
    private String  name;
    private int numero;
    private String description;
    private String horaire;
    private int numSalle;
    private Image affiche;
    
    public String getName() {
        return this.name;
    }
    public String getHoraire() {
        return this.horaire;
    }
    
    public int getNumero() {
        return this.numero;
    }
    public String getDescription() {
        return this.description;
    }
    public int getNumSalle() {
        return this.numSalle;
    }
    public Image getAffiche() {
        return this.affiche;
    }
    
    public void setName(String name) {
        this.name=name;
    }
    public void setHoraire(String horaire) {
        this.horaire=horaire;
    }
    public void setNumero(int numero) {
        this.numero=numero;
    }
    
    public void setDescription(String description) {
        this.description=description;
    }
    
    public void setNumSalle(int numSalle) {
        this.numSalle=numSalle;
    }
    
    public void setAffiche(Image affiche) {
        this.affiche=affiche;
    }
    
    
   
}
