<%-- 
    Document   : jsptest1
    Created on : 20 avr. 2015, 23:10:49
    Author     : chris
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="beans.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="styles.css" rel="stylesheet">
        <link href="style.css" rel="stylesheet">
    </head>
    <body><nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-header">
                <a class=navbar-brand href="index.jsp">JMYM-theatre</a>
            </div>
            <ul class="nav navbar-nav">
                <li ><a href="index.jsp">Accueil</a></li>
                <li><a href="GestionSpectacle">Spectacle</a></li>
                <li><a href="Calendrier.jsp">Calendrier</a></li>
                <li><a href="Information.jsp">Information</a></li>
                <li><a href="Panier.jsp"><img src="image/gestion-du-panier.jpg"/></a></li>
            </ul>
                        
        <% 
        beans.UtilisateurCoBean utilisateur = (beans.UtilisateurCoBean) request.getSession(true).getAttribute("utilisateur"); 
        beans.UtilisateurInscrBean nouveaumembre = (beans.UtilisateurInscrBean) request.getSession(true).getAttribute("newuser");
        if(utilisateur != null){   
            
            out.print("<p class=\"navbar-text navbar-right\"><a class=\"navbar-link \" href=\"Logout\">Deconnexion ("+utilisateur.getLogin()+")</a></p>");
                if(utilisateur.isAdmin())
                {   out.print("<p class=\"navbar-text navbar-right\"><a class=\"navbar-link \" href=\"NewSpectacle.jsp\">Proposer Spectacle</a></p>");
                    out.print("<p class=\"navbar-text navbar-right\"><a class=\"navbar-link \" href=\"GestionMembre\">Groupes</a></p>");
                }
           }
        else if(nouveaumembre != null){
            out.print("<p class=\"navbar-text navbar-right\"><a class=\"navbar-link \" href=\"MonCompte.jsp\">Mon Compte</a></p>");
            out.print("<p class=\"navbar-text navbar-right\"><a class=\"navbar-link \" href=\"Logout\">Deconnexion ("+nouveaumembre.getLogin()+")</a></p>");
        }
        else{
            out.print("<p class=\"navbar-text navbar-right\"><a class=\"navbar-link \" href=\"Connection.jsp\">Se Connecter</a></p>");}                 
        %>
            
            
        </nav>
    </body>
</html>
