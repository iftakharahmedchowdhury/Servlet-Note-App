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

@WebServlet("/note/edit")
public class EditNoteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String noteId = req.getParameter("id");

        try (Connection conn = DatabaseUtils.getConnection()) {
            String sql = "SELECT * FROM notes WHERE id = ?";
            try (PreparedStatement pso = conn.prepareStatement(sql)) {
                pso.setInt(1, Integer.parseInt(noteId));
                try (ResultSet rs = pso.executeQuery()) {
                    if (rs.next()) {
                        req.setAttribute("note", rs.getString("note"));
                        req.setAttribute("id", noteId);
                        req.getRequestDispatcher("/edit.jsp").forward(req, resp);
                    } else {
                        resp.sendRedirect("/notes");
                    }
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String noteId = req.getParameter("id");
        String updatedNote = req.getParameter("note");

        try (Connection conn = DatabaseUtils.getConnection()) {
            String sql = "UPDATE notes SET note = ? WHERE id = ?";
            try (PreparedStatement pso = conn.prepareStatement(sql)) {
                pso.setString(1, updatedNote);
                pso.setInt(2, Integer.parseInt(noteId));
                pso.executeUpdate();
                resp.sendRedirect("../notes");
              /*  resp.sendRedirect("http://localhost:8081/Note/notes");*/
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
