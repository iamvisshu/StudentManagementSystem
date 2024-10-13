package com.example.sms.dao;

import com.example.sms.model.Student;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentDAOTest {

    private StudentDAO studentDAO = new StudentDAO();

    @Test
    public void testInsertStudent() throws SQLException {
        Student student = new Student(7, "John Doe", "john@example.com", "1234567890", "Computer Science");
        studentDAO.insertStudent(student);
        Student fetchedStudent = studentDAO.selectStudent(student.getId());
        assertEquals("John Doe", fetchedStudent.getName());
    }

    @Test
    public void testSelectAllStudents() {
        List<Student> students = studentDAO.selectAllStudents();
        assertNotNull(students);
    }

    @Test
    public void testDeleteStudent() throws SQLException {
        Student student = new Student(1, "Jane Doe", "jane@example.com", "0987654321", "Mathematics");
        studentDAO.insertStudent(student);
        boolean deleted = studentDAO.deleteStudent(student.getId());
        assertTrue(deleted);
    }

    @Test
    public void testUpdateStudent() throws SQLException {
        Student student = new Student(6, "Jack Doe", "jack@example.com", "1231231234", "Physics");
        studentDAO.insertStudent(student);
        student.setName("Jack Smith");
        boolean updated = studentDAO.updateStudent(student);
        assertTrue(updated);
        Student updatedStudent = studentDAO.selectStudent(student.getId());
        assertEquals("Jack Smith", updatedStudent.getName());
    }
}
