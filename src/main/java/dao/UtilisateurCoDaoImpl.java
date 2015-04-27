/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.ProgrammeurCoBean;
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
            private static final String SQL_FIND_ADMIN = "SELECT * FROM programmeur WHERE login=? and mdpUt=?";
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
        preparedStatement = initRequete( connexion, SQL_FIND_ADMIN, false, login,password );
        resultSet = preparedStatement.executeQuery();//On recherche le login dans la table des admins 
        
        if ( resultSet.next() ) 
           { utilisateur = this.link( resultSet,false );}            
        
        
        else{  preparedStatement = initRequete( connexion, SQL_FIND_LOGIN, false, login,password );
                resultSet = preparedStatement.executeQuery();//On recherche le login dans la table des clients
                if ( resultSet.next() )  
                 { utilisateur = this.link( resultSet,true );}  
                    
            }    
            
    } catch ( SQLException e ) {
        throw new DAOException( e );
    } finally {
        closeAll( resultSet, preparedStatement, connexion );
    }

    return utilisateur;
    }
    
    private static UtilisateurCoBean link( ResultSet resultSet,boolean value ) throws SQLException {
        UtilisateurCoBean utilisateur = null ;
    if(value)
     utilisateur = new UtilisateurCoBean();
    else{ utilisateur = new ProgrammeurCoBean();}
    utilisateur.setLogin( resultSet.getString( "login" ) );
    utilisateur.setPassword( resultSet.getString( "mdpUt" ) );
   
    return utilisateur;
    }
    
    
}
