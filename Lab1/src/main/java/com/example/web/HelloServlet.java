package com.example.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "HelloServlet", value = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Перенаправляем на главную страницу
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        // Очистка ресурсов, если необходимо
    }
}
