<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
</head>
<body>
    <h2>User Registration Form</h2>
    <form action="/api/registerUser" method="post">
        <label for="userName">User Name:</label><br>
        <input type="text" id="username" name="username" required><br><br>

        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" required><br><br>

        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password" required><br><br>

        <label for="role">Role:</label><br>
                <select id="role" name="role" required>
                    <option value="JOBSEEKER">Job Seeker</option>
                    <option value="JOBPOSTER">Job Poster</option>
                </select><br><br>

        <input type="submit" value="Register">
    </form>
</body>
</html>
