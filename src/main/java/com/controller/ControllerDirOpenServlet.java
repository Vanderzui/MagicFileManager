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
import java.util.List;

//отвечает за открытие\редактирование
@WebServlet(name = "com.controller.ControllerDirOpenServlet")
public class ControllerDirOpenServlet extends HttpServlet {
    private FileService simpleFileService = new SimpleFileService();
    public String root = "D:/myDir";
    List<FileDto> myFiles;
    List<FileDto> myDir;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contextPath = root + req.getRequestURI().substring(5);
        if (new File(contextPath).isDirectory()) {
            myFiles = simpleFileService.getFileNames(contextPath);
            myDir = simpleFileService.getDirectoryNames(contextPath);
            String urlDir = req.getRequestURI();
            String urlFile = req.getRequestURI().replace("root", "file");
            req.setAttribute("urlDir", urlDir);
            req.setAttribute("urlFile", urlFile);
            req.setAttribute("directories", myDir);
            req.setAttribute("files", myFiles);
            String back = req.getRequestURI() + "/..";
            req.setAttribute("back", back);
//            req.setAttribute("back", req.getRequestURI());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(req.getContextPath() + "/openDir.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contextPath = root + req.getRequestURI().substring(5);
        String fileToDelete = req.getParameter("delete");
        myFiles = simpleFileService.getFileNames(contextPath);
        myDir = simpleFileService.getDirectoryNames(contextPath);
        if(myDir.contains(fileToDelete) || myFiles.contains(fileToDelete)) { //ето хрень не работает, что очевидно
            doDelete(req, resp);
        }
        String dirName = req.getParameter("dirName");
        simpleFileService.createDirectory(contextPath, dirName);
        String fileName = req.getParameter("fileName");
        simpleFileService.createFile(contextPath, fileName);
        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contextPath = root + req.getRequestURI().substring(5);
        String fileToDelete = req.getParameter("delete");
        simpleFileService.delete(contextPath + "/" + fileToDelete);
        doGet(req, resp);
    }
}

