package com.example.sms.dao;

import com.example.sms.model.Student;
import com.example.sms.utils.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private static final Logger logger = LoggerFactory.getLogger(StudentDAO.class);
    private static final String INSERT_STUDENTS_SQL = "INSERT INTO students (name, email, phone, course) VALUES (?, ?, ?, ?)";
    private static final String SELECT_STUDENT_BY_ID = "SELECT id, name, email, phone, course FROM students WHERE id = ?";
    private static final String SELECT_ALL_STUDENTS = "SELECT * FROM students";
    private static final String DELETE_STUDENT_SQL = "DELETE FROM students WHERE id = ?";
    private static final String UPDATE_STUDENT_SQL = "UPDATE students SET name = ?, email = ?, phone = ?, course = ? WHERE id = ?";

    public void insertStudent(Student student) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_STUDENTS_SQL);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getPhone());
            preparedStatement.setString(4, student.getCourse());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error inserting student: ", e);
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) DBConnection.closeConnection(); // Close the connection
            } catch (SQLException e) {
                logger.error("Error closing resources: ", e);
            }
        }
    }

    public Student selectStudent(int id) {
        Student student = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String course = rs.getString("course");
                student = new Student(id, name, email, phone, course);
            }
        } catch (SQLException e) {
            logger.error("Error selecting student by ID: ", e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) DBConnection.closeConnection(); // Close the connection
            } catch (SQLException e) {
                logger.error("Error closing resources: ", e);
            }
        }
        return student;
    }

    public List<Student> selectAllStudents() {
        List<Student> students = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String course = rs.getString("course");
                students.add(new Student(id, name, email, phone, course));
            }
        } catch (SQLException e) {
            logger.error("Error selecting all students: ", e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) DBConnection.closeConnection(); // Close the connection
            } catch (SQLException e) {
                logger.error("Error closing resources: ", e);
            }
        }
        return students;
    }

    public boolean deleteStudent(int id) {
        boolean rowDeleted = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement(DELETE_STUDENT_SQL);
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error deleting student: ", e);
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) DBConnection.closeConnection(); // Close the connection
            } catch (SQLException e) {
                logger.error("Error closing resources: ", e);
            }
        }
        return rowDeleted;
    }

    public boolean updateStudent(Student student) {
        boolean rowUpdated = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement(UPDATE_STUDENT_SQL);
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getPhone());
            statement.setString(4, student.getCourse());
            statement.setInt(5, student.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error updating student: ", e);
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) DBConnection.closeConnection(); // Close the connection
            } catch (SQLException e) {
                logger.error("Error closing resources: ", e);
            }
        }
        return rowUpdated;
    }
}
