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
    
    public void creer(List<Representation> repres,  ArrayList<PreReservation> preRes, String login) throws DAOException ;//creer une reservation ou un achat
       
    public void reserver(List<Representation> repres, ArrayList<PreReservation> preRes, String login) throws DAOException ;//creer une reservation ou un achat

   // public Panier afficher_panier() throws DAOException ;//enlever de la table panier la representation
        
}
