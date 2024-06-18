package gl3b;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Ajouter
 */
@WebServlet("/ajouter")
public class Ajouter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//création de la session utilisateur
		HttpSession session = request.getSession();
		
		//recuperation des variables saisies
		String nom = request.getParameter("name");
		double unitPrice = Double.parseDouble(request.getParameter("unitPrice"));
		double quantity = Double.parseDouble(request.getParameter("quantity"));
		Date expirationDate = Date.valueOf(request.getParameter("expirationDate"));
		
		String url = "jdbc:mysql://localhost:3306/testjee";
		String bdUser = "root";
		String bdPwd = "";
		
		//initialiser la connexion base de données
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, bdUser, bdPwd);
			PreparedStatement pst = con.prepareStatement("INSERT INTO produit (`nom`, `prixUnitaire`, `quantite`, `dateExpiration`) VALUES (?, ?, ?, ?)");
			pst.setString(1, nom);
			pst.setDouble(2, unitPrice);
			pst.setDouble(3, quantity);
			pst.setDate(4, expirationDate);
			int rowsAffected = pst.executeUpdate();
			//ResultSet res = pst.executeQuery();
			
			if (rowsAffected > 0) {
	            // Utilisateur enregistré avec succès
	            request.setAttribute("successMessage", "Produit enregistré avec succès");

	            // Rediriger vers login.jsp
	            RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
	            dispatcher.forward(request, response);
	        } else {
	            // Ajouter un attribut de notification à la requête
	            request.setAttribute("notification", "Erreur lors de l'enregistrement du produit");

	            // Rediriger vers register.jsp pour refaire l'enregistrement
	            response.sendRedirect("ajouter.jsp");
	        }
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
