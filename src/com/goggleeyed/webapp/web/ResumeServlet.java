package com.goggleeyed.webapp.web;

import com.goggleeyed.webapp.Config;
import com.goggleeyed.webapp.model.Resume;
import com.goggleeyed.webapp.storage.Storage;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body><table border=1 >");
        out.println("<tr><th>uuid</th><th>fullName</th><tr>");
        Storage storage = Config.get().getStorage();
        List<Resume> resumes = storage.getAllSorted();
        for (Resume resume : resumes) {
            out.println("<tr><td>" + resume.getUuid() + "</td><td>" + resume.getFullName() + "</td><tr>");
        }
        out.println("</table></body></html>");

//        String name = request.getParameter("name");
//        response.getWriter().write(name == null ? "Hello Resumes!" : "Hello " + name + '!');
    }
}