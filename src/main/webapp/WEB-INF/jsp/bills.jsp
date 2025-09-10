<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Bills</title>
</head>
<body>
<h2>Bills for Employee ID: ${employeeId}</h2>

<a href="/web/bills/new?employeeId=${employeeId}">Add New Bill</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Amount</th>
        <th>Date</th>
        <th>Description</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${bills}" var="bill">
        <tr>
            <td>${bill.id}</td>
            <td>${bill.amount}</td>
            <td>${bill.date}</td>
            <td>${bill.description}</td>
            <td>
                <a href="/web/bills/edit/${bill.id}">Edit</a> |
                <a href="/web/bills/delete/${bill.id}?employeeId=${employeeId}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="/web/employees">Back to Employees</a>
</body>
</html>
