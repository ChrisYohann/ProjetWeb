/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author chris
 */

public class DAOConfigurationException extends RuntimeException {
    
        public DAOConfigurationException( String message ) {
            super( message );
        }

        public DAOConfigurationException( String message, Throwable cause ) {
          super( message, cause );
        }

        public DAOConfigurationException( Throwable cause ) {
          super( cause );
        }
}
    