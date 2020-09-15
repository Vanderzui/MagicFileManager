package com.controller;

import com.service.SimpleFileService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//отвечает за открытие\редактирование
@WebServlet(name = "com.controller.ControllerFileOpenServlet")

public class ControllerFileOpenServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        SimpleFileService simpleFileService = new SimpleFileService();
//        List<String> listIndexPage = simpleFileService.getListFiles("D:", "myDir");
//        request.setAttribute("zzz", listIndexPage);
//        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
//        view.forward(request, response);


}

