package com.controller;

import com.dto.FileDto;
import com.service.FileService;
import com.service.SimpleFileService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class NoteServlet extends HttpServlet {
    private FileService simpleFileService = new SimpleFileService();
    public String root = "D:/myDir";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contextPath = root + req.getRequestURI().substring(5);
//        String fileName = req.getParameter("fileName");
        Map<String, String> notesMap = simpleFileService.openNote(contextPath);
        req.setAttribute("note", notesMap);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(req.getContextPath() + "/note.jsp");
        requestDispatcher.forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contextPath = root + req.getRequestURI().substring(5);
        String fileName = req.getParameter("fileName");
        String inputText = req.getParameter("input");
        simpleFileService.makeNote(contextPath, fileName, inputText);
        doGet(req, resp);
    }
}
