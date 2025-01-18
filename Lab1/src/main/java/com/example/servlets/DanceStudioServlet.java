package com.example.servlets;

import com.example.models.DanceClass;
import com.example.models.DanceStudio;
import com.example.service.DanceClassService;
import com.example.service.DanceStudioService;
import com.example.servlets.HelloServlet;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/studios")
public class DanceStudioServlet extends HelloServlet {
    @EJB
    private DanceStudioService studioService;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<DanceStudio> studios = studioService.getAll();
        request.setAttribute("danceStudioList", studios);
        try {
            getServletContext().getRequestDispatcher("/danceStudioList.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        int id = Integer.parseInt(request.getParameter("id"));

        DanceStudio newStudio = new DanceStudio();
        newStudio.setName(name);
        newStudio.setAddress(address);
        newStudio.setPhone(phone);
        newStudio.setId(id);
        studioService.save(newStudio);
        doGet(request, response);
    }


}