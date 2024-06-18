<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String login = "";
if (session.getAttribute("username") != null) {
	login = session.getAttribute("username").toString();
} else {
	response.sendRedirect("login.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Ajouter nouveau produit</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
}

.container {
	max-width: 400px;
	margin: 0 auto;
	padding: 20px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h2 {
	text-align: center;
}

.form-group {
	margin-bottom: 15px;
}

.form-group label {
	font-weight: bold;
}

.form-group input[type="text"], .form-group input[type="number"],
	.form-group select {
	width: 100%;
	padding: 8px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

.form-group button {
	width: 100%;
	padding: 8px;
	background-image: linear-gradient(90deg, rgb(243, 234, 243), rgb(15, 5, 107));
	color: #fff;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="container">
		<h2>Ajouter nouveau produit</h2>
		<form action="Ajouter" method="post">
			<div class="form-group">
				<label for="name">Nom:</label> <input type="text" id="name"
					name="name">
			</div>
			<div class="form-group">
				<label for="unitPrice">Prix Unitaire:</label> <input type="number"
					id="unitPrice" name="unitPrice">
			</div>
			<div class="form-group">
				<label for="quantity">Quantité:</label> <input type="number"
					id="quantity" name="quantity">
			</div>
			<div class="form-group">
				<label for="expirationDate">Date d'Expiration:</label> <input
					type="date" id="expirationDate" name="expirationDate">
			</div>
			<div class="form-group">
				<button type="submit">Ajouter</button>
			</div>
		</form>

	</div>
	<%-- Afficher le message de succès si l'enregistrement a été fait avec succes --%>
	<%
	if (request.getAttribute("successMessage") != null) {
	%>
	<div class="success-message">
		<%=request.getAttribute("successMessage")%>
	</div>
	<%
	}
	%>
	<%-- ... --%>
</body>
</html>