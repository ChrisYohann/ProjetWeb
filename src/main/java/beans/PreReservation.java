/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author jm
 */
public class PreReservation {
    
    private int position;
    private int numero;
    private String nomspect;
    private int nb_place;
    private String cat;
    private Date date;
    private int numsalle;
    private int heure ;
    
    
    
    public PreReservation(){
        
    }
    
    public int getNum() {
        return this.numero;
    }
    
    public int getSalle() {
        return this.numsalle;
    }
    
    public int getHeure() {
        return heure;
    }
    
    
    public String getNom() {
        return this.nomspect;
    }
    
    public Date getDate() {
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
    
    public void setNum(int num) {
        this.numero=num;
    }
    
    public void setHeure(int heure) {
        
        this.heure=heure;
    }
    
    public void setSalle(int salle) {
        this.numsalle=salle;
    }
    
    public void setPos(int pos) {
        this.position=pos;
    }
    
    public void setNom(String nom){
        this.nomspect=nom;
    }
    
    public void setDate(Date date){
        this.date=date;
    }
    
    public void setNbPlace(int nb){
        this.nb_place=nb;
    }
    
    public void setCat(String cat){
        this.cat=cat;
    }
    public String afficherDate(){
        String belle_date = null ;
        DateFormat dateformatFR = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL, new Locale("FR","fr"));
        belle_date = dateformatFR.format(date);
        belle_date = belle_date.substring(0,belle_date.length()-12);
        return belle_date ;

    }
    
    
    public String afficherInfo() {
        return  "Le " + this.afficherDate()+ " à " + heure + "h en Salle " + numsalle + ", Nombre de place(s): " + nb_place + " en catégorie " + cat ;
    }
    
    public void setDate_Heure_Salle(String date) throws ParseException{
        
        
       
        String jour= date.substring(0,10) ;
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.setDate(sdf.parse(jour));
        Date doute = sdf.parse(jour);
        
        int heure2 = Integer.valueOf(date.substring(11,13));
        int numsalle2 = Integer.valueOf(date.substring(14,15));
        this.setHeure(heure2);
        this.setSalle(numsalle2);
        
    }
}
