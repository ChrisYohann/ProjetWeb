<%-- 
    Document   : Panier
    Created on : 21 avr. 2015, 17:48:12
    Author     : igierm
--%>
<%--
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
                repres1.setJour(new Date());
                repres1.setHeure(14);
                repres1.setNumSalle(3);
                //spect1.setAffiche()
                Representation repres2= new Representation();
                repres2.setSpect(spect1);
                repres2.setJour(new Date());
                repres2.setHeure(17);
                repres2.setNumSalle(1);
                Representation repres3= new Representation();
                repres3.setSpect(spect1);
                repres3.setJour(new Date());
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
                    <input type="checkbox" name="${represvar.getSpect().getName()}" id="${represvar.getSpect().getName()}" checked>
                    <label for="${represvar.getSpect().getName()}">${represvar.getSpect().getName()}</label>
                    <p>Vous avez choisis d'assister à la representation du ${represvar.afficherDate()} à ${represvar.getHeure()}h en Salle ${represvar.getNumSalle()}
                    </p>
                        
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
                spect2.setName("Je suis un spectacle de merde");
                spect2.setNumero(2);
                repres1.setSpect(spect1);
                repres1.setJour(new Date());
                repres1.setHeure(14);
                repres1.setNumSalle(3);
                //spect1.setAffiche()
                Representation repres2= new Representation();
                repres2.setSpect(spect1);
                repres2.setJour(new Date());
                repres2.setHeure(17);
                repres2.setNumSalle(1);
                Representation repres3= new Representation();
                repres3.setSpect(spect1);
                repres3.setJour(new Date());
                repres3.setHeure(14);
                repres3.setNumSalle(3);
                representation.add(repres1);
                representation.add(repres2);
                representation.add(repres3);
                spect1.setRepresentation(representation);
                spect2.setRepresentation(representation);
                spectacle.add(spect1);
                spectacle.add(spect2);
                request.setAttribute("representation", representation);
                request.setAttribute("spectacle", spectacle);
        %> 
        
        <h1 class="Spect">Votre panier:</h1>
        
        <div class="row">
            
            <div class="col-md-offset-1 col-md-5">
                <form method="post" action="PayRes">
                    <%--       <c:forEach items="${spectacle}" var="spectvar" >
                        --%>
                <artSpect class="row">
                    <input type="checkbox" name="spect 1" id="idspect" checked>
                    <label for="nomspectacle">spect 1</label>
                    <br>
                        <label for="jour">Nombre de place</label>
                        <input type="number" name="nbmplace1" id="nbrplace" min="1" max="10" value="1"> <SELECT name="categorie de spect1"  size="1">
<OPTION>orchestre
<OPTION>balcon
<OPTION>poulallier

</SELECT>

<artSpect class="row">
                    <input type="checkbox" name="spect 2" id="idspect" checked>
                    <label for="nomspectacle">spect 2</label>
                    <br>
                        <label for="jour">Nombre de place</label>
                        <input type="number" name="nbmplace2" id="nbrplace" min="1" max="10" value="1"> <SELECT name="categorie de spect2"  size="1">
<OPTION>orchestre
<OPTION>balcon
<OPTION>poulallier

</SELECT>
                        <%--    <c:forEach items="${spectvar.getRepresentation()}" var="represvar">
                                
                            </c:forEach> --%>
                        
                        </select>
                        
                    <br> 
                </artSpect>
                
                    <br><br>
                <input class="btn btn-primary" type="submit" name="payer" value="Payer" />
                <input class="btn btn-primary" type="submit" name="reserver" value="Reserver" />

                </form>
                    </div>
    </body>
</html>
