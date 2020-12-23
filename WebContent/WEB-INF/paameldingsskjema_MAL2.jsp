<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="no">
  <head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="#">
    <link href="css/main.css" rel="stylesheet" type="text/css" />
    <link href="css/formcontroller.css" rel="stylesheet" type="text/css" />
    <script src="js/validator.js" defer></script>
    <!--  <script src="js/formcontroller.js" defer></script> -->

    <title>Påmelding</title>
  </head>
  <body>
    <h2>Påmelding</h2>

    <div id="root">
      <form method="post" action="paamelding">
        <fieldset>
          <label for="fornavn">Fornavn:</label> <input type="text" name="fornavn" id="fornavn" value=<c:out value="${form.fornavn}"/>> 
          <span class="melding">${form.fornavnMelding}</span>

          <label for="etternavn">Etternavn:</label> <input type="text" name="etternavn" id="etternavn" value=<c:out value="${form.etternavn}"/>>
          <span class="melding">${form.etternavnMelding}</span>

          <label for="mobil">Mobil (8 siffer):</label> <input type="text" name="mobil" id="mobil" value=<c:out value="${form.mobil}"/>>
          <span class="melding">${form.mobilMelding}</span>

          <label for="passord">Passord:</label> <input type="password" name="passord" id="passord" value=<c:out value="${form.passord}"/>>
          <span class="melding">${form.passordMelding}</span>

          <label for="passordRepetert">Passord repetert:</label> <input type="password" name="passordRepetert" id="passordRepetert" value=<c:out value="${form.passordRepetert}"/>>
          <span class="melding">${form.passordRepetertMelding}</span>

          <span class="columnfirst">Kjønn:</span>
          <span data-kjonn>
            <label><input type="radio" name="kjonn" id="mann" value="mann" ${form.kjonn == 'mann' ? 'checked' : ''} />mann</label>
            <label><input type="radio" name="kjonn" id="kvinne" value="kvinne" ${form.kjonn == 'kvinne' ? 'checked' : ''} />kvinne</label>
          </span>
          <span class="melding">${form.kjonnMelding}</span>

          <button type="submit" id="submit">Meld meg på</button>
        </fieldset>
      </form>
   
      <div data-info="passord"></div>
      <div data-info="submit"></div>
  
      <form action="logginn">
        <button type="submit">Har allerede en bruker?</button>
      </form>
    </div>
  </body>
</html>