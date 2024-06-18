package gl3b;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deleteProduct
 */
public class deleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        int productId = Integer.parseInt(request.getParameter("id"));

	        String url = "jdbc:mysql://localhost:3306/testjee";
	        String dbUser = "root";
	        String dbPassword = "";

	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection con = DriverManager.getConnection(url, dbUser, dbPassword);
	            PreparedStatement pst = (PreparedStatement) con.prepareStatement("DELETE FROM produit WHERE idProduit=?");
	            pst.setInt(1, productId);

	            pst.executeUpdate();

	            response.sendRedirect("dashboard.jsp");

	} catch (Exception e) {
	            e.printStackTrace();
	            response.getWriter().println("Une erreur s'est produite lors de la suppression du produit.");
	        }
	    }

}
