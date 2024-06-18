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

@WebServlet("/AddDoctor")
public class AddDoctor extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        String sid = request.getParameter("id");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");  // Correction de "Mobile" en "phone"
        String city = request.getParameter("City");
        String email = request.getParameter("email");
        String age = request.getParameter("age");
        String address = request.getParameter("address");
        String qualification = request.getParameter("qualification");

        if (sid == null || sid.isEmpty() || fname == null || fname.isEmpty() ||
            lname == null || lname.isEmpty() || gender == null || gender.isEmpty() ||
            phone == null || phone.isEmpty() || city == null || city.isEmpty() ||
            email == null || email.isEmpty() || age == null || age.isEmpty() ||
            address == null || address.isEmpty() || qualification == null || qualification.isEmpty()) {
            pw.println("<script type=\"text/javascript\">");
            pw.println("alert('All fields are required.');");
            pw.println("window.location.href = \"addDoctor.jsp\";");
            pw.println("</script>");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(sid);
        } catch (NumberFormatException e) {
            pw.println("<script type=\"text/javascript\">");
            pw.println("alert('Invalid ID format.');");
            pw.println("window.location.href = \"addDoctor.jsp\";");
            pw.println("</script>");
            return;
        }

        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateAndTime = df2.format(new Date());

        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DatabaseConnection.initializeDatabase();
            pst = con.prepareStatement("INSERT INTO doctor (id, fname, lname, gender, phone, city, email, age, address, dateandtime, qualification) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setInt(1, id);
            pst.setString(2, fname);
            pst.setString(3, lname);
            pst.setString(4, gender);
            pst.setString(5, phone);
            pst.setString(6, city);
            pst.setString(7, email);
            pst.setString(8, age);
            pst.setString(9, address);
            pst.setString(10, dateAndTime);
            pst.setString(11, qualification);

            int i = pst.executeUpdate();
            if (i > 0) {
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Data added successfully!');");
                pw.println("window.location.href = \"AdminHome.jsp\";");
                pw.println("</script>");
            } else {
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Failed to add data, please try again later.');");
                pw.println("window.location.href = \"addDoctor.jsp\";");
                pw.println("</script>");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
            pw.println("<script type=\"text/javascript\">");
            pw.println("alert('Error: " + ex.getMessage() + "');");
            pw.println("window.location.href = \"error.jsp\";");
            pw.println("</script>");
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
