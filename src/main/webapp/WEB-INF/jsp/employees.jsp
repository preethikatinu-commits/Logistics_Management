<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employees</title>
</head>
<body>
<h2>Employee List</h2>
<a href="${pageContext.request.contextPath}/web/employees/new">Add New Employee</a>
<table border="1">
    <tr>
        <th>ID</th><th>Name</th><th>Department</th><th>Role</th><th>Salary</th><th>Actions</th>
    </tr>
    <c:forEach items="${employees}" var="emp">
        <tr>
            <td>${emp.id}</td>
            <td>${emp.name}</td>
            <td>${emp.department}</td>
            <td>${emp.role}</td>
            <td>${emp.salary}</td>
            <td>
                <a href="${pageContext.request.contextPath}/web/employees/edit/${emp.id}">Edit</a> |
                <a href="${pageContext.request.contextPath}/web/employees/delete/${emp.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
