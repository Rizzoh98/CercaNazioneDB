<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<style type="text/css">
body {
	background-image:
		url('https://cdn.crunchify.com/wp-content/uploads/2013/03/Crunchify.bg_.300.png');
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CercaNazioneDB</title>
</head>
<body>
		<div align="center" style="margin-top: 50px;">

		<form action="reseach" method = "GET">
			Inserisci Nazione: <input type="text" name="nazione"
				size="20px"> <br> <br> <br>
				<input type="submit" value="Submit">
		</form>
		<br><br>
		<p>${risultato}</p>
		
	
</body>
</html>