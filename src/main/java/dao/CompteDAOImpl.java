/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DAOUtil.initRequete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
 * @author igierm
 */
public class CompteDAOImpl implements CompteDAO{
  
    private static String SQL_TICKET = "INSERT INTO salle(numSalle) VALUES (?)";
    private DAOManager manager;

    
    public CompteDAOImpl(DAOManager gerant) {
        this.manager = gerant;
    }
    @Override
    public void creer() {
        

       
    }

}
