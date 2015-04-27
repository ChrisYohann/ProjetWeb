/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;


import beans.Representation;
import beans.Spectacle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;


import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author jm
 */
public class PourRepres {
    private Object DateTimeFormatter;
    
    public PourRepres() {
        
    }
    
    public void gerer(HttpServletRequest request) {
        int oui =1;
        ArrayList<Representation> panier = ( ArrayList<Representation>) request.getSession(true).getAttribute("monpanier");
        if(panier==null) {
            oui = 0;
            panier = new ArrayList<Representation>();
        }
               
        ArrayList<Spectacle> spectacle = ( ArrayList<Spectacle>) request.getSession().getAttribute("liste_spectacles");
        Iterator<Spectacle> it_spect = spectacle.iterator();
                
                      
           while (it_spect.hasNext()) {
               
           Spectacle Spect = it_spect.next();
           String  nom_spect= Spect.getName();
           
           if(request.getParameter("ajout de " + nom_spect)!=null){
               
           
           String date = request.getParameter("jour");
           
           
          
           
           }
           
           
           
           }
           
    }
    }
    

