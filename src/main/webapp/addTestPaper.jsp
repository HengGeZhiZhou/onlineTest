<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/structure.css">
</head>
<body>
<form action="<%=request.getContextPath()%>/onlineTest/questions/addQuestion" method="post" class="login">
    <div style="text-align: center; margin-top: 200px">
        请输入题目：<input type="text" name="question">
        请输入选项A：<input type="text" name="chooseA">
        请输入选项B：<input type="text" name="chooseB">
        请输入选项C：<input type="text" name="chooseC">
        请输入选项D：<input type="text" name="chooseD">
        请输入答案：<input type="text" name="answer">
        <input type="submit" value="提交">
    </div>

</form>
</body>
</html>
