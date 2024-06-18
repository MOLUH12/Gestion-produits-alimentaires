package gl3b;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

        String url = "jdbc:mysql://localhost:3306/testjee";
        String dbUser = "root";
        String dbPassword = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, dbUser, dbPassword);
            PreparedStatement pst = con.prepareStatement("select * from produit");
            ResultSet rs = pst.executeQuery();
            List<Product> productList = new ArrayList<>();
            
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

            request.setAttribute("productList", productList);
            System.out.println(productList.size());
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Une erreur s'est produite lors de la récupération des produits.");
        }
	}*/


}
