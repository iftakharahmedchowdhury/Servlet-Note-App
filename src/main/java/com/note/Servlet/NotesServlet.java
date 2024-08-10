package com.note.Servlet;
import com.note.Model.Note;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/notes")
public class NotesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = (String) req.getSession().getAttribute("role");

        if (role == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        List<Note> notes = new ArrayList<>();
        try (Connection conn = DatabaseUtils.getConnection()) {
            String sql = "SELECT * FROM notes";
            try (PreparedStatement pso = conn.prepareStatement(sql);
                 ResultSet rs = pso.executeQuery()) {
                while (rs.next()) {
                    Note note = new Note();
                    note.setId(rs.getInt("id"));
                    note.setContent(rs.getString("note"));
                    notes.add(note);
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        req.setAttribute("notes", notes);
        req.getRequestDispatcher("notes.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // String role = (String) req.getSession().getAttribute("role");

        String noteContent = req.getParameter("content");

        try (Connection conn = DatabaseUtils.getConnection()) {
            String sql = "INSERT INTO notes (note) VALUES (?)";
            try (PreparedStatement pso = conn.prepareStatement(sql)) {
                pso.setString(1, noteContent);
                pso.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        resp.sendRedirect("notes");
    }
}
