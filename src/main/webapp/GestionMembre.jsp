<%-- 
    Document   : GestionMembre
    Created on : 23 avr. 2015, 11:00:21
    Author     : chris
--%>
<%@ page import="beans.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="entete.jsp" %>
<!DOCTYPE html>
<html>
    <body>
        <h2>Liste des Membres :</h2>
        <c:out value="${deroulement}"/>
        <c:set var="deroulement" scope="session" value=""/>
        <br>
        <div class="row">
            <gestion class="col-md-offset-1 col-md-10">
        <c:forEach items="${liste_membres}" var="membres">
            <div id="blocgestion">
            <artGestion class="row">
                <p class="col-md-4">L'utilisateur ${membres.getLogin()}</p>          
            <c:if test = "${utilisateur.getLogin() ne membres.getLogin()}">
            <c:if test = "${utilisateur.getLogin() ne root}">    
            <form method="post" action="SetRole">
            <input  type="hidden" name="login" value ="${membres.getLogin()}"/>
            <input  type="hidden" name="password" value ="${membres.getPassword()}"/>
            <input type="submit" name="role" value="Passer Membre" />             
            <input type="submit" name="role"  value="Passer Admin" />
            </form>
            </c:if>
            </c:if>
            <p></p>
            <br>
            <br>
            </artgestion>
            </div>
        </c:forEach>
            </gestion>
         </div>
    </body>
</html>