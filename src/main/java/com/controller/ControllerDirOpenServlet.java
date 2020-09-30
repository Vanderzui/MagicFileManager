package com.controller;

import com.dto.FileDto;
import com.service.FileService;
import com.service.SimpleFileService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import static org.apache.commons.fileupload.FileUploadBase.CONTENT_DISPOSITION;

//отвечает за открытие\редактирование
@WebServlet(name = "com.controller.ControllerDirOpenServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024 * 100)
public class ControllerDirOpenServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "upload";
    private FileService simpleFileService = new SimpleFileService();
    public static final String ROOT = "D:/myDir";
    private static final String UPLOAD_DIRECTORY = "D:/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contextPath = ROOT + simpleFileService.checkURL(req.getRequestURI()).substring(5);
        if (new File(contextPath).isDirectory()) {
            List<FileDto> myFiles = simpleFileService.getFileNames(contextPath);
            List<FileDto> myDir = simpleFileService.getDirectoryNames(contextPath);
            String urlDir = simpleFileService.checkURL(req.getRequestURI());
            String urlFile = simpleFileService.checkURL(req.getRequestURI()).replace("root", "file");
            req.setAttribute("urlDir", urlDir);
            req.setAttribute("urlFile", urlFile);
            req.setAttribute("directories", myDir);
            req.setAttribute("files", myFiles);
            String back = simpleFileService.checkURL(req.getRequestURI()) + "/..";
            req.setAttribute("back", back);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(simpleFileService.checkURL(req.getContextPath()) + "/openDir.jsp");
            requestDispatcher.forward(req, resp);

        }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contextPath = ROOT + req.getRequestURI().substring(5);
        req.getSession(true).setAttribute("local", req.getParameter("local"));
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
            dirForSave = ROOT;
        } else {
            dirForSave = ROOT + File.separator + req.getPathInfo();
        }
        String fileName = null;
        for (Part part : req.getParts()) {
            fileName = getFileName(part);
            part.write(dirForSave + File.separator + fileName);
        }
        req.setAttribute("messageUpload", fileName + " File uploaded successfully!");
        doGet(req, resp);
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= " + contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contextPath = ROOT + req.getRequestURI().substring(5);
        String fileToDelete = req.getParameter("delete");
        simpleFileService.deleteNote(simpleFileService.checkURL(req.getRequestURI()) + "/" + fileToDelete);
        simpleFileService.delete(contextPath + "/" + fileToDelete);
        doGet(req, resp);
    }
}

