<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Job Portal</title>
</head>
<body>
    <h2>Welcome to the Job Portal</h2>
    <form action="/api/applyJobs" method="get">
        <button type="submit">Apply Jobs</button>
    </form><br><br>
    <form action="/api/registerJob" method="get">
        <button type="submit">Register Job</button>
    </form>
</body>
</html>
