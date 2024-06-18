package gl3b;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//création de la session utilisateur
				HttpSession session = request.getSession();
				
				//recuperation des variables saisies
				String nom = request.getParameter("nom");
				String email = request.getParameter("email");
				String pays = request.getParameter("pays");
				String ville = request.getParameter("ville");
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				
				String url = "jdbc:mysql://localhost:3306/testjee";
				String bdUser = "root";
				String bdPwd = "";
				
				//initialiser la connexion base de données
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection(url, bdUser, bdPwd);
					java.sql.PreparedStatement pst = con.prepareStatement("INSERT INTO user (`nom`, `email`, `pays`, `ville`, `username`, `password`) VALUES (?, ?, ?, ?, ?, ?)");
					pst.setString(1, nom);
					pst.setString(2, email);
					pst.setString(3, pays);
					pst.setString(4, ville);
					pst.setString(5, username);
					pst.setString(6, password);
					int rowsAffected = pst.executeUpdate();
					//ResultSet res = pst.executeQuery();
					
					if (rowsAffected > 0) {
			            // Utilisateur enregistré avec succès
			            request.setAttribute("successMessage", "Utilisateur enregistré avec succès");

			            // Rediriger vers login.jsp
			            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			            dispatcher.forward(request, response);
			        } else {
			            // Ajouter un attribut de notification à la requête
			            request.setAttribute("notification", "Erreur lors de l'enregistrement de l'utilisateur");

			            // Rediriger vers register.jsp pour refaire l'enregistrement
			            response.sendRedirect("register.jsp");
			        }
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
	}

}
