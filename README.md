# Student Management System 💻✨ : Created by @iamvisshu
## 📃Table of Contents

* About Student Management System 💻✨
* Features
* Technologies Used
* Prerequisites
* Setup Instructions
* Manual Deployment on Tomcat
* Access MySQL Client
* SQL Commands
* Contributing
* License
* Author

## 📌About Student Management System ✨
The Student Management System is a comprehensive web application designed for efficient management of student data. Built using Java, Servlets, and MySQL for the backend and HTML, CSS, and JavaScript for the frontend, this project aims to provide a seamless experience for handling basic CRUD operations.

**Key features include:**

- Adding, editing, deleting, and listing students.
- An intuitive web-based UI/UX.
- Integration with MySQL for robust data management.

Whether you’re an educator, administrator, or developer, this system offers a practical solution for managing student information efficiently.

## ⭐Features

- Basic CRUD operations with a simple web interface.
- Add, edit, delete, and show list of students.
- Perform all operations via UI/UX (WebPages).

## 🔩Technologies Used

- Java 8
- Servlets
- MySQL
- HTML/CSS
- JavaScript

## 📋Prerequisites

- Java 8 or higher
- Maven
- MySQL
- Apache Tomcat Server

## 🔧Setup Instructions

### 1. Clone the Repository

- In my case it was copied on path `C:\Users\xyz\Documents`

```bash
git clone https://github.com/iamvisshu/StudentManagementSystem.git
cd StudentManagementSystem
```
### 2. Import Project in your IDE
* Launch Intellij Idea IDE.
* Go to >> New >> Project from existing Sources >> select you project folder and import it.
* Right click on project and build it by option "Build Module".
* Or goto terminal and type `mvn clean install` to build the project.

## 3. Run the Project
* Follow below steps to run the project.
* NOTE : Create table and add some dummy data, both things by using given sql files : `StudentManagementSystem\src\main\resources\db\schema.sql and data.sql`

## ☑️Manual Deployment on Tomcat
1. **Package Your Application:** Ensure your Java project is packaged as a `.war` file. In IntelliJ IDEA, go to Build > Build Artifacts > Build.

2. **Locate the .war File:** The `.war` file will typically be found in the target directory of your project. In my case it was on path `C:\Users\xyz\Documents\StudentManagementSystem\target\StudentManagementSystem-1.0-SNAPSHOT.war`

3. **Copy .war to Tomcat:** Navigate to your Tomcat installation directory and find the webapps folder (example: `C:\Users\xyz\Documents\apache-tomcat-9.0.93\webapps`).

4. **Deploy .war File:** Copy your `StudentManagementSystem-1.0-SNAPSHOT.war` file to the webapps directory.

5. **Start Tomcat:** Go to path `C:\Users\xyz\Documents\apache-tomcat-9.0.93\bin`. Start your Tomcat server by running the `startup.bat` (Windows) or startup.sh (Linux/macOS) script found in the bin directory of your Tomcat installation.

6. **Access Your Application:** Open your browser and go to `http://localhost:8080/StudentManagementSystem-1.0-SNAPSHOT/`

## ☑️Access MySQL Client

### On Windows
1. Navigate to the MySQL installation directory: Typically, this is `C:\Program Files\MySQL\MySQL Server 8.0\bin`.

2. Open Command Prompt: Press `Win + R`, type `cmd`, and hit Enter.

3. Run MySQL Client: In the Command Prompt, navigate to the MySQL bin directory using `cd C:\Program Files\MySQL\MySQL Server 8.0\bin`.

4. Connect to MySQL: Run `mysql -u root -p` and enter your password when prompted.

### On macOS/Linux
1. Open Terminal: Open your Terminal app.

2. Run MySQL Client: Simply type `mysql -u root -p` and hit Enter. Enter your password when prompted.

## ☑️SQL Commands

```bash
mysql> mysql -u root -p
mysql> USE student_db;
mysql> select * from students;
```
## 🌄IDE Screenshots
- Tomcat setup in IDE
  ![image](https://github.com/user-attachments/assets/9b0a8676-b6eb-49a6-a655-044963b7c3c3)

- Debug in IDE
  ![image](https://github.com/user-attachments/assets/a1d1eed1-e652-4923-9f71-a63837bd5e7f)

- Poject Structure
  ![image](https://github.com/user-attachments/assets/3a9df3a4-1303-4e61-9c0a-5c5f7ef8212a)




## 👫 Contributing:

We welcome contributions to this project! If you'd like to contribute, please:

1. Fork the repository on GitHub.
2. Make your changes and create a pull request.
3. Follow any coding style guidelines mentioned in the code (if any).

## 💳License:

* This project is licensed under the Open-source [**MIT License**].

## ✒️Author:
* @iamvisshu - Vishal Vishwakarma 😇
