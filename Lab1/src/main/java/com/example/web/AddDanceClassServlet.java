package com.example.web;

import com.example.service.DanceClassService;
import com.example.models.DanceClass;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddDanceClassServlet", value = "/add-dance-class")
public class AddDanceClassServlet extends HttpServlet {

    private DanceClassService danceClassService = new DanceClassService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String level = request.getParameter("level");
        String schedule = request.getParameter("schedule");
        int studioId = Integer.parseInt(request.getParameter("studioId"));

        DanceClass danceClass = new DanceClass();
        danceClass.setName(name);
        danceClass.setLevel(level);
        danceClass.setSchedule(schedule);
        danceClass.setStudioId(studioId);

        try {
            // Добавление нового занятия в базу данных
            danceClassService.addDanceClass(danceClass);
        } catch (SQLException e) {
            throw new ServletException("Error adding dance class", e);
        }

        // После добавления, перенаправляем на страницу с занятиями
        response.sendRedirect("dance-classes");
    }
}

