/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.*;
import java.util.* ;
/**
 *
 * @author igierm
 */
public interface PayerDao {
    
    public void creer(Panier panier) throws DAOException ;//creer une reservation ou un achat
    
    public Panier trouver(int id) throws DAOException ;//trouver une representation
    
    public Panier afficher_panier() throws DAOException ;
        
}
