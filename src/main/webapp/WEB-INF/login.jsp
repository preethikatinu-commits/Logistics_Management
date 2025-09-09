<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Login</title>
    <style>
        body{font-family:Arial, sans-serif;padding:20px}
        .box{max-width:420px;margin:0 auto}
        label{display:block;margin:8px 0 4px}
        input{width:100%;padding:8px;border:1px solid #ccc;border-radius:4px}
        .btn{margin-top:12px;padding:8px 14px;border-radius:4px;background:#2b6cff;color:#fff;border:none;cursor:pointer}
        .msg{padding:8px;border-radius:4px;margin-top:12px}
        .msg-error{background:#fff0f0;border:1px solid #f0c5c5;color:#900}
        .msg-success{background:#e6ffed;border:1px solid #c5f0d0;color:#070}
    </style>
</head>
<body>
<div class="box">
    <h2>Login</h2>

    <form method="post" action="${pageContext.request.contextPath}/perform_login">
        <!-- CSRF token -->
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

        <label for="username">Username</label>
        <input id="username" type="text" name="username" value="${param.username != null ? param.username : ''}" />

        <label for="password">Password</label>
        <input id="password" type="password" name="password" />

        <button type="submit" class="btn">Login</button>
    </form>

    <c:if test="${param.error != null}">
        <div class="msg msg-error">Invalid username or password</div>
    </c:if>
    <c:if test="${param.logout != null}">
        <div class="msg msg-success">You have been logged out</div>
    </c:if>
</div>
</body>
</html>