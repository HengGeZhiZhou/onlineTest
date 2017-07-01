
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>result</title>
</head>
<body>
    <h1><%=request.getAttribute("resultInfo")%></h1>
    <a href="<%=request.getContextPath()%>/main.jsp">返回首页</a>
</body>
</html>
