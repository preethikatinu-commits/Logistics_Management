<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Error</title></head>
<body>
  <h2>Something went wrong</h2>
  <p>Status: ${status}</p>
  <p>Error: ${error}</p>
  <p>Message: ${message}</p>
  <p>Path: ${path}</p>
  <p><a href="${pageContext.request.contextPath}/">Home</a> | <a href="${pageContext.request.contextPath}/login">Login</a></p>
</body>
</html>