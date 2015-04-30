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
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jm
 */
public class ManagementRepresDaoImpl implements ManagementRepresDao {

    private static String SPECT_FIND = "SELECT nomSpect from spectacle where numSpect=?";
    private static String SQL_NEW_PREZ = "INSERT INTO representation (numSpect,nbrPlace,jour,heure,numSalle,dernierPO,dernierPP,dernierPB,dernierRO,dernierRP,dernierRB) VALUES (?,?,?,?,?,0,0,0,1,6,13)";

    private DAOManager manager;

    public ManagementRepresDaoImpl(DAOManager gerant) {
        this.manager = gerant;
    }

    public void creer(Representation presentation, String jour) {

        InsertDaoImpl initialize = new InsertDaoImpl(this.manager);
        Representation festival;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        
        
        try {
            initialize.creer();

            connexion = manager.getConnection();
            RepresentationDao maanager = new RepresentationDaoImpl(manager) ;
            festival = maanager.trouver(jour, presentation.getHeure(), presentation.getNumSalle());
            connexion = manager.getConnection();
            if (festival != null) {
                presentation.setErreur("<FONT COLOR=\"red\" >Une autre représentation est déjà programmée.</FONT>");
                throw new DAOException("Impossible de creer representation : Une autre representation est déjà programmée.");
            }
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

    public String trouver_nom(int spect) throws SQLException {

        String rep = "";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connexion = manager.getConnection();
            preparedStatement = initRequete(connexion, SPECT_FIND, true, spect);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                rep = resultSet.getString("nomSpect");
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeAll(resultSet, preparedStatement, connexion);
        }

        return rep;

    }

}
