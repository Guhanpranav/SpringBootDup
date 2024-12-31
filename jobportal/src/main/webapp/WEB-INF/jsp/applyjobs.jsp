<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <title>Jobs List</title>
</head>
<body>
    <h2>Jobs List</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Job Title</th>
                <th>Job Description</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%
            List<com.example.jobportal.entity.Jobs> jobsList = (List<com.example.jobportal.entity.Jobs>)request.getAttribute("jobs");
            for(com.example.jobportal.entity.Jobs job : jobsList){ %>
                <tr>
                    <td><%= job.getTitle() %></td>
                    <td><%= job.getDescription() %></td>
                    <td>
                        <form action="/api/apply/<%= job.getId() %>" method="get">
                            <button type="submit">Apply</button>
                        </form>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
