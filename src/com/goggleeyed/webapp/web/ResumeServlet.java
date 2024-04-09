package com.goggleeyed.webapp.web;

import com.goggleeyed.webapp.Config;
import com.goggleeyed.webapp.model.*;
import com.goggleeyed.webapp.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {

    private Storage storage; // = Config.get().getStorage();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume r;
        try {
            r = storage.get(uuid);
        } catch (Exception e) {
            r = new Resume(uuid, fullName);
            storage.save(r);
        }
        r.setFullName(fullName);
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && !value.trim().isEmpty()) {
                r.addContact(type, value);
            } else {
                r.getContacts().remove(type);
            }
        }
        for (SectionType type : SectionType.values()) {
            String value;
            switch (type) {
                case PERSONAL:
                case OBJECTIVE:
                    value = request.getParameter(type.name());
                    if (value != null && !value.trim().isEmpty()) {
                        r.addSection(type, new TextSection(value));
                    } else {
                        r.getSections().remove(type);
                    }
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    value = request.getParameter(type.name());
                    if (value != null && !value.trim().isEmpty()) {
                        r.addSection(type, new ListSection(value.split("\n")));
                    } else {
                        r.getSections().remove(type);
                    }
                    break;
                case EXPERIENCE:
                case EDUCATION:
                    String[] names = request.getParameterValues(type.name());
                    if (names == null || names.length == 0) {
                        r.getSections().remove(type);
                        break;
                    }
                    String[] urls = request.getParameterValues(type.name());
                    List<Organization> organizations = new ArrayList<>();
                    for (int i = 0; i < names.length; i++) {
                        String name = names[i];
                        String url = urls[i];

                        String[] startDates = request.getParameterValues(type.name() + i + "startDate");
                        String[] endDates = request.getParameterValues(type.name() + i + "endDate");
                        String[] titles = request.getParameterValues(type.name() + i + "title");
                        String[] descriptions = request.getParameterValues(type.name() + i + "description");
                        List<Organization.Position> positions = new ArrayList<>();
                        for (int j = 0; j < startDates.length; j++) {
                            positions.add(new Organization.Position(LocalDate.parse(startDates[j] + "-01"),
                                    LocalDate.parse(endDates[j] + "-01"), titles[j], descriptions[j]));
                        }
                        organizations.add(new Organization(new Link(name, url), positions));
                    }
                    r.addSection(type, new OrganizationSection(organizations));
                    break;
            }

        }
        storage.update(r);
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
            case "edit":
                r = storage.get(uuid);
                break;
            case "create":
                r = new Resume(uuid, "");
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }
}