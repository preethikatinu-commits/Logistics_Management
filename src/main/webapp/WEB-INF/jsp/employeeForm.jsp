<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employee Form</title>
</head>
<body>
<h2>${employee.id == null ? 'Add New Employee' : 'Edit Employee'}</h2>
<form action="${employee.id == null 
                ? pageContext.request.contextPath.concat('/web/employees') 
                : pageContext.request.contextPath.concat('/web/employees/update/').concat(employee.id)}" 
      method="post">

    <input type="hidden" name="id" value="${employee.id}"/>

    Name: <input type="text" name="name" value="${employee.name}" required/><br/>
    Department: <input type="text" name="department" value="${employee.department}"/><br/>
    Role: <input type="text" name="role" value="${employee.role}"/><br/>
    Salary: <input type="number" step="0.01" name="salary" value="${employee.salary}"/><br/>

    <button type="submit">Save</button>
</form>
<a href="${pageContext.request.contextPath}/web/employees">Back to List</a>
</body>
</html>
