<%-- 
    Document   : jsptest1
    Created on : 20 avr. 2015, 23:10:49
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../styles.css" rel="stylesheet">
        <link href="../style.css" rel="stylesheet">
    </head>
    <body><nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-header">
                <a class=navbar-brand href="index.jsp">JMYM-theatre</a>
            </div>
            <ul class="nav navbar-nav">
                <li ><a href="../index.jsp">Accueil</a></li>
                <li><a href="../Spectacle.jsp">Spectacle</a></li>
                <li><a href="../Information.jsp">Information</a></li>
                <li><a href="../Panier.jsp"><img src="../image/gestion-du-panier.jpg"/>
            </ul>
          
            <%--${empty utilisateur.login ? "<p class=\"navbar-text navbar-right\"><a class=\"navbar-link \" href=\"Connection.html\">Se Connecter</a></p>" : "Bonjour, ${utilisateur.login}"}--%>
           
            
           <p class="navbar-text navbar-right"><a class="navbar-link " href="../Connection.jsp">Se Connecter</a></p>
        </nav>
    </body>
</html>
