/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.Image;
import java.util.* ;


/**
 *
 * @author igierm
 */
public class Spectacle {
    private String  name;
    private int numero;
    private String description;
    private Image affiche;
    private List<Representation> representations ;

    
    public String getName() {
        return this.name;
    }
    
    public int getNumero() {
        return this.numero;
    }
    public String getDescription() {
        return this.description;
    }
    
    public Image getAffiche() {
        return this.affiche;
    }
    
    public List<Representation> getRepresentation(){
        return this.representations ;
    }
    
    public void setName(String name) {
        this.name=name;
    }
    
    public void setNumero(int numero) {
        this.numero=numero;
    }
    
    public void setDescription(String description) {
        this.description=description;
    }
    
    public void setAffiche(Image affiche) {
        this.affiche=affiche;
    }
    
    public void setRepresentation(List<Representation> scenes){
        this.representations = scenes ;
    }
    
     
}
