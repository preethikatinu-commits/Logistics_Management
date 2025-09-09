<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>Login</title></head>
<body>
<h2>Login</h2>

<form method="post" action="${pageContext.request.contextPath}/perform_login">
    <label>Username: <input type="text" name="username" /></label><br/>
    <label>Password: <input type="password" name="password" /></label><br/>
    <button type="submit">Login</button>
</form>

<c:if test="${param.error != null}">
    <p style="color:red">Invalid username or password</p>
</c:if>
<c:if test="${param.logout != null}">
    <p style="color:green">You are logged out</p>
</c:if>
</body>
</html>