<%-- 
    Document   : Aujourdhui
    Created on : 21 avr. 2015, 17:12:42
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
		 <link href="../test.css" rel="stylesheet">
			<script type="text/javascript" src="../calendrier.js"></script>
    </head>
    <body>
        <div style="float:right;"><script type="text/javascript" >
		calendrier();
</script> </div>	



        
         <center><h1 class="Spect">Calendrier - Aujourd'hui</h1></center>
        
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
        
        <br><br><br><br><br><br><br><br><br><br><br>
        
<div id="contenu">
<div id="par7861" class="paragraphe tpl TPL_AGENDATHEATRELISTE"><div class="inner"><ul>
    <li class="case first">
        <div>
                        <img id="taille" src="../image/aff1.jpg"/>
                        <h3>Le petit garçon perdu</h3>
                        <p>Cette piece est a propos de je ne sais quoi, elle est super chouette il faut aller a voir! </p>
                        <ul>
                            <li><script type="text/javascript"> date_aujourdui(0); </script> - 20h30</li>
                            <li><script type="text/javascript"> date_aujourdui(14); </script> - 20h30</li>
                        </ul>
        </div>
    </li>
    <li class="case">
        <div>
                        <img id="taille" src="../image/aff2.jpg"/>
                        <h3>Les nuits trop courtes </h3>
                        <p>Cette piece est a propos de je ne sais quoi, 
                        elle est super chouette il faut [...]</p>
                        <ul>
                            <li><script type="text/javascript"> date_aujourdui(0); </script> - 20h30</li>
                            <li><script type="text/javascript"> date_aujourdui(7); </script> - 20h30</li>
                        </ul>
        </div>
    </li>
    <li class="case">
        <div>
                        <img id="taille" src="../image/affS.jpg"/>
                        <h3>La Bar-Mitzva</h3>
                        <p>L'histoire raconte la bar-mitzva du chat d'un rabbin </p>
                        <ul>
                            <li><script type="text/javascript"> date_aujourdui(0); </script> - 16h45</li>
                            <li><script type="text/javascript"> date_aujourdui(18); </script> - 20h30</li>
                        </ul>
    </li>
    <li class="case">
        <div>
                        <img id="taille" src="../image/romeo.jpg"/>
                        <h3>Roméo</h3>
                        <p>Cette piece raconte l'histoire de Roméo et Juliette, le classique à ne pas manquer </p>
                        <ul>
                            <li><script type="text/javascript"> date_aujourdui(0); </script> - 16h45</li>
                            <li><script type="text/javascript"> date_aujourdui(18); </script> - 16h45</li>
                        </ul>
        </div>
    </li>
    </body>
</html>
