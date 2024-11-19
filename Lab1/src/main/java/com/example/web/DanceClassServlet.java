package com.example.web;

import com.example.service.DanceClassService;
import com.example.models.DanceClass;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "DanceClassServlet", value = "/dance-classes")
public class DanceClassServlet extends HttpServlet {

    private DanceClassService danceClassService = new DanceClassService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<DanceClass> danceClassList = danceClassService.getAll();
            request.setAttribute("danceClasses", danceClassList);
            request.getRequestDispatcher("/danceClassList.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error fetching dance classes", e);
        }
    }
}
