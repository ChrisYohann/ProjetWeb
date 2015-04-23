/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import beans.UtilisateurCoBean;
import beans.UtilisateurInscrBean;
import dao.UtilisateurInscrDao;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author chris
 */
public class GestionMembre {
    
    private UtilisateurInscrDao utilisateur ;
    
    public GestionMembre(UtilisateurInscrDao user){
       this.utilisateur = user ;
   }
    
    public List<UtilisateurInscrBean> AfficherMembre(HttpServletRequest request){
        return utilisateur.trouver() ;
    }
    
    
}
