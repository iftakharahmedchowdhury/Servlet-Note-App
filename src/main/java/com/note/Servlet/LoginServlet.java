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
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try (Connection conn = DatabaseUtils.getConnection()) {
            String sql = "SELECT role FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement pso = conn.prepareStatement(sql)) {
                pso.setString(1, username);
                pso.setString(2, password);

                try (ResultSet rs = pso.executeQuery()) {
                    if (rs.next()) {
                        String role = rs.getString("role");
                        req.getSession().setAttribute("role", role);
                        resp.sendRedirect("notes");
                    } else {
                        resp.sendRedirect("login.jsp?error=Invalid credentials");
                    }
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}

