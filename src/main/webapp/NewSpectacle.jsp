<%-- 
    Document   : NewSpectacle
    Created on : 25 avr. 2015, 00:36:31
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="entete.jsp" %>

<!DOCTYPE html>
<html>
    <body>
        <form  class="col-md-5" method="post" action="SetSpectacleCo">
        <label for="nomSpect">Nom du Spectacle :</label>
        <p><input type="text" name="nomSpect" /></p>
        <label for="description">Description (max 200 caractères) :</label>
        <p><TEXTAREA name="description" rows=10 COLS=40 maxlength="200"></TEXTAREA></p>
        <label for="affiche">Affiche (sans l'extension .jpg):</label>
        <p><input type="text" name="affiche" /></p>
        <br>
        <input class="btn btn-primary" type="submit" name="creer" value="Représentations" />      
        </form>
    </body>
</html>
