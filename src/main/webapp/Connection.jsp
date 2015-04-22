<%-- 
    Document   : Connection
    Created on : 20 avr. 2015, 23:23:48
    Author     : chris
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="entete.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Se connecter</title>
    </head>
    <body>
         
        <h1 class="Spect">Se connecter</h1>
        
        <div class="row">
            
            <div class="col-md-offset-1 col-md-4">
                <div id="seCo">
                <form class="well" method="post" action="CustomerCo">
                
                    <legend>Bonjour, comment allez vous?</legend>
                    <div class="form-group">
                    
                    <label  for="login">Login</label> : 
                    <input class="form-control" type="text" name="login" id="login" size="25" 
                           maxlength="20" required/>
                    
                    <label  for="mdp">Mot de passe</label> : 
                    <input  class="form-control" type="password" name="mdp" id="mdp" size="25" 
                           maxlength="20" required/>
                    
                    <br>
                    <div class="col-md-offset-3">
                    <input id="bout" class="btn btn-primary" type="submit" value="Se Connecter" />
                    <br>
                    </div>
                    <br>
                    <a href="MotDePasseOublie.html">Mot de passe oublié</a>
                    </div>
            </form>
            </div>
            </div>
            <div class="col-md-offset-1 col-md-5">
                <div class="row">
                <form class="well" method="post" action="ControleurInscr">
                <p>
                    <legend>Vous n'étes pas encore inscrit?</legend>
                    <div class="form-group">
                    <label for="nom">Nom</label> : 
                    <input class="form-control" type="text" name="nom" id="nom" size="25" 
                           maxlength="20" required/>
                    
                    <label  for="prenom">Prenom</label> : 
                    <input class="form-control" type="text" name="prenom" id="prenom" size="25" 
                           maxlength="20" required/>
                    
                    <label  for="login">Login</label> : 
                    <input class="form-control" type="text" name="login" id="login" size="25" 
                           maxlength="20" required/>
                    
                    <label  for="mdp">Mot de passe</label> : 
                    <input  class="form-control" type="password" name="mdp" id="mdp" size="25" 
                           maxlength="20" required/>
                    
                    <label  for="mail">e-mail</label> : 
                    <input class="form-control" type="email" name="mail" id="mail" size="25" 
                           maxlength="20"/>
                    <br>
                    <div class="col-md-offset-5">
                    <input class="btn btn-primary" type="submit" value="Envoyer" />
                    <br>
                    </div>
                    </div>
                
            </form>
            </div>
            </div>
        </div>
    </body>
</html>
