<%-- 
    Document   : LogSuccessful
    Created on : 22 avr. 2015, 15:56:26
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="entete.jsp" %>
<!DOCTYPE html>
<html>
    <body>
       <body>
       <center>Bienvenue <%= ((beans.UtilisateurCoBean)request.getAttribute("utilisateur")).getLogin() %>, Vous allez être redirigé vers la page d'accueil </p></center>
        <div><h6><meta http-equiv="refresh" content="3; URL=index.jsp"></h6></div>
    </body> 
    </body>
</html>
