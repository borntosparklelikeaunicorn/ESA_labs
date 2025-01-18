package com.example.servlets;

import com.example.models.DanceClass;
import com.example.service.DanceClassService;
import com.example.servlets.HelloServlet;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/classes")
public class DanceClassServlet extends HelloServlet {
    @EJB
    private DanceClassService classService;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<DanceClass> classes = classService.getAll();
        request.setAttribute("danceClassList", classes);
        try {
            getServletContext().getRequestDispatcher("/danceClassList.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String style = request.getParameter("style");
        String level = request.getParameter("level");
        String schedule = request.getParameter("schedule");
        int studio_id = Integer.parseInt(request.getParameter("studio_id"));

        DanceClass newClass = new DanceClass();
        newClass.setName(style);
        newClass.setLevel(level);
        newClass.setSchedule(schedule);
        newClass.setStudioId(studio_id);
        classService.save(newClass);
        doGet(request, response);
    }
}