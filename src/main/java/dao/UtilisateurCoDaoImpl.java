/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.UtilisateurCoBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static dao.DAOUtil.* ;

/**
 *
 * @author chris
 */
public class UtilisateurCoDaoImpl implements UtilisateurCoDao {
            private static final String SQL_FIND_LOGIN = "SELECT * FROM utilisateur WHERE login=? and mdpUt=?";
            private DAOManager manager ;
            
    public UtilisateurCoDaoImpl(DAOManager gerant){
        this.manager = gerant ;
    }
            
            
    @Override
    public void creer(UtilisateurCoBean utilisateur) throws DAOException {
       
    }

    @Override
    public UtilisateurCoBean trouver(String login,String password) throws DAOException {
                Connection connexion = null;
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;
                UtilisateurCoBean utilisateur = null;

    try {
        connexion = manager.getConnection();
        preparedStatement = initRequete( connexion, SQL_FIND_LOGIN, false, login,password );
        resultSet = preparedStatement.executeQuery();//On recherche le login dans la table 
        if ( resultSet.next() ) { //Si l'utilisateur est dans la table avec le bon mot de passe, on lui crée le Bean associé 
            utilisateur = this.link( resultSet );
        }
    } catch ( SQLException e ) {
        throw new DAOException( e );
    } finally {
        closeAll( resultSet, preparedStatement, connexion );
    }

    return utilisateur;
    }
    
    private static UtilisateurCoBean link( ResultSet resultSet ) throws SQLException {
    
    UtilisateurCoBean utilisateur = new UtilisateurCoBean();
    utilisateur.setLogin( resultSet.getString( "login" ) );
    utilisateur.setPassword( resultSet.getString( "mdpUt" ) );
  
    return utilisateur;
    }
    
    
}
