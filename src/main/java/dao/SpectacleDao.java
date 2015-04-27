/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Representation;
import java.util.* ;
import beans.Spectacle;

/**
 *
 * @author chris
 */
public interface SpectacleDao {
    
    public void creer(Spectacle spectacle) throws DAOException ;
    
    public Spectacle trouver(int numSpect) throws DAOException ;
    
    public List<Spectacle> afficher_spectacles() throws DAOException ;
    
    public List<Representation> associer_representations(int numSpect) throws DAOException ;
    
}
