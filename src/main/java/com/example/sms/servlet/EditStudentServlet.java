package com.example.sms.servlet;

import com.example.sms.dao.StudentDAO;
import com.example.sms.model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null && request.getHeader("Accept") != null && request.getHeader("Accept").contains("application/json")) {
            // Fetch student data and return JSON (for the fetch request)
            int id = Integer.parseInt(idParam);
            StudentDAO studentDAO = new StudentDAO();
            Student existingStudent = studentDAO.selectStudent(id);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String json = new com.google.gson.Gson().toJson(existingStudent);
            response.getWriter().write(json);
        } else if (idParam != null) {
            // Forward to the HTML form (for direct browser navigation)
            request.getRequestDispatcher("/edit-student.html").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Student ID is missing.");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                String course = request.getParameter("course");

                Student student = new Student(id, name, email, phone, course);
                StudentDAO studentDAO = new StudentDAO();
                studentDAO.updateStudent(student);

                response.sendRedirect("list-students.html");
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid student ID format.");
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating the student.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Student ID is missing.");
        }
    }
}
