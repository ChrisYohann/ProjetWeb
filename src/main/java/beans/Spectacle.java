/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;


import java.util.* ;


/**
 *
 * @author igierm
 */
public class Spectacle {
    private String  name;
    private int numero;
    private String description;
    private String affiche;
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
    
    public String getAffiche() {
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
    
    public void setAffiche(String affiche) {
        this.affiche=affiche;
    }
    
    public void setRepresentation(List<Representation> scenes){
        this.representations = scenes ;
    }
    
     
}
