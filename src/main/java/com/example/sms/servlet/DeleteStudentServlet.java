package com.example.sms.servlet;

import com.example.sms.dao.StudentDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/delete-student")
public class DeleteStudentServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(DeleteStudentServlet.class);
    private StudentDAO studentDAO;

    public void init() {
        studentDAO = new StudentDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            studentDAO.deleteStudent(id);
        } catch (Exception e) {
            logger.error("Exception occurred while deleting : ", e);
        }
        response.sendRedirect("list-students.html");
    }
}
