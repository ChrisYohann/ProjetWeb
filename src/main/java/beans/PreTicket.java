/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author jm
 */
public class PreTicket {
    
    private int position;
    private String nomspect;
    private int nb_place;
    private String cat;
    private String date;
    
    
    public PreTicket(){
        
    }
    
    public String getNom() {
        return this.nomspect;
    }
    
    public String getDate() {
        return this.date;
    }
    
    public String getCat() {
        return this.cat;
    }
    
    public int getNbPlace() {
        return this.nb_place;
    }
    
    public int getPos() {
        
        return this.position;
    }
    
    public void setPos(int pos) {
        this.position=pos;
    }
    
    public void setNom(String nom){
        this.nomspect=nom;
    }
    
    public void setDate(String date){
        this.date=date;
    }
    
    public void setNbPlace(int nb){
        this.nb_place=nb;
    }
    
    public void setCat(String cat){
        this.cat=cat;
    }
    
    public String afficherInfo() {
        return "Le " + date + ", Nombre de place(s): " + nb_place + " en catégorie " + cat +  " en position " + position ;
    }
}
