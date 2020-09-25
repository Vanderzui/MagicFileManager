package com.controller;

import com.service.FileService;
import com.service.SimpleFileService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoteServlet extends HttpServlet {
    private FileService simpleFileService = new SimpleFileService();
    public String root = "D:/myDir";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contextPath = root + req.getRequestURI().substring(5);


    }
}
