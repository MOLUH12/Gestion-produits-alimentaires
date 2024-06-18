package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.DatabaseConnection;

@WebServlet("/AddPatient")
public class AddPatient extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");  // Correction de "Mobile" en "phone"
        String city = request.getParameter("City");
        String email = request.getParameter("email");
        String age = request.getParameter("age");
        String address = request.getParameter("address");
        
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String DateAndTime = df2.format(new Date());

        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DatabaseConnection.initializeDatabase();
            pst = con.prepareStatement("INSERT INTO patient (fname, lname, gender, city, email, age, address, registration_date, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, fname);
            pst.setString(2, lname);
            pst.setString(3, gender);
            pst.setString(4, city);
            pst.setString(5, email);
            pst.setString(6, age);
            pst.setString(7, address);
            pst.setString(8, DateAndTime);
            pst.setString(9, phone);

            int i = pst.executeUpdate();
            if (i > 0) {
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Patient added successfully!');");
                pw.println("window.location.href = \"UserHome.jsp\";");
                pw.println("</script>");
            } else {
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Failed to add patient. Please try again.');");
                pw.println("window.location.href = \"addpatient.jsp\";");
                pw.println("</script>");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddPatient.class.getName()).log(Level.SEVERE, null, ex);
            pw.println("<script type=\"text/javascript\">");
            pw.println("alert('An error occurred: " + ex.getMessage() + "');");
            pw.println("window.location.href = \"addpatient.jsp\";");
            pw.println("</script>");
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AddPatient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
