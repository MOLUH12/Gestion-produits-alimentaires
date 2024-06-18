package gl3b;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//création de la session utilisateur
		HttpSession session = request.getSession();
		
		//recuperation des variables saisies
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String url = "jdbc:mysql://localhost:3306/testjee";
		String bdUser = "root";
		String bdPwd = "";
		
		//initialiser la connexion base de données
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, bdUser, bdPwd);
			java.sql.PreparedStatement pst = con.prepareStatement("select id from user where username=? AND password=?");
			pst.setString(1, username);
			pst.setString(2, password);
			ResultSet res = pst.executeQuery();
			
			if (res.next() == true) {
			    session.setAttribute("username",username);
			    response.sendRedirect("dashboard.jsp");
			} else {
			    // Ajouter un attribut de notification à la requête
			    request.setAttribute("notification", "Utilisateur non trouvé");

			    // Rediriger vers login.jsp
			    RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			    dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}

}
