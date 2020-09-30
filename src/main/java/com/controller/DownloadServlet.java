package com.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DownloadServlet extends HttpServlet {
    private static final int BYTES_DOWNLOAD = 1024;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fileName = req.getPathInfo();
        String requestURI = req.getRequestURI();
        resp.setContentType("text/plain"); //;charset=UTF-8
        resp.setHeader("Content-Disposition", "attachment;filename=downloadname.txt");
        ServletContext ctx = getServletContext();
        InputStream is = ctx.getResourceAsStream("/testing.txt");
//        InputStream is = ctx.getResourceAsStream("D:/myDir" + fileName); хзззззззззззззз

        int read = 0;
        byte[] bytes = new byte[BYTES_DOWNLOAD];
        OutputStream os = resp.getOutputStream();

        while ((read = is.read(bytes)) != -1) {
            os.write(bytes, 0, read);
        }
        os.flush();
        os.close();
    }
}
