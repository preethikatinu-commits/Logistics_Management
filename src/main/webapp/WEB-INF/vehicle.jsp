<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Vehicles</title>
</head>
<body>
<h2>Vehicle List</h2>
<table border="1">
    <tr><th>ID</th><th>Number Plate</th><th>Type</th><th>Status</th></tr>
    <c:forEach var="v" items="${vehicleList}">
        <tr>
            <td>${v.vehicleId}</td>
            <td>${v.vehicleNumber}</td>
            <td>${v.type}</td>
            <td>${v.status}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
