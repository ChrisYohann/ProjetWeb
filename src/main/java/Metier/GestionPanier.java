/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import beans.PreTicket;
import beans.Spectacle;
import java.io.PrintWriter;
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
    
    public void gerer(HttpServletRequest request) {
        
        ArrayList<PreTicket> panier = ( ArrayList<PreTicket>) request.getSession(true).getAttribute("monpanier");
        if(panier==null) {
            panier = new ArrayList<PreTicket>();
        }
        ArrayList<Spectacle> spectacle = ( ArrayList<Spectacle>) request.getSession().getAttribute("liste_spectacles");
        Iterator<Spectacle> it_spect = spectacle.iterator();
                
           PreTicket preticket = new PreTicket();
           
           String nom_spect= it_spect.next().getName();
           if(request.getParameter("ajout de " + nom_spect )!=null){
           String categorie = request.getParameter("categorie de " + nom_spect);
           String date = request.getParameter("jour");
           int nbrplace = Integer.valueOf(request.getParameter("nbrplace " + nom_spect));
           
           preticket.setCat(categorie);
           preticket.setNbPlace(nbrplace);
           preticket.setNom(nom_spect);
           preticket.setDate(date);
           panier.add(preticket);
           request.getSession().setAttribute("monpanier", panier);
           
           }
           
           
           
            }
    }
    

