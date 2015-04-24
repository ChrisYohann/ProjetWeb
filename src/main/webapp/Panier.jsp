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
                java.util.List<Representation> representation = new java.util.ArrayList<Representation>();
                Representation repres1= new Representation();
                
                Spectacle spect1= new Spectacle();
                spect1.setName("Je suis un super spectacle");
                spect1.setNumero(1);
                spect1.setDescription("Ceci est un petit paragraphe "
                        + "destiné à decrire brievement cette charmante piéce de théatre");
                
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
                request.setAttribute("representation", representation);
        %>
        <h1 class="Spect">Votre panier:</h1>
        
        <div class="row">
            
            <div class="col-md-offset-1 col-md-5">
                <form method="post" action="PayRes">
                    <c:forEach items="${representation}" var="represvar" >
                        
                <artSpect class="row">
                    <input type="checkbox" name="${represvar.getSpect().getName()}" id="${represvar.getSpect().getName()}">
                    <label for="${represvar.getSpect().getName()}">${represvar.getSpect().getName()}</label>
                    <p>Vous avez reservé la representation du ${represvar.getJour()} à ${represvar.getHeure()} dans la salle ${represvar.getNumSalle()}<br>
                        
                        </p>
                    <form  class="col-md-5" method="post" action="addCart">
                    <br> 
                </artSpect>
                </c:forEach>
                <input class="btn btn-primary" type="submit" name="payer" value="Payer" />
                <input class="btn btn-primary" type="submit" name="reserver" value="Reserver" />

                </form>
                    </div>
    </body>
</html>
