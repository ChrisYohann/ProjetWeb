/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author chris
 */
public class DAOUtil {
    
    public static PreparedStatement initRequete( Connection connexion, String requete_sql, boolean returnGeneratedKeys, Object... parametres ) throws SQLException {
    PreparedStatement preparedStatement = connexion.prepareStatement( requete_sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);//TODO modifié pour oracle
                            for ( int i = 0; i < parametres.length; i++ ) {
                            preparedStatement.setObject( i + 1, parametres[i] );
                                }
      return preparedStatement;
    }
    
    /* Fermeture silencieuse du resultset */
public static void closeResultSet( ResultSet resultSet ) {
    if ( resultSet != null ) {
        try {
            resultSet.close();
        } catch ( SQLException e ) {
            System.out.println( "Échec de la fermeture du ResultSet : " + e.getMessage() );
        }
    }
}

/* Fermeture silencieuse du statement */
public static void closeStatement( Statement statement ) {
    if ( statement != null ) {
        try {
            statement.close();
        } catch ( SQLException e ) {
            System.out.println( "Échec de la fermeture du Statement : " + e.getMessage() );
        }
    }
}

/* Fermeture silencieuse de la connexion */
public static void closeConnection( Connection connexion ) {
    if ( connexion != null ) {
        try {
            connexion.close();
        } catch ( SQLException e ) {
            System.out.println( "Échec de la fermeture de la connexion : " + e.getMessage() );
        }
    }
}

/* Fermetures silencieuses du statement et de la connexion */
public static void closeStatConnect( Statement statement, Connection connexion ) {
    closeStatement( statement );
    closeConnection( connexion );
}

/* Fermetures silencieuses du resultset, du statement et de la connexion */
public static void closeAll( ResultSet resultSet, Statement statement, Connection connexion ) {
    closeResultSet( resultSet );
    closeStatement( statement );
    closeConnection( connexion );
}
    
}
