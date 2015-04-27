/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import beans.PreReservation;
import beans.Representation;
import beans.UtilisateurCoBean;
import dao.DAOException;
import dao.PayerDao;
import dao.RepresentationDao;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author igierm
 */
public class GestionAchat {
    
    private PayerDao payer ;
    private RepresentationDao represdao;

    
    public GestionAchat(PayerDao scene,RepresentationDao representant){
       this.payer = scene ;
       this.represdao = representant ;

   }
    
   public String date_en_chaine(Date date){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date) ;
    }
     
    public void gerer(HttpServletRequest request, HttpServletResponse response){
        List<Representation> repres = new ArrayList<Representation>();
        ArrayList<PreReservation> panier = ( ArrayList<PreReservation>) request.getSession(true).getAttribute("monpanier");
        
        for (int i=0; i<panier.size(); i++) {
                String pos = request.getParameter(Integer.toString(panier.get(i).getPos()));
                   if ( pos!=null) {
                     Date date=  panier.get(i).getDate();
                     String dateE = date_en_chaine(date);
                     int heure =panier.get(i).getHeure();
                     int salle =panier.get(i).getSalle();
                     Representation representation= this.represdao.trouver(dateE, heure, salle);
                     repres.add(representation);
                           }
                    
                   
               }//fin de la boucle for
        String payers=request.getParameter("payer");
        String reserver=request.getParameter("reserver");
        String login= ((UtilisateurCoBean) request.getSession().getAttribute("utilisateur")).getLogin();
        //TODO: gerer les cas impossible
            if (payers!=null) {
        payer.creer(repres, login);
            }else if(reserver!=null)  {
        payer.reserver(repres, login);    
            }
    }
}
