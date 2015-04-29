/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Compte;
import static dao.DAOUtil.initRequete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import beans.Representation;
import beans.Spectacle;
import beans.UtilisateurInscrBean;
import static dao.DAOUtil.closeAll;
import static dao.DAOUtil.initRequete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
/**
 *
 * @author igierm
 */
public class CompteDAOImpl implements CompteDAO{
  
    private static String SQL_TICKET = "select distinct a.login,a.numDossier, a.jour, a.numSpect, a.heure, a.numSalle, a.numRang, a.numPLace, count(*) as NbResa from reservation a group by numDossier ";
    private DAOManager manager;

    
    public CompteDAOImpl(DAOManager gerant) {
        this.manager = gerant;
    }
    @Override
    public List<Compte> creer() {
      
      List<Compte> compte =new ArrayList();
      Connection connexion = null ;
      PreparedStatement preparedStatement = null;
      ResultSet resultSet = null;
      int i=0;
       
      try { connexion = manager.getConnection() ;
            preparedStatement = initRequete(connexion, SQL_TICKET, false);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                Compte comptable = new Compte();
                comptable.setLogin(resultSet.getString(1));
                comptable.setNumDossier(resultSet.getInt(2));
                comptable.setJour(resultSet.getDate(3));
                comptable.setNumSpect(resultSet.getInt(4));
                comptable.setHeure(resultSet.getInt(5));
                comptable.setNumSalle(resultSet.getInt(6));
                comptable.setNumRang(resultSet.getInt(7));
                comptable.setNumPlace(resultSet.getInt(8));
                comptable.setnbrPlaceValide(resultSet.getInt(9));
                compte.add(comptable);
            }                
            } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeAll(resultSet, preparedStatement, connexion);
        }
      return compte;
    }

}
