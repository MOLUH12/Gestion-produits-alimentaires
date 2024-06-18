package controller;

import database.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        
        String user = request.getParameter("Username");
        String pass = request.getParameter("password");
        String repass = request.getParameter("repassword");

        if (!pass.equals(repass)) {
            pw.println("<script type=\"text/javascript\">");
            pw.println("alert('Passwords do not match!');");
            pw.println("window.location.href = \"userRegister.jsp\";");
            pw.println("</script>");
            return;
        }

        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DatabaseConnection.initializeDatabase();
            pst = con.prepareStatement("INSERT INTO login (username, password) VALUES (?, ?)");
            pst.setString(1, user);
            pst.setString(2, pass);

            int i = pst.executeUpdate();
            if (i > 0) {
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Register Successfully..!');");
                pw.println("window.location.href = \"index.jsp\";");
                pw.println("</script>");
            } else {
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Register Failed');");
                pw.println("window.location.href = \"userRegister.jsp\";");
                pw.println("</script>");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            pw.println("<script type=\"text/javascript\">");
            pw.println("alert('An error occurred: " + e.getMessage() + "');");
            pw.println("window.location.href = \"error.jsp\";");
            pw.println("</script>");
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
