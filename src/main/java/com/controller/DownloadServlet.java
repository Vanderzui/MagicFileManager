package com.controller;

import com.service.FileService;
import com.service.SimpleFileService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DownloadServlet extends HttpServlet {
    private FileService simpleFileService = new SimpleFileService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String filePath = req.getPathInfo();
        try (FileInputStream fis = new FileInputStream(simpleFileService.getRootDir() + filePath)) {
            resp.setContentType("text/plain");
            resp.setHeader("Content-Disposition", "attachment;filename=");
            OutputStream os = resp.getOutputStream();
            simpleFileService.downloadFile(fis, os);
        }
    }
}
