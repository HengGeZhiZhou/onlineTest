<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/17
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>picture</title>
</head>
<body>
<form action="<%=request.getContextPath()%>onlineTest/questions/uploadQuestions" method="post" enctype="multipart/form-data">
    选择文件:<input type="file" name="file">
    <input type="submit" value="上传">
</form>
<h3>-----------------------</h3>
<form action="<%=request.getContextPath()%>/swagger/Test/photosUpload" method="post" enctype="multipart/form-data">
    选择文件:<input type="file" name="file"><br>
    选择文件:<input type="file" name="file"><br>
    选择文件:<input type="file" name="file"><br>
    <input type="submit" value="上传">
</form>
</body>
</html>
