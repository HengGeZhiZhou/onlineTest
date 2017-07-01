<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/structure.css">
</head>
<body>
<form action="<%=request.getContextPath()%>onlineTest/questions/uploadQuestions" enctype="multipart/form-data" method="post" class="box login">
    <img style="height:160px;width:332px; " src="${pageContext.request.contextPath}/img/pic.jpg">
    <p style="color: red">注：请导入Excel表格(.xlsx或.xls格式)</p>
    选择文件:<input type="file" name="file">
    <footer>
        <input type="submit" class="btnLogin" value="批量导入试题" tabindex="4">
    </footer>
</form>
</body>
</html>
