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
    
        
    private SpectacleDao spectacle ;
    
    public GestionSpectacle(SpectacleDao scene){
       this.spectacle = scene ;
   }
    
    public List<Spectacle> AfficherSpectacle(HttpServletRequest request){
        return spectacle.afficher_spectacles() ;
        
    }
    
}
    

