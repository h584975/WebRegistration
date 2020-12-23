<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Fra https://purecss.io/ -->
<link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
<title>Påmelding</title>
</head>
<body>
  <h2>Påmelding</h2>
  <form method="post" action="paamelding" class="pure-form pure-form-aligned">
    <fieldset>

      <div class="pure-control-group">
        <label for="fornavn">Fornavn:</label> 
        <input type="text" name="fornavn" value=<c:out value="${empty feil1 ? fornavn : ''}"/>> 
        <font color="red">${empty feil1 ? '' : 'Ugyldig fornavn'}</font>
      </div>

      <div class="pure-control-group">
        <label for="etternavn">Etternavn:</label>
        <input type="text" name="etternavn" value=<c:out value="${empty feil2 ? etternavn : ''}"/>>
        <font color="red">${empty feil2 ? '' : 'Ugyldig etternavn'}</font>
      </div>

      <div class="pure-control-group">
        <label for="mobil">Mobil (8 siffer):</label>
        <input type="text" name="mobil" value=<c:out value="${empty feil3 ? mobil : ''}"/>>
        <font color="red">${empty feil3 ? '' : 'Ugyldig mobilnummer'}</font>
      </div>

      <div class="pure-control-group">
        <label for="fornavn">Passord:</label>
        <input type="password" name="passord" value=<c:out value="${empty feil4 ? passord : ''}"/>>
        <font color="red">${empty feil4 ? '' : 'Ugyldig passord'}</font>
      </div>

      <div class="pure-control-group">
        <label for="passordRepetert">Passord repetert:</label> 
        <input type="password" name="passordRepetert" value=<c:out value="${empty feil4 && empty feil5 ? passord2 : ''}"/>>
        <font color="red">${empty feil5 ? '' : 'Passordene er ulike'}</font>
      </div>

      <div class="pure-control-group">
        <label for="kjonn">Kjønn:</label>
        <input type="radio" name="kjonn" value="mann" ${kjonn == 'mann' ? 'checked' : ''} />mann
        <input type="radio" name="kjonn" value="kvinne" ${kjonn == 'kvinne' ? 'checked' : ''} />kvinne
        <font color="red">${empty feil6 ? '' : 'Du må oppgi kjønn'}</font>
      </div>

      <div class="pure-controls">
        <button type="submit" class="pure-button pure-button-primary">Meld meg på</button>
      </div>
    </fieldset>
  </form>
  <form action="logginn" class="pure-form pure-form-aligned">

     <div class="pure-controls">
      <button type="submit" class="pure-button pure-button-primary">Har allerede en bruker</button>
     </div>
  </form>
</body>
</html>