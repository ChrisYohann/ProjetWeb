<%-- 
    Document   : Spectacle
    Created on : 21 avr. 2015, 17:05:45
    Author     : igierm
--%>

<%@page import="beans.UtilisateurCoBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="entete.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Spectacle</title>
    </head>
    <body>
        <h1 class="Spect">Spectacle</h1>
        
        <div class="row">
            <article class="col-md-offset-1 col-md-10">
                <artSpect class="row">
                    <img class="col-md-5" height=500px src="image/romeo.jpg"/>
                    <p class="col-md-5">Ceci est un petit paragraphe destiné à decrire brievement
                        cette charmante piéce de théatre<br><br>Horaire: 14h30 17h00 19h15
                        <br><br>Salle 1
                        </p>
                    <form  class="col-md-5" method="post" action="addCart">
                    <input class="btn btn-primary" type="submit" value="Ajouter au panier" />
                    </form>
                     <% if (request.getSession(true).getAttribute("utilisateur") != null && ((UtilisateurCoBean)request.getSession(true).getAttribute("utilisateur")).isAdmin())
                        {
                    out.print("<form  class=\"col-md-5\" method=\"post\" action=\"SpectacleManagementCo\">");
                    out.print("<input class=\"btn btn-primary\" type=\"submit\" value=\"Modifier\" />");
                    out.print("</form>");
                    out.print("<form  class=\"col-md-5\" method=\"post\" action=\"GestionBookings\">");
                    out.print("<input class=\"btn btn-primary\" type=\"submit\" value=\"Voir les Réservations\" />");
                    out.print("</form>");}
                    %>
                </artSpect>
                <artSpect class="row">
                    <img class="col-md-5" height=500px src="image/Scoop.jpg"/>
                    <p class="col-md-5">Ceci est un petit paragraphe destiné à decrire brievement
                        cette charmante piéce de théatre<br><br>Horaire: 14h30 17h00 19h15
                    <br><br>Salle 2</p>
                    <form  class="col-md-5" method="post" action="addCart">
                    <input class="btn btn-primary" type="submit" value="Ajouter au panier" />
                 <% if (request.getSession(true).getAttribute("utilisateur") != null && ((UtilisateurCoBean)request.getSession(true).getAttribute("utilisateur")).isAdmin())
                        {
                    out.print("<form  class=\"col-md-5\" method=\"post\" action=\"SpectacleManagementCo\">");
                    out.print("<input class=\"btn btn-primary\" type=\"submit\" value=\"Modifier\" />");
                    out.print("</form>");
                    out.print("<form  class=\"col-md-5\" method=\"post\" action=\"GestionBookings\">");
                    out.print("<input class=\"btn btn-primary\" type=\"submit\" value=\"Voir les Réservations\" />");
                    out.print("</form>");}
                    %>
                    </form>
                </artSpect>
                <artSpect class="row">
                    <img class="col-md-5" height=500px src="image/affS.jpg"/>
                    <p class="col-md-5">Ceci est un petit paragraphe destiné à decrire brievement
                        cette charmante piéce de théatre<br><br>Horaire: 14h30 17h00 19h15
                    <br><br>Salle 1</p>
                    <form  class="col-md-5" method="post" action="addCart">
                    <input class="btn btn-primary" type="submit" value="Ajouter au panier" />
                    </form>
                    <% if (request.getSession(true).getAttribute("utilisateur") != null && ((UtilisateurCoBean)request.getSession(true).getAttribute("utilisateur")).isAdmin())
                        {
                    out.print("<form  class=\"col-md-5\" method=\"post\" action=\"SpectacleManagementCo\">");
                    out.print("<input class=\"btn btn-primary\" type=\"submit\" value=\"Modifier\" />");
                    out.print("</form>");
                    out.print("<form  class=\"col-md-5\" method=\"post\" action=\"GestionBookings\">");
                    out.print("<input class=\"btn btn-primary\" type=\"submit\" value=\"Voir les Réservations\" />");
                    out.print("</form>");}
                    %>
                </artSpect>
            </article>
        </div>
    </body>
</html>
