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
public interface SetRoleDao {
    
    public String update_role(String user,String pass,boolean value) throws DAOException ;
        
    
    
}
