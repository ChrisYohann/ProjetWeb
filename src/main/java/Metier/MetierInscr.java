/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import beans.UtilisateurInscrBean;
import controleur.Crypteur;
import dao.DAOConfigurationException;
import dao.DAOException;
import dao.UtilisateurInscrDao;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Properties;
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
   private Crypteur crypteur;
   
   public MetierInscr(UtilisateurInscrDao user, Crypteur crypt){
       this.utilisateur = user ;
       this.crypteur = crypt;
   }
   
   public UtilisateurInscrBean inscrireUtilisateur(HttpServletRequest request) throws NoSuchAlgorithmException{
       boolean inscrit = false ;
       UtilisateurInscrBean nouveaumembre = new UtilisateurInscrBean();
       nouveaumembre.setNom(request.getParameter(CHAMP_NOM));
       nouveaumembre.setPrenom(request.getParameter(CHAMP_PRENOM));
       nouveaumembre.setLogin(request.getParameter(CHAMP_LOGIN));
       String mdp = request.getParameter(CHAMP_PASS);
       String yo=crypteur.crypter_mdp(mdp);
       nouveaumembre.setPassword(yo);
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
