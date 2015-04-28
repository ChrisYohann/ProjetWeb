/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Representation;
import static dao.DAOUtil.closeAll;
import static dao.DAOUtil.initRequete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author chris
 */
public class RepresentationDaoImpl implements RepresentationDao {

    private static String SQL_NEW_PREZ = "INSERT INTO representation (numSpect,nbrPlace,jour,heure,numSalle,dernierPO,dernierPP,dernierPB,dernierRO,dernierRP,dernierRB) VALUES (?,?,?,?,?,0,0,0,1,6,13)";
    private static String SQL_CHECK_ONLY = "SELECT * from representation where jour = ? and heure = ? and numSalle = ?";
    private static String UTF8 = "set NAMES 'utf8'";
    
    private DAOManager manager;

    public RepresentationDaoImpl(DAOManager gerant) {
        this.manager = gerant;
    }

    @Override
    public void creer(Representation presentation, String jour) throws DAOException {
        InsertDaoImpl initialize = new InsertDaoImpl(this.manager);
        Representation festival;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            initialize.creer();

            festival = this.trouver(jour, presentation.getHeure(), presentation.getNumSalle());
            connexion = manager.getConnection();
            if (festival != null) {
                presentation.setErreur("<FONT COLOR=\"red\" >Une autre représentation est déjà programmée.</FONT>");
                throw new DAOException("Impossible de creer representation : Une autre representation est déjà programmée.");
            }
            preparedStatement = initRequete(connexion,UTF8,true) ;
            int statut = preparedStatement.executeUpdate();
            preparedStatement = initRequete(connexion, SQL_NEW_PREZ, true, presentation.getSpect().getNumero(), presentation.getNbrPlace(), jour, presentation.getHeure(), presentation.getNumSalle());
            int success = preparedStatement.executeUpdate();
            if (success == 0) {
                throw new DAOException("Une erreur est survenue. Veuillez réessayer ultérieurement.");
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeAll(resultSet, preparedStatement, connexion);
        }

    }

    @Override
    public Representation trouver(String jour, int heure, int numSalle) {
        Representation festival = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        SpectacleDaoImpl caissier = new SpectacleDaoImpl(this.manager);

        try {
            connexion = manager.getConnection();
            preparedStatement = initRequete(connexion, SQL_CHECK_ONLY, false, jour, heure, numSalle);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                festival = new Representation();
                festival.setSpect(caissier.trouver(resultSet.getInt("numSpect")));
                festival.setNbrPlace(resultSet.getInt("nbrPlace"));
                festival.setJour(resultSet.getDate("jour"));
                festival.setHeure(heure);
                festival.setNumSalle(numSalle);
                festival.setDernierPB(resultSet.getInt("dernierPB"));
                festival.setDernierPP(resultSet.getInt("dernierPP"));
                festival.setDernierPO(resultSet.getInt("dernierPO"));
                festival.setDernierRB(resultSet.getInt("dernierRB"));
                festival.setDernierRP(resultSet.getInt("dernierRP"));
                festival.setDernierRO(resultSet.getInt("dernierRO"));
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeAll(resultSet, preparedStatement, connexion);
        }
        return festival;
    }

}
