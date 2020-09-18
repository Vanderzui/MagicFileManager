package com.controller;

import com.service.FileService;
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
@WebServlet(name = "GetListFilesServlet")
public class GetListFilesServlet extends HttpServlet {
    private FileService simpleFileService = new SimpleFileService();;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> myFiles = simpleFileService.getFileNames("D:/myDir");
        List<String> myDir = simpleFileService.getDirectoryNames("D:/myDir");
        req.setAttribute("directories", myDir);
        req.setAttribute("files", myFiles);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(req, resp);


    }
}
