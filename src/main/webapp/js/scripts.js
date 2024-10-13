document.getElementById('add-student-form').addEventListener('submit', function (e) {
    e.preventDefault();

    const name = document.querySelector('input[name="name"]').value;
    const email = document.querySelector('input[name="email"]').value;
    const phone = document.querySelector('input[name="phone"]').value;
    const course = document.querySelector('input[name="course"]').value;

    fetch('/StudentManagementSystem-1.0-SNAPSHOT/add-student', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({ name, email, phone, course })
    })
    .then(response => response.text())
    .then(data => {
        console.log('Student added:', data);
        // Redirect to the student list page after adding the student
        window.location.href = '/StudentManagementSystem-1.0-SNAPSHOT/list-students.html';
    })
    .catch(error => console.error('Error:', error));
});

// Function to load students and display them
function loadStudents() {
    fetch('/StudentManagementSystem-1.0-SNAPSHOT/list-students')
        .then(response => {
            if (response.ok) {
                return response.json(); // Parse JSON response
            } else {
                throw new Error('Network response was not ok.');
            }
        })
        .then(data => {
            const studentList = document.getElementById('student-list');
            if (data.length > 0) {
                studentList.innerHTML = `
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Course</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            ${data.map(student => `
                                <tr>
                                    <td>${student.id}</td>
                                    <td>${student.name}</td>
                                    <td>${student.email}</td>
                                    <td>${student.phone}</td>
                                    <td>${student.course}</td>
                                    <td>
                                        <a href="/StudentManagementSystem-1.0-SNAPSHOT/edit-student?id=${student.id}">Edit</a>
                                        <a href="/StudentManagementSystem-1.0-SNAPSHOT/delete-student?id=${student.id}" onclick="return confirm('Are you sure?');">Delete</a>
                                    </td>
                                </tr>
                            `).join('')}
                        </tbody>
                    </table>
                `;
            } else {
                studentList.innerHTML = '<p>No students found.</p>';
            }
        })
        .catch(error => {
            console.error('Error fetching students:', error);
            document.getElementById('student-list').innerHTML = '<p>Error loading student data.</p>';
        });
}

// Trigger the loadStudents function when the page loads
window.onload = function() {
    if (document.getElementById('student-list')) {
        loadStudents();
    }
};
