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
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
public class ControllerDirOpenServlet extends HttpServlet {
    public static final String LOCAL = "local";
    public static final String UTF_8 = "UTF-8";
    public static final String DIR_NAME = "dirName";
    public static final String FILE_NAME = "fileName";
    private FileService simpleFileService = new SimpleFileService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contextPath = simpleFileService.getRootDir() +
                simpleFileService.checkURL(req.getRequestURI()).substring(5);
        if (new File(contextPath).isDirectory()) {
            setAttributes(req, contextPath);
            RequestDispatcher requestDispatcher =
                    req.getRequestDispatcher(simpleFileService.checkURL(req.getContextPath()) + "/openDir.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    private void setAttributes(HttpServletRequest req, String contextPath) throws IOException {
        List<FileDto> myFiles = simpleFileService.getFileNames(contextPath);
        List<FileDto> myDir = simpleFileService.getDirectoryNames(contextPath);
        String urlDir = simpleFileService.checkURL(req.getRequestURI());
        String urlFile = simpleFileService.checkURL(req.getRequestURI())
                .replace("root", "file");
        String urlDownload = simpleFileService.checkURL(req.getRequestURI())
                .replace("root", "download");
        req.setAttribute("urlDir", urlDir);
        req.setAttribute("urlFile", urlFile);
        req.setAttribute("directories", myDir);
        req.setAttribute("files", myFiles);
        req.setAttribute("download", urlDownload);
        String back = simpleFileService.checkURL(req.getRequestURI()) + "/..";
        req.setAttribute("back", back);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding(UTF_8);
        String contextPath = simpleFileService.getRootDir() + req.getRequestURI().substring(5);
        if (req.getParameter(LOCAL) != null) {
            req.getSession(true).setAttribute(LOCAL, req.getParameter(LOCAL));
            doGet(req, resp);
        }else if (ServletFileUpload.isMultipartContent(req)) {
            uploadFile(req, resp);
        }else if (req.getParameter("delete") == null) {
            createFileOrDirectory(req, contextPath);
            doGet(req, resp);
        } else {
            doDelete(req, resp);
        }
    }

    private void createFileOrDirectory(HttpServletRequest req, String contextPath) throws IOException {
        simpleFileService.getFileNames(contextPath);
        simpleFileService.getDirectoryNames(contextPath);
        String dirName = req.getParameter(DIR_NAME);
        simpleFileService.createDirectory(contextPath, dirName);
        String fileName = req.getParameter(FILE_NAME);
        simpleFileService.createFile(contextPath, fileName);
    }

    private void uploadFile(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String dirForSave;
        if (req.getPathInfo() == null) {
            dirForSave = simpleFileService.getRootDir();
        } else {
            dirForSave = simpleFileService.getRootDir() + File.separator + req.getPathInfo();
        }
        String fileName;
        for (Part part : req.getParts()) {
            fileName = simpleFileService.getFileName(part);
            part.write(dirForSave + File.separator + fileName);
        }
        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String contextPath = simpleFileService.getRootDir() + req.getRequestURI().substring(5);
        String fileToDelete = req.getParameter("delete");
        simpleFileService.deleteNote(simpleFileService.checkURL(req.getRequestURI())
                                    + "/" + fileToDelete);
        simpleFileService.delete(contextPath + "/" + fileToDelete);
        try {
            doGet(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}

