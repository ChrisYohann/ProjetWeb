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
public class UtilisateurInscrBean {
    
        private String nom ;
        private String prenom ;
        private String login ;
        private String password ;
        private String email ;
        private String message_erreur ;
        private boolean inscrit ;
        
        public UtilisateurInscrBean(){
            this.inscrit = false ;
        }
         
        public void setNom(String name){
            this.nom = name ;
        }
        
        public void setPrenom(String surname){
            this.prenom = surname ;
        }
        
        public void setEmail(String courriel){
            this.email = courriel ;
        }
        
        public void setLogin(String pseudo){
            this.login = pseudo ;
        }
        
        public void setPassword(String motdepasse){
            this.password = motdepasse ;
        }
        
        public void setInscrit(boolean value){
            this.inscrit = value ;
        }
        
        public void setErreur(String erreur){
            this.message_erreur = erreur ;
        }
        
        public String getNom(){
            return this.nom ;
        }
        
        public String getPrenom(){
            return this.prenom ;
        }
        
        public String getEmail(){
            return this.email ;
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
        
        public boolean getInscrit(){
            return this.inscrit ;
        }
    
        
        
}
