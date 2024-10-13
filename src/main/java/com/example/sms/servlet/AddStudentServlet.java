package com.example.sms.servlet;

import com.example.sms.dao.StudentDAO;
import com.example.sms.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@WebServlet("/add-student")
public class AddStudentServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(AddStudentServlet.class);
    private StudentDAO studentDAO;

    public void init() {
        studentDAO = new StudentDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String course = request.getParameter("course");

        Student student = new Student(0, name, email, phone, course);
        try {
            studentDAO.insertStudent(student);
        } catch (Exception e) {
            logger.error("Exception occurred while adding : ", e);
        }
        response.sendRedirect("list-students.html");
    }
}
