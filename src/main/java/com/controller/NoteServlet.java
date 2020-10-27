package com.controller;

import com.service.FileService;
import com.service.SimpleFileService;
import lombok.SneakyThrows;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoteServlet extends HttpServlet {
    private FileService simpleFileService = new SimpleFileService();

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        String contextPath = simpleFileService.checkURL(req.getRequestURI());
        String openNote = simpleFileService.openNote(contextPath);
        req.setAttribute("note", openNote);
        req.setAttribute("close", simpleFileService.checkURL(req.getRequestURI())
                .replace("/note", "")
                .replace("file", "root") + "/..");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/note.jsp");
        requestDispatcher.forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("local") != null) {
            req.getSession(true).setAttribute("local", req.getParameter("local"));
        } else {
            String contextPath = simpleFileService.checkURL(req.getRequestURI());
            String inputText = req.getParameter("input");
            simpleFileService.makeNote(contextPath, inputText);
        }
        doGet(req, resp);
    }
}
