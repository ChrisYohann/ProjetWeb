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
        <h1 class="Spect">Votre Compte :</h1>
        <div class="row">
        <div class="col-md-offset-1 col-md-5">
        <h4>Vos réservations :</h4>
        <h6>Ces reservations resteront valides jusqu'à l'heure de debut de la représentation. Veuillez les payer en ligne pour recevoir le ticket.</h6>
        
                <form method="post" action="PasserCaisse">
                <c:forEach items="${liste_comptes}" var="cvar" >
                    <input type="checkbox" name="${cvar.getNumSpect()}" id="idspect" checked> 
                    <input  type="hidden" name="numDossier" value ="${cvar.getNumDossier()}"/>
                    <label for="nomspectacle">${cvar.getNomSpect()}</label>

 
                    <br>${cvar.afficherDate()} à ${cvar.getHeure()} heures en salle ${cvar.getNumSalle()} Nombre de places :
                    ${cvar.getnbrPlaceValide()}<br></artspect>
                    
                    <p><input class="btn btn-primary" type="submit" name="${cvar.getNumSpect()}" value="Payer" /></p> 
                    
                </c:forEach>
                </form>
            
        </div>
        <div class="col-md-offset-1 col-md-4">

        <h4>Vos achats :</h4>
                <div class="row">
            
                <c:forEach items="${liste_achats}" var="cvar" >
                    
                    <p>${cvar.getNomSpect()}
 
                    ${cvar.afficherDate()} à ${cvar.getHeure()} heures en salle ${cvar.getNumSalle()} Nombre de places :
                    ${cvar.getnbrPlaceValide()}<br><br></p></artspect>
                    
                </c:forEach>
          
        </div>
        </div>
        </div>
    </body>
</html>

