<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>query</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/structure.css">
</head>
<body>
<form action="<%=request.getContextPath()%>/onlineTest/students/getGrades" method="get" class="box login">
    <fieldset class="boxBody">
        <label>请输入学号</label>
        <input type="text" tabindex="1" required name="stuNum">
        <label><a href="#" class="rLink" tabindex="5">忘记学号？</a>
            请输入姓名</label>
        <input type="text" tabindex="2" required>
    </fieldset>
    <footer>
        <input type="submit" class="btnLogin" value="成绩查询" tabindex="4">
    </footer>
</form>
</body>
</html>
