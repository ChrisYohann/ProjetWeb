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
    
        protected String login ;
        protected String password ;
        private String message_erreur ;
            
        public void setLogin(String pseudo){
            this.login = pseudo ;
        }
        
        public void setPassword(String motdepasse){
            this.password = password ;
        }
        
         public void setErreur(String erreur){
            this.message_erreur = erreur ;
        }
        
        public String getLogin(){
            return this.login ;
        }
        
        public String getPassword(){
            return this.password ;
        }
        
        public String getErreur(){
            return this.message_erreur ;
        }
        
        public boolean isAdmin(){
            return false ;
        }
        
        public boolean isUtilisCoBean() {
            if(this==null) return false;
            return true;
        }
        
}