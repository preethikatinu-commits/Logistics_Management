<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Bill Summary</title>
    <style>
        table {
            width: 50%;
            border-collapse: collapse;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid #444;
            padding: 8px 12px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        h2 {
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>

<h2>Employee Bill Summary for Today</h2>

<table>
    <thead>
        <tr>
            <th>Employee ID</th>
            <th>Number of Bills</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="summary" items="${summaryList}">
            <tr>
                <td>${summary.employeeId}</td>
                <td>${summary.totalBills}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
