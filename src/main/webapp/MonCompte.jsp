<%-- 
    Document   : MonCompte
    Created on : 22 avr. 2015, 16:55:34
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="entete.jsp" %>
<!DOCTYPE html>
<html>
    <body>
        <h1 class="Spect">Votre Compte:</h1>

        <div class="row">
            <article class="col-md-offset-1 col-md-10">
                <c:forEach items="${liste_comptes}" var="cvar" >

                    <p>dans votre compte monsieur ${cvar.getLogin()}</p>  
                </c:forEach>
                <%--
        <artSpect class="row">
            <img class="col-md-5" height=500px src="image/Scoop.jpg"/>
            <p class="col-md-5">Ceci est un petit paragraphe destiné à decrire brievement
                cette charmante piéce de théatre<br><br>Horaire: 14h30 17h00 19h15
            <br><br>Salle 2</p>
            <form  class="col-md-5" method="post" action="addCart">
                <input type="number" name="nbrplace" id="nbrplace" min="1" max="10" value="1">
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
                <input type="number" name="nbrplace" id="nbrplace" min="1" max="10" value="1">
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
                --%>
            </article>
        </div>
    </body>
</html>

