/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author chris
 */
public class UtilisateurCoBean {
    
        private String login ;
        private String password ;
        
        public UtilisateurCoBean(){
            this.login = null ;
            this.password = null ;
        }
    
        public void setLogin(String pseudo){
            this.login = pseudo ;
        }
        
        public void setPassword(String motdepasse){
            this.password = password ;
        }
        
        
        public String getLogin(){
            return this.login ;
        }
        
        public String getPassword(){
            return this.password ;
        }
}