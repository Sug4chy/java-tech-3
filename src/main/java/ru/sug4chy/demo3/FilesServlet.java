package ru.sug4chy.demo3;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/files")
public class FilesServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String path = request.getParameter("path");
        File[] directories = getDirectories(path);
        if (directories == null) {
            directories = new File[0];
        }

        File[] files = getFiles(path);
        if (files == null) {
            files = new File[0];
        }

        request.setAttribute("directories", directories);
        request.setAttribute("files", files);
        var dispatcher = request.getRequestDispatcher("files.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    private File[] getDirectories(String path) {
        if (path == null) {
            path = "C:\\";
        }
        return new File(path).listFiles(File::isDirectory);
    }

    private File[] getFiles(String path) {
        if (path == null) {
            path = "C:\\";
        }
        return new File(path).listFiles(File::isFile);
    }
}