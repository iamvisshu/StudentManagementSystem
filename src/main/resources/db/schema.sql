-- Create the database
CREATE DATABASE IF NOT EXISTS student_db;

-- Switch to the student_db database
USE student_db;

-- Create the students table
CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(15),
    course VARCHAR(100)
);
