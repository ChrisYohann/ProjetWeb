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
  


    private static String SQL_LISTE_RESERVATIONS = "select distinct a.login,a.numDossier, a.jour, a.numSpect,a.heure, a.numSalle, a.numRang, a.numPLace,s.nomSpect, count(*) as NbResa from reservation a, spectacle s where a.login = ? and a.numSpect = s.numSpect group by numDossier ";
    private static String SQL_LISTE_ACHATS = "select distinct a.login,a.numDossier, a.jour, a.numSpect,a.heure, a.numSalle, a.numRang, a.numPLace,s.nomSpect, count(*) as NbResa from achat a, spectacle s where a.login = ? and a.numSpect = s.numSpect group by numDossier ";
    private static String PASSE_A_LA_CAISSE = "INSERT INTO achat (login, numDossier, numTicket, numSpect,jour, heure, numSalle, numRang, numPlace) select login,numDossier,numTicket,numSpect,jour,heure,numSalle,numRang,numPlace from reservation where numDossier = ?";
    private static String DELETE_BOOKINGS = "delete from reservation where numDossier = ?";

    private DAOManager manager;

    
    public CompteDAOImpl(DAOManager gerant) {
        this.manager = gerant;
    }
    @Override
    public List<Compte> creer(String login,boolean value) {
      
      List<Compte> compte =new ArrayList();
      Connection connexion = null ;
      PreparedStatement preparedStatement = null;
      ResultSet resultSet = null;
      ResultSet resulta = null;
      int i=0;
      Compte comptable = new Compte();
       
      try { connexion = manager.getConnection() ;
           if(value)
           {preparedStatement = initRequete(connexion, SQL_LISTE_RESERVATIONS, false,login);}
           else
           {preparedStatement = initRequete(connexion, SQL_LISTE_ACHATS, false,login);}    
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                
                comptable.setLogin(resultSet.getString(1));
                comptable.setNumDossier(resultSet.getInt(2));
                comptable.setJour(resultSet.getDate(3));
                comptable.setNumSpect(resultSet.getInt(4));
                comptable.setHeure(resultSet.getInt(5));
                comptable.setNumSalle(resultSet.getInt(6));
                comptable.setNumRang(resultSet.getInt(7));
                comptable.setNumPlace(resultSet.getInt(8));
                comptable.setnbrPlaceValide(resultSet.getInt(10));
                comptable.setNomSpect(resultSet.getString("nomSpect"));
                compte.add(comptable);
            }     
            } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeAll(resultSet, preparedStatement, connexion);
        }
      return compte;
    }
    
    public void payer_reservation(int numDossier) throws DAOException{
      Connection connexion = null ;
      PreparedStatement preparedStatement = null;
      ResultSet resultSet = null;
      
       
      try { connexion = manager.getConnection() ;
            preparedStatement = initRequete(connexion, PASSE_A_LA_CAISSE, true,numDossier);
            int statut = preparedStatement.executeUpdate();
            preparedStatement = initRequete(connexion, DELETE_BOOKINGS, true,numDossier);
            statut = preparedStatement.executeUpdate();
            
                   
            } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeAll(resultSet, preparedStatement, connexion);
        }
    
    }
        
    }


