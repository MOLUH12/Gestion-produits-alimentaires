<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Page d'Inscription</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
}

.container {
	max-width: 800px;
	margin: 10px auto;
	max-height: 40%;
	padding: 10px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	display: flex;
	justify-content: space-around;
    align-items: flex-start;
	
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

.form-group input[type="text"], .form-group input[type="password"],
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
<script>
	// Fonction pour afficher les villes en fonction du pays sélectionné
	function afficherVilles() {
		var paysSelect = document.getElementById("pays");
		var villeSelect = document.getElementById("ville");

		// Récupérer la valeur sélectionnée du pays
		var pays = paysSelect.value;

		// Vider la liste des villes
		villeSelect.innerHTML = "";

		// Ajouter les villes correspondantes au pays sélectionné
		if (pays === "cameroun") {
			ajouterOption(villeSelect, "yaounde", "Yaoundé");
			ajouterOption(villeSelect, "douala", "Douala");
			ajouterOption(villeSelect, "bamenda", "Bamenda");
		} else if (pays === "france") {
			ajouterOption(villeSelect, "paris", "Paris");
			ajouterOption(villeSelect, "marseille", "Marseille");
			ajouterOption(villeSelect, "lyon", "Lyon");
		} else if (pays === "etats-unis") {
			ajouterOption(villeSelect, "new-york", "New York");
			ajouterOption(villeSelect, "los-angeles", "Los Angeles");
			ajouterOption(villeSelect, "chicago", "Chicago");
		} else if (pays === "canada") {
			ajouterOption(villeSelect, "montreal", "Montréal");
			ajouterOption(villeSelect, "toronto", "Toronto");
			ajouterOption(villeSelect, "vancouver", "Vancouver");
		} else if (pays === "belgique") {
			ajouterOption(villeSelect, "bruxelles", "Bruxelles");
			ajouterOption(villeSelect, "liege", "Liège");
			ajouterOption(villeSelect, "namur", "Namur");
		} else if (pays === "rca") {
			ajouterOption(villeSelect, "bangui", "Bangui");
			ajouterOption(villeSelect, "bambari", "Bambari");
			ajouterOption(villeSelect, "bossangoa", "Bossangoa");
		} else if (pays === "gabon") {
			ajouterOption(villeSelect, "libreville", "Libreville");
			ajouterOption(villeSelect, "port-gentil", "Port-Gentil");
			ajouterOption(villeSelect, "franceville", "Franceville");
		} else if (pays === "guinee") {
			ajouterOption(villeSelect, "conakry", "Conakry");
			ajouterOption(villeSelect, "labe", "Labé");
			ajouterOption(villeSelect, "nzerekore", "Nzérékoré");
		} else if (pays === "russie") {
			ajouterOption(villeSelect, "moscou", "Moscou");
			ajouterOption(villeSelect, "saint-petersbourg", "Saint-Pétersbourg");
			ajouterOption(villeSelect, "novossibirsk", "Novossibirsk");
		} else if (pays === "cote-d-ivoire") {
			ajouterOption(villeSelect, "abidjan", "Abidjan");
			ajouterOption(villeSelect, "bouake", "Bouaké");
			ajouterOption(villeSelect, "daloa", "Daloa");
		}
	}

	// Fonction pour ajouter une option à un élément select
	function ajouterOption(select, value, text) {
		var option = document.createElement("option");
		option.value = value;
		option.text = text;
		select.appendChild(option);
	}

	// Fonction de validation du formulaire
	function validerFormulaire() {
		var nom = document.getElementById("nom").value;
		var email = document.getElementById("email").value;
		var password = document.getElementById("password").value;
		var confirmPassword = document.getElementById("confirm-password").value;

		// Vérifier si les champs sont vides
		if (nom === "" || email === "" || password === ""
				|| confirmPassword === "") {
			alert("Veuillez remplir tous les champs.");
			return false;
		}

		// Vérifier si le mot de passe et la confirmation sont identiques
		if (password !== confirmPassword) {
			alert("Le mot de passe et la confirmation ne correspondent pas.");
			return false;
		}

		// Validation réussie
		alert("Formulaire validé !");
		return true;
	}
</script>
</head>
<body>
	<div class="container">
	<div><img src="images/signup-image.jpg" alt="sing up image"></div>
		<div><h2>Page d'Inscription</h2>
		<form onsubmit="return validerFormulaire()" action="Register"
			method="post">
			<div class="form-group">
				<label for="nom">Nom :</label> <input type="text" id="nom"
					name="nom" required>
			</div>
			<div class="form-group">
				<label for="email">Email :</label> <input type="text" id="email"
					name="email" required>
			</div>
			<div class="form-group">
				<label for="username">Username:</label> <input type="text"
					id="username" name="username" required>
			</div>
			<div class="form-group">
				<label for="password">Mot de passe :</label> <input type="password"
					id="password" name="password" required>
			</div>
			<div class="form-group">
				<label for="confirm-password">Confirmer le mot de passe :</label> <input
					type="password" id="confirm-password" name="confirm-password"
					required>
			</div>
			<div class="form-group">
				<label for="pays">Pays :</label> <select id="pays" name="pays"
					onchange="afficherVilles()" required>
					<option value="">Sélectionner un pays</option>
					<option value="cameroun">Cameroun</option>
					<option value="france">France</option>
					<option value="etats-unis">États-Unis</option>
					<option value="canada">Canada</option>
					<option value="belgique">Belgique</option>
					<option value="rca">République centrafricaine</option>
					<option value="gabon">Gabon</option>
					<option value="guinee">Guinée</option>
					<option value="russie">Russie</option>
					<option value="cote-d-ivoire">Côte d'Ivoire</option>
				</select>
			</div>
			<div class="form-group">
				<label for="ville">Ville :</label> <select id="ville" name="ville"
					required>
					<option value="">Sélectionner une ville</option>
				</select>
			</div>
			<div class="form-group">
				<button type="submit">S'inscrire</button>
			</div>
		</form>
		<p>
			Déjà inscrit ? <a href="login.jsp">Se connecter</a>
		</p>
	</div>
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