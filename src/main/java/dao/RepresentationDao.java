/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Representation;
import java.util.*;

/**
 *
 * @author chris
 */
public interface RepresentationDao {
    
    public void creer(Representation presentation,String jour) throws DAOException ;
    
    public Representation trouver(String jour,int heure,int numSalle) throws DAOException ;
    
   
    
}
