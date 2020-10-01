package com.controller;

import com.dto.FileDto;
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
    private FileService simpleFileService = new SimpleFileService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contextPath = simpleFileService.getRootDir() + req.getRequestURI().substring(5);
        if (new File(contextPath).isFile()) {
            FileDto open = simpleFileService.openFile(contextPath);
            req.setAttribute("result", open.getText());
            req.setAttribute("close", simpleFileService.checkURL(req.getRequestURI()).replace("file", "root") + "/..");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(req.getContextPath() + "/openFile.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("local") != null) {
            req.getSession(true).setAttribute("local", req.getParameter("local"));
            doGet(req, resp);
        } else {
            String input = req.getParameter("input");
            String contextPath = simpleFileService.getRootDir() + req.getRequestURI().substring(5);
            simpleFileService.write(contextPath, input);
            //String change = simpleFileService.openFile(contextPath) + input;
            //req.setAttribute("result", change);
            doGet(req, resp);
        }
    }
}
