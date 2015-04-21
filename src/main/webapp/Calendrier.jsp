<%-- 
    Document   : Calendrier
    Created on : 21 avr. 2015, 17:07:01
    Author     : igierm
--%>

<<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="entete.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" media="screen, print, handheld" type="text/css" href="calendrier.css" />
		 <link href="test.css" rel="stylesheet">
		<script type="text/javascript" src="calendrier.js"></script>
        <title>Calendrier</title>
    </head>
    <body>
        <div style="float:right;"><script type="text/javascript" >
		calendrier();
</script> </div>
        
      <center> <h1 class="Spect">Calendrier</h1></center>
        
		<br><br>
                    
                    <nav class="navbar">
                        <div id="barre">
                       <ul class="nav navbar-nav">
			
                           <li> <a href="./Spectacles/Aujourdhui.jsp">Aujourd'hui</a></li>
                           <li> <a href="./Spectacles/Semaine.jsp">Cette Semaine</a></li>
                       <li><a href="./Spectacles/Mois.jsp">Ce Mois</a> </li>
                        <li><a href="./Spectacles/Tout.jsp">Tous Les Spectacles</a> </li>
			
                       </ul>
                        </div>
                    </nav>
                    
    
    
    </body>
</html>
