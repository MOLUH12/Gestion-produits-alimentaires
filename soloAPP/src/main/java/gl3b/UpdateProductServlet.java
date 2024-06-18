package gl3b;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateProductServlet
 */
@WebServlet("/UpdateProduct")
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double unitPrice = Double.parseDouble(request.getParameter("unitPrice"));
        double quantity = Double.parseDouble(request.getParameter("quantity"));
        Date expirationDate = Date.valueOf(request.getParameter("expirationDate"));

        String url = "jdbc:mysql://localhost:3306/testjee";
		String bdUser = "root";
		String bdPwd = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, bdUser, bdPwd);
            PreparedStatement pst = (PreparedStatement) con.prepareStatement("UPDATE produit SET nom=?, prixUnitaire=?, quantite=?, dateExpiration=? WHERE idProduit=?");
            pst.setString(1, name);
            pst.setDouble(2, unitPrice);
            pst.setDouble(3, quantity);
            pst.setDate(4, expirationDate);
            pst.setInt(5, productId);

            pst.executeUpdate();

            response.sendRedirect("dashboard.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Une erreur s'est produite lors de la modification du produit.");
        }
    }

}
