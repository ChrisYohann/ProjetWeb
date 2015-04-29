<%-- 
    Document   : SetRepresentation
    Created on : 25 avr. 2015, 21:00:36
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="entete.jsp" %>
<!DOCTYPE html>
<html>
    <body>
        <h2>Representations :</h2>
        <p>${message_erreur}</p>
        <% request.getSession(true).setAttribute("message_erreur",""); %>
        <c:if test="${not empty representation}">
            ${representation.getErreur()}
        </c:if>
        <form  class="col-md-5" method="post" action="SetRepresentation">
        <label for="nomSpect">Nom du Spectacle :</label>
        <p><input type="text" name="nomSpect" value="${newspectacle.getName()}" disabled /></p>
        <label for="description">Description (max 200 caractères) :</label>
        <p><TEXTAREA name="description" rows=10 COLS=40 maxlength="200" value="${newspectacle.getDescription()}" disabled></TEXTAREA></p>
        <label for="affiche">Affiche (sans l'extension .jpg):</label>
        <p><input type="text" name="affiche" value="${newspectacle.getAffiche()}" disabled /></p>
        <br>    
        <p><label for="nomSpect">Date :</label>
        <input type="number" name="day" min="1" max="28" value="1">
        <SELECT name="month">
		<OPTION VALUE="01">Janvier</OPTION>
		<OPTION VALUE="02">Fevrier</OPTION>
		<OPTION VALUE="03">Mars</OPTION>
		<OPTION VALUE="04">Avril</OPTION>
		<OPTION VALUE="05">Mai</OPTION>
                <OPTION VALUE="06">Juin</OPTION>
		<OPTION VALUE="07">Juillet</OPTION>
		<OPTION VALUE="08">Août</OPTION>
		<OPTION VALUE="09">Septembre</OPTION>
		<OPTION VALUE="10">Octobre</OPTION>
                <OPTION VALUE="11">Novembre</OPTION>
		<OPTION VALUE="12">Décembre</OPTION>
        </SELECT>     
         <SELECT name = "year">
                <OPTION VALUE="2015">2015</OPTION>
		<OPTION VALUE="2016">2016</OPTION>  
        </SELECT></p>
        <p><label for="salle">Salle :</label>
        <SELECT name = "salle">
                <OPTION VALUE="01">1</OPTION>
		<OPTION VALUE="02">2</OPTION>
		<OPTION VALUE="03">3</OPTION>  
        </SELECT>
        </p>
        <p>
            <label for="places">Nombre de places:</label>
            <input type="number" name="places" id="places" min="71" max="180" value="180">
        </p>
        <p>
        <label for="heure">Heure :</label>
        <SELECT name="heure">
		<OPTION VALUE="13">13</OPTION>
		<OPTION VALUE="14">14</OPTION>
		<OPTION VALUE="15">15</OPTION>
		<OPTION VALUE="16">16</OPTION>
		<OPTION VALUE="17">17</OPTION>
                <OPTION VALUE="18">18</OPTION>
		<OPTION VALUE="19">19</OPTION>
		<OPTION VALUE="20">20</OPTION>
		<OPTION VALUE="21">21</OPTION>
		<OPTION VALUE="22">22</OPTION>
        </SELECT> heures     
        <br>
        <br>
        <br>
        <br>
        <input class="btn btn-primary" type="submit" name="terminer" value="Terminer" />
        <input class="btn btn-primary" type="submit" name="terminer" value="Ajouter autre Representation" />
        </form>
    </body>
</html>

