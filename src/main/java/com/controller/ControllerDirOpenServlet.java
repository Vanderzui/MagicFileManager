package com.controller;

import com.dto.FileDto;
import com.service.FileService;
import com.service.SimpleFileService;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "com.controller.ControllerDirOpenServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024 * 100)
public class ControllerDirOpenServlet extends HttpServlet {
    private FileService simpleFileService = new SimpleFileService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contextPath = simpleFileService.getRootDir() + simpleFileService.checkURL(req.getRequestURI()).substring(5);
        if (new File(contextPath).isDirectory()) {
            List<FileDto> myFiles = simpleFileService.getFileNames(contextPath);
            List<FileDto> myDir = simpleFileService.getDirectoryNames(contextPath);
            String urlDir = simpleFileService.checkURL(req.getRequestURI());
            String urlFile = simpleFileService.checkURL(req.getRequestURI()).replace("root", "file");
            String urlDownload = simpleFileService.checkURL(req.getRequestURI()).replace("root", "download");
            req.setAttribute("urlDir", urlDir);
            req.setAttribute("urlFile", urlFile);
            req.setAttribute("directories", myDir);
            req.setAttribute("files", myFiles);
            req.setAttribute("download", urlDownload);
            String back = simpleFileService.checkURL(req.getRequestURI()) + "/..";
            req.setAttribute("back", back);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(simpleFileService.checkURL(req.getContextPath()) + "/openDir.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String contextPath = simpleFileService.getRootDir() + req.getRequestURI().substring(5);
        if (req.getParameter("local") != null) {
            req.getSession(true).setAttribute("local", req.getParameter("local"));
            doGet(req, resp);
        }
        if (ServletFileUpload.isMultipartContent(req)) {
            uploadFile(req, resp);
        }
        if (req.getParameter("delete") != null) {
            doDelete(req, resp);
        } else {
            simpleFileService.getFileNames(contextPath);
            simpleFileService.getDirectoryNames(contextPath);
            String dirName = req.getParameter("dirName");
            simpleFileService.createDirectory(contextPath, dirName);
            String fileName = req.getParameter("fileName");
            simpleFileService.createFile(contextPath, fileName);
            doGet(req, resp);
        }

    }

    private void uploadFile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dirForSave;
        if (req.getPathInfo() == null) {
            dirForSave = simpleFileService.getRootDir();
        } else {
            dirForSave = simpleFileService.getRootDir() + File.separator + req.getPathInfo();
        }
        String fileName = null;
        for (Part part : req.getParts()) {
            fileName = simpleFileService.getFileName(part);
            part.write(dirForSave + File.separator + fileName);
        }
        doGet(req, resp);
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contextPath = simpleFileService.getRootDir() + req.getRequestURI().substring(5);
        String fileToDelete = req.getParameter("delete");
        simpleFileService.deleteNote(simpleFileService.checkURL(req.getRequestURI()) + "/" + fileToDelete);
        simpleFileService.delete(contextPath + "/" + fileToDelete);
        doGet(req, resp);
    }
}

