<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <title>Job Registration</title>
</head>
<body>
    <h2>Job Registration Form</h2>
    <form action="/api/registerJob" method="post">
        <label for="title">Job Title:</label><br>
        <input type="text" id="title" name="title" required><br><br>

        <label for="description">description: </label><br>
        <input type="text" id="description" name="description" required><br><br>

         <label for="skill">skill:</label><br>
         <input type="text" id="skill" name="skill" required><br><br>

        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" required><br><br>



        <input type="submit" value="Register">
    </form>
</body>
</html>
