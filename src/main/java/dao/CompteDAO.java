/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Compte;
import java.util.List;

/**
 *
 * @author igierm
 */
public interface CompteDAO {
    
        public List<Compte> creer(String login, boolean value) throws DAOException;
        
        public void payer_reservation(int numDossier) throws DAOException;

}
