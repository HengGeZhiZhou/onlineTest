<%@ page import="HelloSpringMVC.entity.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>grades</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/structure.css">
</head>
<body>
<% Student student= (Student) request.getAttribute("student"); %>
<form action="" method="get" class="box login">
        <div style="text-align: center;" class="result">
            <br>
            <h1>学号：<%=student.getStudentId() %></h1><br><br><br>
            <h3>姓名：<%=student.getStudentName()%></h3><br><br><br>
            <h3>成绩：<%=student.getGrades()%></h3><br><br><br>
            <h3>上次测试时间：<%=student.getTestTime().toString().substring(0,11) %></h3><br><br><br>
            <a href="${pageContext.request.contextPath}/main.jsp">返回首页</a>
        </div>
</form>
</body>
</html>
