/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import beans.PreReservation;
import beans.Representation;
import beans.Spectacle;
import dao.ManagementRepresDao;
import dao.ManagementRepresDaoImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jm
 */
public class GestionNewRepres {
    private static final String JOUR = "day";
    private static final String MOIS = "month";
    private static final String ANNEE = "year";
    private static final String SALLE = "salle";
    private static final String SPECTACLE = "newspectacle";
    private static final String HEURE = "heure";
    private static final String PLACES = "places" ;
    private ManagementRepresDao manager ;
    
    public GestionNewRepres(ManagementRepresDao repres) {
        this.manager = repres; 
    }
    
    public Representation creer_representation(HttpServletRequest request) {
        String jour = request.getParameter(JOUR);
        String mois = request.getParameter(MOIS);
        String annee = request.getParameter(ANNEE);
        String date = date_en_chaine(jour, mois, annee);

        Representation prez = new Representation();
        
        prez.setSpect((Spectacle) request.getSession().getAttribute("representation2"));
        prez.setJour(format_date(jour,mois,annee));
        prez.setHeure(Integer.parseInt(request.getParameter(HEURE)));
        prez.setNumSalle(Integer.parseInt(request.getParameter(SALLE)));
        prez.setNbrPlace(Integer.valueOf(request.getParameter(PLACES)));
        
        this.manager.creer(prez,date);
        return prez;
    }
    
    
    public int trouver_elt(HttpServletRequest request) {

        int elt = -1;
       ArrayList<Spectacle> spect = (ArrayList<Spectacle>) request.getSession().getAttribute("liste_spectacles");


        int i = 0;
        int j = spect.size();

        if (spect.size() == 1) {
            elt = 1;

        } else {

            boolean dernier_element = true;
            for (i = 1; i < j + 1; i++) {

                if (i != j) {
                    String pos = request.getParameter("modifier " + spect.get(i - 1).getNumero());
                    if (pos != null) {

                        dernier_element = false;
                        elt = i;

                    }
                }

            }
            if (dernier_element) {

                elt = spect.size();
            }

        }

        return elt;

    }
    
    public String date_en_chaine(String jour, String mois, String annee){
        return annee + "-" + mois + "-" + jour;
    }
    
    public Date format_date(String jour, String mois, String annee) {
        String s = annee + "-" + mois + "-" + jour;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = sdf.parse(s);
        } catch (ParseException p) {
            p.printStackTrace();
        }
        return date ;
    }
}
