/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.UtilisateurInscrBean;
import static dao.DAOUtil.closeAll;
import static dao.DAOUtil.initRequete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author chris
 */
public class SetRoleDaoImpl implements SetRoleDao {

    private static final String SQL_FIND_MEMBRE = "SELECT * FROM utilisateur WHERE login=? and mdpUt=?";
    private static final String SQL_FIND_ADMIN = "SELECT * FROM programmeur WHERE login=? and mdpUt=?";
    private static final String SQL_PASSER_MEMBRE = "DELETE FROM programmeur WHERE login=?";
    private static final String SQL_PASSER_ADMIN = "INSERT INTO programmeur (login, nomUt, prenomUt,AdresseUt,mdpUt) VALUES (?,?,?,?,?);";

    private DAOManager manager;

    public SetRoleDaoImpl(DAOManager gerant) {
        this.manager = gerant;
    }

    @Override
    public String update_role(String user, String pass, boolean value) throws DAOException {
        String deroulement = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UtilisateurInscrBean utilisateur = null;
        UtilisateurInscrDaoImpl tool = new UtilisateurInscrDaoImpl(this.manager);

        try {
            connexion = manager.getConnection();
            preparedStatement = initRequete(connexion, SQL_FIND_MEMBRE, false, user, pass);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                utilisateur = tool.link(resultSet);
                if (value) {
                    preparedStatement = initRequete(connexion, SQL_PASSER_ADMIN, true, user, utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), pass);
                    int success = preparedStatement.executeUpdate();
                    if (success == 0) {
                        deroulement = "<FONT COLOR=\"red\" >L'utilisateur est déjà Administrateur</FONT>";
                        throw new DAOException("L'utilisateur est déjà Administrateur.");
                    }
                } else {
                    preparedStatement = initRequete(connexion, SQL_PASSER_MEMBRE, true, user);
                    int success = preparedStatement.executeUpdate();
                    if (success == 0) {
                        deroulement = "<FONT COLOR=\"red\" >L'utilisateur est déjà Membre.</FONT>";
                        throw new DAOException("L'utilisateur est déjà Membre.");
                    }

                }
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeAll(resultSet, preparedStatement, connexion);
        }
        deroulement = "Changement effectué.";
        return deroulement;
    }

}
