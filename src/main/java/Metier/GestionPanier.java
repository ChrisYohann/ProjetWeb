/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import beans.PreReservation;

import beans.Representation;
import beans.Spectacle;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jm
 */
public class GestionPanier {
    
        
    public GestionPanier() {
        
    }
    
    public void gerer(HttpServletRequest request) throws ParseException {
        
        int oui = 1;
        ArrayList<PreReservation> panier = ( ArrayList<PreReservation>) request.getSession(true).getAttribute("monpanier");
        if(panier==null) {
            oui = 0;
            panier = new ArrayList<PreReservation>();
        }
        ArrayList<Spectacle> spectacle = ( ArrayList<Spectacle>) request.getSession().getAttribute("liste_spectacles");
        Iterator<Spectacle> it_spect = spectacle.iterator();
                
           PreReservation preticket = new PreReservation();
           
           while (it_spect.hasNext()) {
           Spectacle Spect = it_spect.next();
           String  nom_spect= Spect.getName();
           if(request.getParameter("ajout de " + nom_spect)!=null){
           String categorie = request.getParameter("categorie de " + nom_spect);
           String date = request.getParameter("jour");
           int nbrplace = Integer.valueOf(request.getParameter("nbrplace " + nom_spect));
           
           
           preticket.setCat(categorie);
           preticket.setNbPlace(nbrplace);
           preticket.setNom(nom_spect);
           preticket.setDate_Heure_Salle(date);
           if(oui==0) preticket.setPos(1);
           else {
           preticket.setPos(panier.size() + 1);
           }
           panier.add(preticket);
           request.getSession().setAttribute("monpanier", panier);
           
           }
           
           }
           
            }
    }
    

