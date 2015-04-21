/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import dao.UtilisateurCoDao ;
import beans.UtilisateurCoBean ;
import dao.DAOException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author chris
 */

public class MetierCo {
    private final static String CHAMP_USER = "login" ;
    private final static String CHAMP_PASS = "mdp" ;
    
    private UtilisateurCoDao utilisateurdao ;
    
    public MetierCo(UtilisateurCoDao utilisateur){
        this.utilisateurdao = utilisateur ;
    }
    
    public UtilisateurCoBean connectUser( HttpServletRequest request ) {
    String user = request.getParameter(CHAMP_USER );
    String pass = request.getParameter(CHAMP_PASS);

    UtilisateurCoBean utilisateur = new UtilisateurCoBean();
    try {
        this.utilisateurdao.trouver(user, pass);
        }
     catch ( DAOException e ) {
        String resultat = "Échec de connexion. Mot de passe incorrect.";
        e.printStackTrace();
    }

      return utilisateur;
    }
    
    
}
