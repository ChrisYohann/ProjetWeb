<%-- 
    Document   : Semaine
    Created on : 21 avr. 2015, 17:16:41
    Author     : igierm
--%>

<<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="entete.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calendrier</title>
        <link rel="stylesheet" media="screen, print, handheld" type="text/css" href="../calendrier.css" />
			<script type="text/javascript" src="../calendrier.js"></script>
    </head>
    <body>
        <div style="float:right;"><script type="text/javascript" >
		calendrier();
</script> </div>
        
		 <center><h1 class="Spect">Calendrier - Semaine</h1></center>
        
		<br><br>
			<nav class="navbar">
                        <div id="barre">
                       <ul class="nav navbar-nav">
			
                           <li> <a href="Aujourdhui.jsp">Aujourd'hui</a></li>
                           <li> <a href="Semaine.jsp">Cette Semaine</a></li>
                       <li><a href="Mois.jsp">Ce Mois</a> </li>
                        <li><a href="Tout.jsp">Tous Les Spectacles</a> </li>
			
                       </ul>
                        </div>
                    </nav>
        
        
        <div class="row">
            <article class="col-md-offset-1 col-md-10">
                <artSpect class="row">
                    <img class="col-md-5" height=500px src="../image/romeo.jpg"/>
                    <p class="col-md-5">Ceci est un petit paragraphe destiné à decrire brievement
                        cette charmante piéce de théatre<br><br>Horaire: 14h30 17h00 19h15
                        <br><br>Salle 1</p>
                </artSpect>
                <artSpect class="row">
                    <img class="col-md-5" height=500px src="../image/Scoop.jpg"/>
                    <p class="col-md-5">Ceci est un petit paragraphe destiné à decrire brievement
                        cette charmante piéce de théatre<br><br>Horaire: 14h30 17h00 19h15
                    <br><br>Salle 2</p>
                </artSpect>
                <artSpect class="row">
                    <img class="col-md-5" height=500px src="../image/affS.jpg"/>
                    <p class="col-md-5">Ceci est un petit paragraphe destiné à decrire brievement
                        cette charmante piéce de théatre<br><br>Horaire: 14h30 17h00 19h15
                    <br><br>Salle 1</p>
                </artSpect>
            </article>
        </div>
    </body>
</html>
