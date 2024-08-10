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

@WebServlet("/note/delete")
public class DeleteNoteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String noteId = req.getParameter("id");

        try (Connection conn = DatabaseUtils.getConnection()) {
            String sql = "DELETE FROM notes WHERE id = ?";
            try (PreparedStatement pso = conn.prepareStatement(sql)) {
                pso.setInt(1, Integer.parseInt(noteId));
                pso.executeUpdate();
                resp.sendRedirect("../notes");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}

