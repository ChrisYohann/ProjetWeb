/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
 * @author chris
 */
public class SpectacleDaoImpl implements SpectacleDao {

    private static String SQL_CHECK_SPECTACLE = "SELECT * FROM spectacle where numSpect=?";
    private static String SQL_NEW_SPECTACLE = "INSERT INTO spectacle (numSpect,nomSpect,nbrPlace) VALUES (?,?,?)";
    private static String SQL_ALL_SPECTACLES = "SELECT * FROM spectacle ";
    private static String SQL_DATE_SPECTACLE = "SELECT DISTINCT prez.numSpect,prez.nbrPlace,prez.jour,prez.heure,prez.numSalle from representation prez,spectacle s where s.numSpect = ? and prez.numSpect = s.numSpect ";
    private DAOManager manager;

    public SpectacleDaoImpl(DAOManager gerant) {
        this.manager = gerant;
    }

    @Override
    public void creer(Spectacle spectacle) throws DAOException {

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connexion = manager.getConnection();
            preparedStatement = initRequete(connexion, SQL_CHECK_SPECTACLE, false, spectacle.getNumero());
            resultSet = preparedStatement.executeQuery();//On recherche le login dans la table 
            if (resultSet.next()) {
                //spectacle.setErreur("<FONT COLOR=\"red\" >Le spectacle existe déjà.</FONT>");
                throw new DAOException("Echec : Le spectacle existe déjà");
            }
            preparedStatement = initRequete(connexion, SQL_NEW_SPECTACLE, true, spectacle.getNumero(), spectacle.getName(), spectacle.getDescription());
            int success = preparedStatement.executeUpdate();
            if (success == 0) {
                //new_user.setErreur("Erreur survenue. Veuillez réessayer dans quelques instants.");
                throw new DAOException("Echec d'ajout du spectacle dans la table");

            }
            //new_user.setInscrit(true);

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeAll(resultSet, preparedStatement, connexion);
        }

    }

    @Override
    public Spectacle trouver(int numSpect) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Spectacle fiesta = null;

        try {
            connexion = manager.getConnection();
            preparedStatement = initRequete(connexion, SQL_CHECK_SPECTACLE, false, numSpect);
            resultSet = preparedStatement.executeQuery();//On recherche le spectacle
            if (resultSet.next()) {
                  fiesta = this.link(resultSet) ;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeAll(resultSet, preparedStatement, connexion);
        }
        return fiesta ;
    }

    @Override
    public List<Spectacle> afficher_spectacles() throws DAOException {
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

    public List<Representation> associer_representations(int numSpect) {
        List<Representation> spectacles_list = new ArrayList();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Representation prez = new Representation();
        

        try {
            connexion = manager.getConnection();
            preparedStatement = initRequete(connexion, SQL_DATE_SPECTACLE, false, numSpect);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                prez.setSpect(this.trouver(resultSet.getInt("numSpect")));
                prez.setJour((Date)resultSet.getDate("jour"));
                prez.setHeure(resultSet.getInt("heure"));
                prez.setNumSalle(resultSet.getInt("numSalle"));
                spectacles_list.add(prez);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeAll(resultSet, preparedStatement, connexion);
        }
        return spectacles_list;
    }

    public Spectacle link(ResultSet resultSet) throws SQLException {

        Spectacle spectacle = new Spectacle();
        spectacle.setNumero(resultSet.getInt("numSpect"));
        spectacle.setName(resultSet.getString("nomSpect"));
        spectacle.setDescription(resultSet.getString("description"));

        return spectacle;
    }

}
