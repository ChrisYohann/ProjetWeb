/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.UtilisateurInscrBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.* ;
import static dao.DAOUtil.* ;

/**
 *
 * @author chris
 */
public class UtilisateurInscrDaoImpl implements UtilisateurInscrDao {
            private static final String SQL_SIGN_UP = "INSERT INTO utilisateur (login, nomUt, prenomUt,AdresseUt,mdpUt) VALUES (?,?,?,?,?);";
            private static final String SQL_CHECK_IN = "SELECT * FROM utilisateur where login=?" ;
            private static final String SQL_ALL_MEMBERS = "SELECT * FROM utilisateur ;";
            private DAOManager manager ;
            
    public UtilisateurInscrDaoImpl(DAOManager gerant){
        this.manager = gerant ;
    }
            
            
    @Override
    public void creer(UtilisateurInscrBean new_user) throws DAOException {
        
        Connection connexion = null;
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;
                UtilisateurInscrBean utilisateur = null;
                
                  try {
        connexion = manager.getConnection();
        preparedStatement = initRequete( connexion, SQL_CHECK_IN, false,new_user.getLogin());
        resultSet = preparedStatement.executeQuery();//On recherche le login dans la table 
        if ( resultSet.next()) { //Si un tel pseudo est déjà dans la table, ou ça bug pour une erreur quelconque
            
            new_user.setErreur("<FONT COLOR=\"red\" >Le pseudo existe déjà.</FONT>");
            throw new DAOException("Echec de l'inscription L'utilisateur existe déjà") ;
        }
        preparedStatement = initRequete( connexion, SQL_SIGN_UP, true,new_user.getLogin(),new_user.getNom(),new_user.getPrenom(),new_user.getEmail(),new_user.getPassword());
        int success = preparedStatement.executeUpdate();
        if(success == 0){
           new_user.setErreur("Erreur survenue. Veuillez réessayer dans quelques instants.");
            throw new DAOException("Echec d'ajout de l'utilisateur dans la table");
            
        }
        new_user.setInscrit(true);
        
    } catch ( SQLException e ) {
        throw new DAOException( e );
    } finally {
        closeAll( resultSet, preparedStatement, connexion );
    }

       
    }
    
    public List<UtilisateurInscrBean> trouver() throws DAOException{
        List<UtilisateurInscrBean> members_list = new ArrayList();
                Connection connexion = null;
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;
                UtilisateurInscrBean utilisateur = null;
                
                  try {
        connexion = manager.getConnection();
        preparedStatement = initRequete( connexion, SQL_ALL_MEMBERS,false);
        resultSet = preparedStatement.executeQuery();
        while ( resultSet.next()) { 
            members_list.add(this.link(resultSet));
        }
        
    } catch ( SQLException e ) {
        throw new DAOException( e );
    } finally {
        closeAll( resultSet, preparedStatement, connexion );
    }
        return members_list ;
    }

    
   private UtilisateurInscrBean link( ResultSet resultSet ) throws SQLException {
    
    UtilisateurInscrBean utilisateur = new UtilisateurInscrBean();
    utilisateur.setLogin( resultSet.getString( "login" ) );
    utilisateur.setPassword( resultSet.getString( "mdpUt" ) );
    utilisateur.setNom(resultSet.getString("nomUt"));
    utilisateur.setPrenom(resultSet.getString("prenomUt"));
    utilisateur.setEmail(resultSet.getString("AdresseUt"));
    utilisateur.setInscrit(true);
  
    return utilisateur;
    }
    
    
}
