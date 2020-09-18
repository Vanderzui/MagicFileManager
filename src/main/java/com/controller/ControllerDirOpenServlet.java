package com.controller;

import com.service.FileService;
import com.service.SimpleFileService;
import com.sun.org.apache.xerces.internal.impl.io.UTF8Reader;
import com.sun.xml.internal.bind.v2.runtime.output.UTF8XmlOutput;
import com.sun.xml.internal.stream.writers.UTF8OutputStreamWriter;

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
    private FileService simpleFileService =  new SimpleFileService();
    public String root = "D:/myDir";

    //для открытия в новом окне
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contextPath = root + req.getRequestURI();
        if(new File(contextPath).isDirectory()) {
            List<String> myFiles = simpleFileService.getFileNames(contextPath);
            List<String> myDir = simpleFileService.getDirectoryNames(contextPath);
            String url = req.getRequestURI();
            req.setAttribute("url", url);
            req.setAttribute("directories", myDir);
            req.setAttribute("files", myFiles);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("openDir.jsp");
            requestDispatcher.forward(req, resp);


        }


    }

    //для редактирования
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        SimpleFileService simpleFileService = new SimpleFileService();
//        List<String> listIndexPage = simpleFileService.getListFiles("D:", "myDir");
//        request.setAttribute("zzz", listIndexPage);
//        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
//        view.forward(request, response);


}

