/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import beans.UtilisateurInscrBean;
import dao.DAOException;
import dao.UtilisateurInscrDao;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author chris
 */
public class MetierInscr {
    
   private static final String CHAMP_NOM = "nom" ;
   private static final String CHAMP_PRENOM = "prenom" ;
   private static final String CHAMP_LOGIN = "login" ;
   private static final String CHAMP_PASS = "mdp" ;
   private static final String CHAMP_MAIL = "mail" ;
   
   private UtilisateurInscrDao utilisateur ;
   
   public MetierInscr(UtilisateurInscrDao user){
       this.utilisateur = user ;
   }
   
   public UtilisateurInscrBean inscrireUtilisateur(HttpServletRequest request){
       boolean inscrit = false ;
       UtilisateurInscrBean nouveaumembre = new UtilisateurInscrBean();
       nouveaumembre.setNom(request.getParameter(CHAMP_NOM));
       nouveaumembre.setPrenom(request.getParameter(CHAMP_PRENOM));
       nouveaumembre.setLogin(request.getParameter(CHAMP_LOGIN));
       nouveaumembre.setPassword(request.getParameter(CHAMP_PASS));
       nouveaumembre.setEmail(request.getParameter(CHAMP_MAIL));
       
       
    try {
        this.utilisateur.creer(nouveaumembre) ;
        }
     catch ( DAOException e ) {
        String resultat = "Échec d'inscription. L'utilisateur existe déjà.";
        e.printStackTrace();
    }

       
       return nouveaumembre ;
   }
    
}
