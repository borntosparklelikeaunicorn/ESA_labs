package com.example.web;

import com.example.models.Studio;
import com.example.service.DanceStudioService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "DanceStudioServlet", value = "/studios")
public class DanceStudioServlet extends HttpServlet {

    private DanceStudioService studioService = new DanceStudioService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Studio> studioList = studioService.getAll();
            request.setAttribute("studios", studioList);
            request.getRequestDispatcher("/studioList.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error fetching studios", e);
        }
    }
}
