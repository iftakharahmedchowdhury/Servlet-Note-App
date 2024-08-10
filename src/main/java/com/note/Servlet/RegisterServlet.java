package com.note.Servlet;


import com.note.Util.DatabaseUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        try (Connection conn = DatabaseUtils.getConnection()) {
            String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            try (PreparedStatement pso = conn.prepareStatement(sql)) {
                pso.setString(1, username);
                pso.setString(2, password);
                pso.setString(3, role);
                pso.executeUpdate();
                resp.sendRedirect("login.jsp?message=Registration successful");
            }
        } catch (SQLException e) {
            resp.sendRedirect("register.jsp?errorMessage=Registration failed. Duplicate username found.");
        }
    }
}
