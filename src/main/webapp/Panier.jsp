<%-- 
    Document   : Panier
    Created on : 21 avr. 2015, 17:48:12
    Author     : igierm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="beans.*"%>
<%@ include file="entete.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panier</title>
    </head>
    <body>
        <%
                java.util.ArrayList<Representation> representation = new java.util.ArrayList<Representation>();
                java.util.ArrayList<Spectacle> spectacle = new java.util.ArrayList<Spectacle>();

                Representation repres1= new Representation();
                
                Spectacle spect1= new Spectacle();
                spect1.setName("Je suis un super spectacle");
                spect1.setNumero(1);
                spect1.setDescription("Ceci est un petit paragraphe "
                        + "destiné à decrire brievement cette charmante piéce de théatre");
                Spectacle spect2= new Spectacle();
                spect2.setName("Je suis un autre super spectacle");
                spect2.setNumero(2);
                repres1.setSpect(spect1);
                repres1.setJour("mardi 30 mais");
                repres1.setHeure(14);
                repres1.setNumSalle(3);
                //spect1.setAffiche()
                Representation repres2= new Representation();
                repres2.setSpect(spect1);
                repres2.setJour("jeudi 31 decembre");
                repres2.setHeure(17);
                repres2.setNumSalle(1);
                Representation repres3= new Representation();
                repres3.setSpect(spect1);
                repres3.setJour("mardi 30 mais");
                repres3.setHeure(14);
                repres3.setNumSalle(3);
                representation.add(repres1);
                representation.add(repres2);
                representation.add(repres3);
                spect1.setRepres(representation);
                spect2.setRepres(representation);
                spectacle.add(spect1);
                spectacle.add(spect2);
                request.setAttribute("representation", representation);
                request.setAttribute("spectacle", spectacle);
        %>
        <h1 class="Spect">Votre panier:</h1>
        
        <div class="row">
            
            <div class="col-md-offset-1 col-md-5">
                <form method="post" action="PayRes">
                    <c:forEach items="${spectacle}" var="spectvar" >
                        
                <artSpect class="row">
                    <input type="checkbox" name="${spectvar.getName()}" id="${spectvar.getName()}" checked>
                    <label for="${spectvar.getName()}">${spectvar.getName()}</label>
                    <p>Veuillez choisir la representation qui vous convients (et le nbr de place souhaité?)<br>
                        </p>
                        <label for="jour">Date:</label>
                        <select name="jour" id="jour">
                            <c:forEach items="${spectvar.getRepres()}" var="represvar">
                                <option value="${represvar.getJour()}">${represvar.getJour()}</option>
                            </c:forEach>
                        </select>
                        <label for="heure">Heure:</label>
                        <select name="heure" id="heure">
                            <c:forEach items="${spectvar.getRepres()}" var="represvar">
                                <option value="${represvar.getHeure()}">${represvar.getHeure()}</option>
                            </c:forEach>
                        </select>
                        <label for="salle">Salle:</label>
                        <select name="jour" id="jour">
                            <c:forEach items="${spectvar.getRepres()}" var="represvar">
                                <option value="${represvar.getNumSalle()}">${represvar.getNumSalle()}</option>
                            </c:forEach>
                        </select>
                    <br> 
                </artSpect>
                </c:forEach>
                    <br><br>
                <input class="btn btn-primary" type="submit" name="payer" value="Payer" />
                <input class="btn btn-primary" type="submit" name="reserver" value="Reserver" />

                </form>
                    </div>
    </body>
</html>
