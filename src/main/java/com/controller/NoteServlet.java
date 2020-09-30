package com.controller;

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
        String contextPath = simpleFileService.checkURL(req.getRequestURI());
        Map<String, String> notesMap = simpleFileService.openNote(contextPath);
        req.setAttribute("note", notesMap);
        req.setAttribute("close", simpleFileService.checkURL(req.getRequestURI()).replace("/note", "").replace("file", "root") + "/..");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/note.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("local") != null) {
            req.getSession(true).setAttribute("local", req.getParameter("local"));
            doGet(req, resp);
        } else {
            String contextPath = simpleFileService.checkURL(req.getRequestURI());
            String inputText = req.getParameter("input");
            simpleFileService.makeNote(contextPath, inputText);
            doGet(req, resp);
        }
    }
}
