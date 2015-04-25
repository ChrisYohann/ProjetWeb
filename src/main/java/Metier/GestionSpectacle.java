/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import beans.Spectacle;
import dao.SpectacleDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author chris
 */
public class GestionSpectacle {
    
   private static final String CHAMP_nomSpect = "nomSpect" ;
   private static final String CHAMP_DESCRIPTION = "description" ;
   private static final String CHAMP_IMAGE = "affiche" ;
        
    private SpectacleDao spectacle ;
    
    public GestionSpectacle(SpectacleDao scene){
       this.spectacle = scene ;
   }
    
    public void nouveauSpectacle(HttpServletRequest request){
        Spectacle festival = new Spectacle();
        
        festival.setName(request.getParameter(CHAMP_nomSpect));
        festival.setDescription(request.getParameter(CHAMP_DESCRIPTION));
        festival.setAffiche(request.getParameter(CHAMP_IMAGE));
        this.spectacle.creer(festival);
    }
    
    public List<Spectacle> AfficherSpectacle(HttpServletRequest request){
        return spectacle.afficher_spectacles() ;
        
    }
    
}
    

