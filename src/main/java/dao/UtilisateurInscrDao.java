/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.UtilisateurInscrBean;
import java.util.* ;

/**
 *
 * @author chris
 */
public interface UtilisateurInscrDao {
    
     void creer( UtilisateurInscrBean utilisateur ) throws DAOException;

     public List<UtilisateurInscrBean> trouver() throws DAOException ;
    
}
