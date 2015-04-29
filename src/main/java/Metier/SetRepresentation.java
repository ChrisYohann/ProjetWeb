/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import beans.Spectacle;
import beans.Representation;
import dao.DAOException;
import dao.RepresentationDao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author chris
 */
public class SetRepresentation {

    private static final String JOUR = "day";
    private static final String MOIS = "month";
    private static final String ANNEE = "year";
    private static final String SALLE = "salle";
    private static final String SPECTACLE = "newspectacle";
    private static final String HEURE = "heure";
    private static final String PLACES = "places" ;
    
    private RepresentationDao representant;
    
    
    public SetRepresentation(RepresentationDao representant) {
        this.representant = representant ;
    }
    

    public Representation creer_representation(HttpServletRequest request) {
        String jour = request.getParameter(JOUR);
        String mois = request.getParameter(MOIS);
        String annee = request.getParameter(ANNEE);
        String date = date_en_chaine(jour, mois, annee);

        Representation prez = new Representation();
        prez.setSpect(((Spectacle) request.getSession(true).getAttribute(SPECTACLE)));
        prez.setJour(format_date(jour,mois,annee));
        prez.setHeure(Integer.parseInt(request.getParameter(HEURE)));
        prez.setNumSalle(Integer.parseInt(request.getParameter(SALLE)));
        prez.setNbrPlace(Integer.valueOf(request.getParameter(PLACES)));
        
        
        
        //On compare à la date actuelle
        /*if (format_date(jour,mois,annee).compareTo(new Date()) < 0) {
            request.getSession(true).setAttribute("message_erreur", "<FONT COLOR=\"red\" >Vous ne pouvez pas créer une representation antérieure à la date actuelle</FONT>");
            return null ;
        }*/
        this.representant.creer(prez,date);
              
        return prez ;
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
