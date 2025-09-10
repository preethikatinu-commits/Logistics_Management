<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Bill Form</title>
</head>
<body>
<h2>${bill.id == null ? "Add Bill" : "Edit Bill"}</h2>

<form action="${bill.id == null ? '/web/bills' : '/web/bills/update/' + bill.id}" method="post">
    <input type="hidden" name="employeeId" value="${employeeId}" />

    Amount: <input type="number" step="0.01" name="amount" value="${bill.amount}" required/><br/>
    Date: <input type="date" name="date" value="${bill.date}" required/><br/>
    Description: <input type="text" name="description" value="${bill.description}"/><br/>

    <button type="submit">Save</button>
</form>

<a href="/web/bills?employeeId=${employeeId}">Back to Bills</a>
</body>
</html>
