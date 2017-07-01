<%@ page import="HelloSpringMVC.entity.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="HelloSpringMVC.entity.Questions" %>
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

<% List<Questions> questions= (List<Questions>) request.getAttribute("questions"); %>

<div style="text-align: center;">
    <h1>所有试题</h1>
    <table class="hovertable">
        <tr>
            <th>题号</th><th>题目</th><th>选项</th><th>选项B</th><th>选项C</th><th>选项D</th><th>答案</th>
            <<th>删除题目</th>
        </tr>
        <% for (int i = 0; i < questions.size(); i++) {
            Questions questions1 = questions.get(i);%>
        <tr onmouseover="this.style.backgroundColor='#ffff66';"
            onmouseout="this.style.backgroundColor='#d4e3e5';">
            <td><%=questions1.getQuestionId()%></td>
            <td><%=questions1.getQuestion()%></td>
            <td><%=questions1.getChooseA()%></td>
            <td><%=questions1.getChooseB()%></td>
            <td><%=questions1.getChooseC()%></td>
            <td><%=questions1.getChooseD()%></td>
            <td><%=questions1.getAnswer()%></td>
            <td>
                <a href="${pageContext.request.contextPath}/onlineTest/questions/deleteQuestion?questionID=<%=questions1.getQuestionId()%>">删除</a>
            </td>
        </tr>
        <%
            }%>
    </table>
    <a  href="<%=request.getContextPath()%>/main.jsp">点击返回首页</a>
</div>


</body>
</html>
