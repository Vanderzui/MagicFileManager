package com.controller;

import com.service.SimpleFileService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

//отвечает за создание\удаление
@WebServlet(name = "com.controller.ControllerServlet",urlPatterns = "")
public class GetListFilesServlet extends HttpServlet {
    private SimpleFileService simpleFileService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        simpleFileService = new SimpleFileService();
        List<String> myFiles = simpleFileService.getFileNames("D:", "myDir");
        List<String> myDir = simpleFileService.getDirectoryNames("D:", "myDir");
        req.setAttribute("files", myFiles);
        req.setAttribute("directories", myDir);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(req, resp);
    }
}
