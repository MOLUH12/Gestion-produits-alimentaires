<%@ page isELIgnored="false"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.Date"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="gl3b.Product"%>
<%
String login = "";
if (session.getAttribute("username") != null) {
	login = session.getAttribute("username").toString();
} else {
	response.sendRedirect("login.jsp");
}

Product product = (Product) request.getAttribute("product");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="dashboard.css">
<title>Modifier le Produit</title>
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
	background-color: #4CAF50;
	color: #fff;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="container">
		<h2>Modifier le Produit</h2>
		<%
		List<Product> productList = new ArrayList<>();
		// Ajoutez des produits à la liste
		String url = "jdbc:mysql://localhost:3306/testjee";
		String dbUser = "root";
		String dbPassword = "";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, dbUser, dbPassword);
			PreparedStatement pst = con.prepareStatement("select * from produit WHERE idProduit=?");
			int productId = Integer.parseInt(request.getParameter("id"));
			pst.setInt(1, productId);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
		%>
		<form action="UpdateProductServlet" method="post">
			<input type="hidden" name="id" value="<%=rs.getInt(1)%>">
			<div class="form-group">
				<label for="name">Nom:</label> <input type="text" id="name"
					name="name" value="<%=rs.getString(2)%>">
			</div>
			<div class="form-group">
				<label for="unitPrice">Prix Unitaire:</label> <input type="number"
					id="unitPrice" name="unitPrice" value="<%=rs.getDouble(3)%>">
			</div>
			<div class="form-group">
				<label for="quantity">Quantité:</label> <input type="number"
					id="quantity" name="quantity" value="<%=rs.getDouble(4)%>">
			</div>
			<div class="form-group">
				<label for="expirationDate">Date d'Expiration:</label> <input
					type="date" id="expirationDate" name="expirationDate"
					value="<%=rs.getDate(5)%>">
			</div>
			<div class="form-group">
				<button type="submit">Modifier</button>
			</div>
		</form>
		<%
		} else {
		%>
		<b>Produit introuvable</b>
		<%
		}
		} catch (Exception e) {
		e.printStackTrace();
		response.getWriter().println("Une erreur s'est produite lors de la récupération des produits.");
		}
		%>
		<div class="container">
			<a href="dashboard.jsp">Annuler</a>
		</div>
	</div>
</body>
</html>