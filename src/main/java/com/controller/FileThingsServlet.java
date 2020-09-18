package com.controller;

import com.service.FileService;
import com.service.SimpleFileService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "com.controller.FileThingsServlet")
public class FileThingsServlet extends HttpServlet {
    private FileService simpleFileService =  new SimpleFileService();
    public String root = "D:/myDir";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String contextPath = root + req.getRequestURI();
            if(new File(contextPath).isFile()) {
                String open = simpleFileService.openFile(contextPath);
                req.setAttribute("result", open);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("openFile.jsp");
                requestDispatcher.forward(req, resp);
            }
        }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
