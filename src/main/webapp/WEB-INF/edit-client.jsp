<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <title>Edit Client</title>
  <style>
    body{font-family:Arial;padding:16px}
    .form-row{margin-bottom:10px}
    label{display:block;font-weight:600;margin-bottom:4px}
    input[type="text"], input[type="email"]{width:320px;padding:6px;border:1px solid #ccc;border-radius:4px}
    .btn{padding:8px 14px;border-radius:4px;background:#2b6cff;color:#fff;border:none;cursor:pointer}
    .msg{padding:8px;margin-bottom:10px;border-radius:4px}
    .success{background:#e6ffed;border:1px solid #c5f0d0}
    .msg-error{background:#fff0f0;border:1px solid #f0c5c5}
    .form-error { color:#b00020; font-size:0.9em; margin-top:4px; } /* renamed */
  </style>
</head>
<body>
  <h2>Edit Client</h2>

  <c:if test="${not empty message}">
    <div class="msg success">${message}</div>
  </c:if>
  <c:if test="${not empty error}">
    <div class="msg msg-error">${error}</div>
  </c:if>

  <form:form method="post" modelAttribute="client" action="${pageContext.request.contextPath}/clients/edit/${client.id}">
    <div class="form-row">
      <label for="name">Name</label>
      <form:input path="name" id="name" />
      <form:errors path="name" cssClass="form-error"/>
    </div>

    <div class="form-row">
      <label for="phone">Phone</label>
      <form:input path="phone" id="phone" />
      <form:errors path="phone" cssClass="form-error"/>
    </div>

    <div class="form-row">
      <label for="email">Email</label>
      <form:input path="email" id="email" />
      <form:errors path="email" cssClass="form-error"/>
    </div>

    <div class="form-row">
      <button type="submit" class="btn">Save</button>
      <a href="${pageContext.request.contextPath}/clients" style="margin-left:12px">Back to list</a>
    </div>
  </form:form>
</body>
</html>
