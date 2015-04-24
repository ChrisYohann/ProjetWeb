/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import dao.SetRoleDao;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author chris
 */
public class MetierSetRole {
    
    private SetRoleDao maitre ;
    
    public MetierSetRole(SetRoleDao master){
        this.maitre = master ;
    }
    
    public String changer_role(HttpServletRequest request){
           String user = request.getParameter("login");
           String pass = request.getParameter("password");
           String deroulement ;
           
           
           if(request.getParameter("role").equals("Passer Membre")){
               deroulement = maitre.update_role(user, pass, false) ;
           }
          
           else{
               deroulement = maitre.update_role(user, pass, true) ;
           }
           
           return deroulement ;
        
    }
    
    
    
}
