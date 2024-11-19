package com.example.web;

import com.example.models.Studio;
import com.example.service.DanceStudioService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddStudioServlet", value = "/add-studio")
public class AddStudioServlet extends HttpServlet {

    private DanceStudioService studioService = new DanceStudioService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        Studio studio = new Studio();
        studio.setName(name);
        studio.setAddress(address);
        studio.setPhone(phone);

        try {
            studioService.addStudio(studio);
        } catch (SQLException e) {
            throw new ServletException("Error adding studio", e);
        }

        response.sendRedirect("studios");
    }
}
