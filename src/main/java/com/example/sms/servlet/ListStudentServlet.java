package com.example.sms.servlet;

import com.example.sms.dao.StudentDAO;
import com.example.sms.model.Student;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet("/list-students")
public class  ListStudentServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ListStudentServlet.class);
    private StudentDAO studentDAO;

    public void init() {
        studentDAO = new StudentDAO();
        logger.info("ListStudentServlet initialized and StudentDAO instance created.");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<Student> listStudents = null;
        try {
            listStudents = studentDAO.selectAllStudents();
            logger.info("Fetched {} students from the database.", listStudents.size());
        } catch (Exception e) {
            logger.error("Error fetching students from database.", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Unable to retrieve student list.\"}");
            return;
        }

        // Convert listStudents to JSON
        Gson gson = new Gson();
        String json = gson.toJson(listStudents);
        response.getWriter().write(json);
    }

}
