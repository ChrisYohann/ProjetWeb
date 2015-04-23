<%-- 
    Document   : Spectacle
    Created on : 21 avr. 2015, 17:05:45
    Author     : igierm
--%>

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
        <%
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
                spect3.setDescription("Ceci est un petit paragraphe "
                        + "destiné à decrire brievement cette charmante piéce de théatre");
                spect3.setHoraire("Horaire: 14h30 17h00 19h15");
                spect3.setNumSalle(1);
                spectacle.add(spect1);
                spectacle.add(spect2);
                spectacle.add(spect3);
                request.setAttribute("spectacle", spectacle);
        %>
        <h1 class="Spect">Spectacle</h1>
        
        <div class="row">
            <article class="col-md-offset-1 col-md-10">
                <% for ( i=0; i<spectacle.size(); i++) {%>
                <artSpect class="row">
                    <img class="col-md-5" height=500px src="image/romeo.jpg"/>
                    <p class="col-md-5">${spectacle.get(i).getDescription()}<br><br>${spectacle.get(i).getHoraire()}
                        <br><br>Salle ${spectacle.get(i).getNumSalle()}
                        </p>
                    <form  class="col-md-5" method="post" action="addCart">
                        <label for="nbrplace"></label><br>
                        <input type="number" name="nbrplace" id="nbrplace" min="1" max="10" value="1">
                    <input class="btn btn-primary" type="submit" name=${spectacle.get(i).getNumero()} value="Ajouter au panier" />
                    <br>
                    </form>    
                </artSpect>
                        <% }%>
                        <%--
                <artSpect class="row">
                    <img class="col-md-5" height=500px src="image/Scoop.jpg"/>
                    <p class="col-md-5">Ceci est un petit paragraphe destiné à decrire brievement
                        cette charmante piéce de théatre<br><br>Horaire: 14h30 17h00 19h15
                    <br><br>Salle 2</p>
                    <form  class="col-md-5" method="post" action="addCart">
                        <input type="number" name="nbrplace" id="nbrplace" min="1" max="10" value="1">
                    <input class="btn btn-primary" type="submit" value="Ajouter au panier" />
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
                </artSpect>
                        --%>
            </article>
        </div>
    </body>
</html>
