<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Font Icon -->
    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="css/style.css">
    <title>Page de Connexion</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }
        .main {
      	 display: flex;
      	 justify-content: space-around;
        /*vertical-align: top*/
         }

        .container {
            max-width: 800px;
            height: 50%;
            margin: 150px auto;
            padding: 20px;
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

        .form-group input[type="text"],
        .form-group input[type="password"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .form-group button {
            width: 100%;
            padding: 8px;
            background-image: linear-gradient(90deg, rgb(3, 234, 243), rgb(15, 200, 27));
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="container">   
		<div><img src="images/signin-image.jpg" alt="sing up image"></div>
        <div>
       <h2>Connexion</h2>
       <form action="Login" method="post">
            <div class="form-group">
                <label for="username">Nom d'utilisateur:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Mot de passe:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <button type="submit" value="Log in">Se connecter</button>
            </div>
        </form>
        <p>Pas encore inscrit ? <a href="register.jsp">S'inscrire</a></p> <!-- Lien vers la page d'inscription -->
    	<%-- Afficher la notification si elle est présente --%>
    <% if (request.getAttribute("notification") != null) { %>
        <div class="notification">
            <%= request.getAttribute("notification") %>
        </div>
        </div>
    <% } %>
    <%-- ... --%>
    </div>
</body>
</html>