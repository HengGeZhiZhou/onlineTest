<%@ page import="HelloSpringMVC.entity.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>allStudents</title>
</head>
<body>
<style type="text/css">
    table.hovertable {
        font-family: verdana,arial,sans-serif;
        font-size:20px;
        color:#333333;
        width: 80%;
        margin-left:10%;
        border-width: 1px;
        border-color: #999999;
        border-collapse: collapse;
    }
    table.hovertable th {
        background-color:#c3dde0;
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #a9c6c9;
    }
    table.hovertable tr {
        background-color:#d4e3e5;
    }
    table.hovertable td {
        border-width: 1px;
        text-align: center;
        padding: 8px;
        border-style: solid;
        border-color: #a9c6c9;
    }
</style>

<% List<Student> students= (List<Student>) request.getAttribute("students"); %>

<div style="text-align: center;">
    <h1>学生考试成绩统计</h1>
    <table class="hovertable">
        <tr>
            <th>学号</th><th>姓名</th><th>修改信息</th><th>删除该学生档案</th>
        </tr>
        <% for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);%>
        <tr onmouseover="this.style.backgroundColor='#ffff66';"
            onmouseout="this.style.backgroundColor='#d4e3e5';">
            <td><%=student.getStudentId()%></td>
            <td><%=student.getStudentName()%></td>
            <td>
                <a href="${pageContext.request.contextPath}/updateInfo.jsp?id=<%=student.getStudentId()%>&name=<%=student.getStudentName()%>">修改</>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/onlineTest/students/deleteStudent?studentID=<%=student.getStudentId()%>">删除</a></td>
        </tr>
        <%
            }%>
    </table>
    <a  href="<%=request.getContextPath()%>/main.jsp">点击返回首页</a>
</div>


</body>
</html>
