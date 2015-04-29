<%-- 
    Document   : Spectacle
    Created on : 21 avr. 2015, 17:05:45
    Author     : igierm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="beans.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="entete.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="Spectacle.js"></script>
        <title>Spectacle</title>
    </head>
    <body>
        <%-- <%
                 java.util.List<Spectacle> spectacle = new java.util.ArrayList<Spectacle>();
                 Spectacle spect1= new Spectacle();
                 int i=0;
                 spect1.setName("Je suis un super spectacle");
                 spect1.setNumero(1);
                 spect1.setDescription("Ceci est un petit paragraphe "
                         + "destiné à decrire brievement cette charmante piéce de théatre");
                 spect1.setHoraire("Horaire: 14h30 17h00 19h15");
                 spect1.setNumSalle(3);
                 //spect1.setAffiche()
                 Spectacle spect2= new Spectacle();
                 spect2.setName("Je suis un autre super spectacle");
                 spect2.setNumero(2);
                 spect2.setDescription("Ceci est un petit paragraphe "
                         + "destiné à decrire brievement cette charmante piéce de théatre");
                 spect2.setHoraire("Horaire: 14h30 17h00 19h15");
                 spect2.setNumSalle(1);
                 Spectacle spect3= new Spectacle();
                 spect3.setName("Je suis le meilleur spectacle");
                 spect3.setNumero(3);
                 spect3.setDescription("bla bla Ceci est un petit paragraphe "
                         + "destiné à decrire brievement cette charmante piéce de théatre");
                 spect3.setHoraire("Horaire: 14h30 17h00 19h15");
                 spect3.setNumSalle(1);
                 spectacle.add(spect1);
                 spectacle.add(spect2);
                 spectacle.add(spect3);
                 request.setAttribute("spectacle", spectacle);
         %>--%>
        <h1 class="Spect">Spectacle</h1>
     
<p>  <span id='tab'/> Recherche Spectacle</p>
        <div class="row">
            <article class="col-md-offset-1 col-md-10">
                <c:forEach items="${liste_spectacles}" var="spectvar" >

                <artSpect class="row">  
                    <img class="col-md-5" height=500px src="image/${spectvar.getAffiche()}.jpg"/>
                    <p class="col-md-5">${spectvar.getName()}<br><br>
                        <br><br>${spectvar.getDescription()}
                        
                    <form  class="col-md-5" method="post" action="addCart">
                        <label  for="jour">Date:</label>
                        <select name="jour" id="jour">
                            
                            <c:forEach items="${spectvar.getRepresentation()}" var="represvar">
                                
                                    
                                <option value="${represvar.getJour()} ${represvar.getHeure()} ${represvar.getNumSalle()}">${represvar.afficherDate()}, à ${represvar.getHeure()}h en Salle ${represvar.getNumSalle()}, nombre de palces restantes : ${represvar.getNbrPlace()}</option>
                               
                            </c:forEach>
                        </select> 
                        
                         <c:forEach items="${spectvar.getRepresentation()}" var="representation">
                         

                                  <br></c:forEach> 
                       
                        </p>

                                <label for="nbrplace"> Nombre de Place: </label>

                                <input type="number" name="nbrplace ${spectvar.getNumero()}" id="nbrplace" min="1" max="10" value="1"><SELECT name="categorie de ${spectvar.getNumero()}"  size="1">

                                <OPTION>orchestre
                                <OPTION>balcon
                                <OPTION>poulailler

                            </SELECT>
                            <br><br>

                            <input class="btn btn-primary" type="submit" name="ajout de ${spectvar.getNumero()}" value="Ajouter au panier" /></form> 
                            <c:if test="${not empty utilisateur}">
                                <c:if test="${utilisateur.isAdmin()}">
                                    <form  class="col-md-5" method="post" action="SpectacleManagementCo">
                                        <input class="btn btn-primary" type="submit" name="modifier ${spectvar.getNumero()}" value="Ajouter une représentation pour ce spectacle" />
                                    </form>
                                   <form  class="col-md-5" method="post" action="GestionBookings">
                                        <input class="btn btn-primary" type="submit" name="gestion ${spectvar.getNumero()}" value="Voir les Réservations" />
                                    </form>
                                    
                                    
                                    
                                </c:if>    
                            </c:if>
                            <br>
                          
                    </artSpect>
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
