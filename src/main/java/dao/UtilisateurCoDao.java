/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.UtilisateurCoBean ;

/**
 *
 * @author chris
 */
public interface UtilisateurCoDao {
    
    void creer( UtilisateurCoBean utilisateur ) throws DAOException;

    UtilisateurCoBean trouver( String login, String password ) throws DAOException;
    
}
