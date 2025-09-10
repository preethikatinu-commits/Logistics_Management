<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Register Branch</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; }
        label { display:block; margin-top:10px; font-weight:bold; }
        input, select { width: 300px; padding:6px; margin-top:4px; }
        .btn { margin-top:15px; padding:8px 14px; background:#2b6cff; color:#fff; border:none; border-radius:4px; cursor:pointer; }
        .global-error { color: #b00020; margin-bottom:10px; font-weight:bold; }
        .field-row { margin-bottom: 8px; }
        .form-error { color:#b00020; font-size:0.9em; margin-top:4px; }
    </style>
</head>
<body>
    <h2>Register New Branch</h2>

    <c:if test="${not empty error}">
        <div class="global-error">${error}</div>
    </c:if>

    <form:form method="post" modelAttribute="branch" action="${pageContext.request.contextPath}/branches/register">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

        <div class="field-row">
            <label for="name">Branch Name</label>
            <form:input path="name" id="name"/>
            <form:errors path="name" cssClass="form-error"/>
        </div>
        <div class="field-row">
            <label for="address">Address</label>
            <form:input path="address" id="address"/>
            <form:errors path="address" cssClass="form-error"/>
        </div>
        <div class="field-row">
            <label for="city">City</label>
            <form:input path="city" id="city"/>
            <form:errors path="city" cssClass="form-error"/>
        </div>
        <div class="field-row">
            <label for="state">State</label>
            <form:input path="state" id="state"/>
            <form:errors path="state" cssClass="form-error"/>
        </div>
        <div class="field-row">
            <label for="pinCode">Pin Code</label>
            <form:input path="pinCode" id="pinCode"/>
            <form:errors path="pinCode" cssClass="form-error"/>
        </div>
        <div class="field-row">
            <label for="type">Branch Type</label>
            <form:select path="type" id="type">
                <form:options items="${types}" />
            </form:select>
            <form:errors path="type" cssClass="form-error"/>
        </div>
        <div class="field-row">
            <label for="clientId">Client</label>
            <form:select path="clientId" id="clientId">
                <form:options items="${clients}" itemValue="id" itemLabel="name"/>
            </form:select>
            <form:errors path="clientId" cssClass="form-error"/>
        </div>

        <div style="margin-top:16px;">
            <button type="submit" class="btn">Save Branch</button>
            <a href="${pageContext.request.contextPath}/branches" style="margin-left:15px;">Back</a>
        </div>
    </form:form>
</body>
</html>