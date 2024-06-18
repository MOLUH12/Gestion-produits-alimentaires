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
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="dashboard.css">
<title>Page d'Accueil</title>
</head>
<body>
	<h1>LISTE DES PRODUITS</h1>

	<table>
		<tr>
			<th>Nom</th>
			<th>Prix Unitaire</th>
			<th>Quantité</th>
			<th>Date d'Expiration</th>
			<th>Actions</th>
		</tr>
		<%-- Boucle pour afficher les produits --%>
		<%
		List<Product> productList = new ArrayList<>();
		// Ajoutez des produits à la liste
		String url = "jdbc:mysql://localhost:3306/testjee";
		String dbUser = "root";
		String dbPassword = "";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, dbUser, dbPassword);
			PreparedStatement pst = con.prepareStatement("select * from produit");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("idProduit");
				String name = rs.getString("nom");
				double unitPrice = rs.getDouble("prixUnitaire");
				int quantity = rs.getInt("quantite");
				Date expirationDate = rs.getDate("dateExpiration");

				Product product = new Product(id, name, unitPrice, quantity, expirationDate);
				// System.out.println(product.getName());
				productList.add(product);
			}
			/*request.setAttribute("product", product);
			request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
			request.setAttribute("productList", productList);
			System.out.println(productList.size());
			request.getRequestDispatcher("dashboard.jsp").forward(request, response);*/
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("Une erreur s'est produite lors de la récupération des produits.");
		}
		for (Product product : productList) {
		%>
		<tr>
			<td><%=product.getName()%></td>
			<td><%=product.getUnitPrice()%></td>
			<td><%=product.getQuantity()%></td>
			<td><%=product.getExpirationDate()%></td>
			<td>
					<div class="form-group">
						<button>
						<a href="updateProduct.jsp?id=<%= product.getId() %>">Modifier</a>
						</button>
					</div>
				<div class="form-group">
					<button>
						<a href="deleteProduct?id=<%=product.getId()%>">Delete</a>
					</button></td>
			</div>
		</tr>
		<%
		}
		%>
	</table>
	<div class="container">
		<form action="Logout" method="post">
			<div class="form-group">
				<button type="submit">Se déconnecter</button>
			</div>
		</form>
	</div>
	<div class="container">
		<form action="ajouter.jsp" method="post">
			<div class="form-group">
				<button type="submit">Ajouter</button>
			</div>
		</form>
	</div>
</body>
</html>