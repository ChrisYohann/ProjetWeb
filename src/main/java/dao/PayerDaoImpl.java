/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.*;
import static dao.DAOUtil.closeAll;
import static dao.DAOUtil.initRequete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author chris
 * */


public class PayerDaoImpl implements PayerDao {

    private static String SQL_ADD_ACHAT = "INSERT INTO achat (login, numDossier, numTicket, numSpect,jour, heure, numSalle, numRang, numPlace) VALUES (?,?,?,?,?,?,?,?, ?)";
    private static String SQL_ADD_RESERVATION = "INSERT INTO reservation (login, numSpect,jour, heure, numSalle, numRang, numPlace) VALUES (?,?,?,?,?,?,?)";
    private static String SQL_ALL_SPECTACLES = "SELECT * FROM spectacle ";
    private static String SQL_DATE_SPECTACLE = "SELECT DISTINCT prez.numSpect,prez.nbrPlace,prez.jour,prez.heure,prez.numSalle from representation prez,spectacle s where s.numSpect = ? and prez.numSpect = s.numSpect ";
    private DAOManager manager;

    public PayerDaoImpl(DAOManager gerant) {
        this.manager = gerant;
    }

    //dans le cas ou l'utilisateur souhaite payer pour les representation en parametre
    @Override
    public void creer(List<Representation> rep, List<PreReservation> preRes, String login) throws DAOException {

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connexion = manager.getConnection();
            for (int i=0;i<rep.size();i++){
            preparedStatement = initRequete(connexion, SQL_ADD_ACHAT, false, login, rep.get(i).getSpect().getNumero(),
                    rep.get(i).getJour(),rep.get(i).getHeure(), rep.get(i).getNumSalle(), 15, 9);//TODO numrang place et
            int sucess = preparedStatement.executeUpdate();//On recherche le login dans la table 
            if (sucess==0) {
                //spectacle.setErreur("<FONT COLOR=\"red\" >Le spectacle existe déjà.</FONT>");
                throw new DAOException("Echec : La reservation correspondant n'a pas été chargée");
                }
            }
            
            //new_user.setInscrit(true);

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeAll(resultSet, preparedStatement, connexion);
        }

    }
    
    @Override
    public void reserver(List<Representation> rep, List<PreReservation> preRes, String login) throws DAOException {

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connexion = manager.getConnection();
            for (int i=0;i<rep.size();i++){
            preparedStatement = initRequete(connexion, SQL_ADD_RESERVATION, false, login, rep.get(i).getSpect().getNumero(),
                    rep.get(i).getJour(),rep.get(i).getHeure(), rep.get(i).getNumSalle(), 15, 9);//TODO
            int sucess = preparedStatement.executeUpdate();//On recherche le login dans la table 
            if (sucess==0) {
                //spectacle.setErreur("<FONT COLOR=\"red\" >Le spectacle existe déjà.</FONT>");
                throw new DAOException("Echec : La reservation correspondant n'a pas été chargée");
                }
            }
            
            //new_user.setInscrit(true);

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeAll(resultSet, preparedStatement, connexion);
        }

    }
    
/*
    @Override
    public Panier afficher_panier() throws DAOException {
        List<Spectacle> spectacles_list = new ArrayList();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Spectacle festival = null;

        try {
            connexion = manager.getConnection();
            preparedStatement = initRequete(connexion, SQL_ALL_SPECTACLES, false);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                festival = this.link(resultSet);
                festival.setRepresentation(this.associer_representations(festival.getNumero()));
                spectacles_list.add(festival);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeAll(resultSet, preparedStatement, connexion);
        }
        return spectacles_list;
    }

    public Panier link(ResultSet resultSet) throws SQLException {

        Panier panier = new Panier();
        panier.setNumero(resultSet.getInt("numSpect"));
        panier.setName(resultSet.getString("nomSpect"));
        panier.setDescription(resultSet.getString("description"));

        return panier;
    }

*/


}

