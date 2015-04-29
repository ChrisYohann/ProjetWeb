/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author matylde
 */
public class Compte {

    private int nbrPlaceValide;
    private int numSpect;
    private int id;
    private String login;
    private List<Representation> repres;
    private Date jour;
    private int heure;
    private int nbrPlace;
    private int numDossier;
    private int numRang;
    private int numPlace;
    private int numSalle;
    private String nomSpect;

    public int getnbrPlacValide() {
        return this.nbrPlaceValide;
    }

    public void setnbrPlaceValide(int nbrPlaceValide) {
        this.nbrPlaceValide = nbrPlaceValide;
    }

    public int getNumSalle() {
        return numSalle;
    }

    public Date getJour() {
        return this.jour;
    }

    public int getNumSpect() {
        return this.numSpect;
    }

    public int getHeure() {
        return this.heure;
    }

    public int getId() {
        return this.id;
    }

    public String getLogin() {
        return this.login;
    }
    
    public String getNomSpect(){
        return this.nomSpect ;
    }

    public List<Representation> getRepres() {
        return this.repres;
    }

    public int getNumDossier() {
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

    public void setJour(Date jour) {
        this.jour = jour;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    public void setNumDossier(int numDossier) {
        this.numDossier = numDossier;
    }

    public void setNumSalle(int numSalle) {
        this.numSalle = numSalle;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setRepres(List<Representation> repres) {
        this.repres = repres;
    }

    public void setNbrPlace(int nubrPlace) {
        this.nbrPlace = nbrPlace;
    }

    public void setNumRang(int numRang) {
        this.numRang = numRang;
    }

    public void setNumPlace(int numPlace) {
        this.numPlace = numPlace;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPanier() {
        return false;
    }

    public void setNumSpect(int numSpect) {
        this.numSpect = numSpect;
    }
    
    public void setNomSpect(String nomSpect) {
        this.nomSpect = nomSpect;
    }
}
