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
        <c:forEach items="${liste_membres}" var="membres">
            <c:out value="${membres.getLogin()}"/> 
            <form method="post" action="SetRole">
            <input type="hidden" name="login" value ="${membres.getLogin()}"/>
            <input type="hidden" name="password" value ="${membres.getPassword()}"/>
            <input type="hidden" name="role" value="membre"/>
            <input type="submit" value="Passer Membre" />
            </form>
            <form method="post" action="SetRole">
            <input type="hidden" name="login" value ="${membres.getLogin()}"/>
            <input type="hidden" name="password" value ="${membres.getPassword()}"/>
            <input type="hidden" name="role" value="admin"/>                
            <input type="submit" value="Passer Admin" />
            </form>
            <br>
        </c:forEach>
    </body>
</html>