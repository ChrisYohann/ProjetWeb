/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import beans.Compte;
import beans.Spectacle;
import dao.CompteDAO;
import dao.SpectacleDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author matylde
 */
public class GestionCompte {
    private CompteDAO compte ;
    
    public GestionCompte(CompteDAO scene){
       this.compte = scene ;
   }
    
   public List<Compte> AfficherCompte(HttpServletRequest request){
        return compte.creer() ;
        
    }
}
