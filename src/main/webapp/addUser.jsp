<%@ page import="HelloSpringMVC.entity.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addStudents</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/structure.css">
</head>
<body>
<form action="<%=request.getContextPath()%>/onlineTest/students/addStudent" method="post" class="box login">
    <fieldset class="boxBody">
        <label>请输入学号</label>
        <input type="text" tabindex="1" required name="studentID">
        <label>
            请输入姓名</label>
        <input type="text" tabindex="2" required name="studentName">
    </fieldset>
    <footer>
        <input type="submit" class="btnLogin" value="添加学生" tabindex="4">
    </footer>
</form>
</body>
</html>
