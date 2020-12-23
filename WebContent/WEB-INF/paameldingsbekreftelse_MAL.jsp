<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Fra https://purecss.io/ -->
<link rel="stylesheet"
	href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
<title>Påmeldingsbekreftelse</title>
</head>
<body>
	<h2>Påmeldingsbekreftelse</h2>
	<p>Påmeldingen er mottatt for</p>
	<p>
		&nbsp;&nbsp;&nbsp; <c:out value="${deltager.fornavn}"/> <br />
		&nbsp;&nbsp;&nbsp; <c:out value="${deltager.etternavn}"/> <br />
		&nbsp;&nbsp;&nbsp; <c:out value="${deltager.mobil}"/><br /> 
        &nbsp;&nbsp;&nbsp; <c:out value="${deltager.kjonn}"/>
	</p>
	<a href="deltagerliste">Gå til deltagerlisten</a>
</body>
</html>