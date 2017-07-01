<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>首页</title>

</head>
<body>
<style>
    /* 下拉按钮样式 */
    .bg{
        background:#eff3f6;
        border: 1px solid #C3D4DB;
        width: 100%;
        height:100%;
        padding:0;
        margin:0px 0px 0px 0px ;
        position:relative;
        /*margin-top: 100px;*/
    }
    .head{
        font-family: "微软雅黑";
    }

    .dropbtn {
        background-color: #4CAF50;
        color: white;
        padding: 16px;
        font-size: 16px;
        border: none;
        cursor: pointer;
    }

    /* 容器 <div> - 需要定位下拉内容 */
    .dropdown {
        position: relative;
        top: 100px;
        display: inline-block;
        margin-left:3%;
    }

    /* 下拉内容 (默认隐藏) */
    .dropdown-content {
        display: none;
        position: absolute;
        background-color: #f9f9f9;
        min-width: 160px;
        box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
    }

    /* 下拉菜单的链接 */
    .dropdown-content a {
        color: black;
        padding: 12px 16px;
        text-decoration: none;
        display: block;
    }

    /* 鼠标移上去后修改下拉菜单链接颜色 */
    .dropdown-content a:hover {background-color: #f1f1f1}

    /* 在鼠标移上去后显示下拉菜单 */
    .dropdown:hover .dropdown-content {
        display: block;
    }

    /* 当下拉内容显示后修改下拉按钮的背景颜色 */
    .dropdown:hover .dropbtn {
        background-color: #3e8e41;
    }
</style>


<div class="bg" style="text-align: center;">
    <h1 class="head">学生在线测试系统</h1>
    <div  class="dropdown">
        <button class="dropbtn">用户管理</button>
        <div class="dropdown-content">
            <a href="<%=request.getContextPath()%>/addUser.jsp">学生添加</a>
            <a href="<%=request.getContextPath()%>/onlineTest/students/getAllStudents">查看所有学生情况</a>
            <a href="<%=request.getContextPath()%>/importStudents.jsp">学生批量导入</a>
        </div>
    </div>
    <div class="dropdown">
        <button class="dropbtn">试题管理</button>
        <div class="dropdown-content">
            <a href="<%=request.getContextPath()%>/addTestPaper.jsp">试题添加</a>
            <a href="<%=request.getContextPath()%>/onlineTest/questions/getAllQuestions">查看所有试题</a>
            <a href="<%=request.getContextPath()%>/importTestPapers.jsp">试题批量导入</a>
        </div>
    </div>
    <div class="dropdown">
        <button class="dropbtn">试卷管理</button>
        <div class="dropdown-content">
            <a href="<%=request.getContextPath()%>/onlineTest/students/getPic">查看考试情况统计柱状图</a>
            <a href="<%=request.getContextPath()%>/onlineTest/students/getAllStudentsGrades">查看学生排名</a>
        </div>
    </div>
    <div class="dropdown">
        <button class="dropbtn">在线测试</button>
        <div class="dropdown-content">
            <a href="<%=request.getContextPath()%>/onlineTest/questions/startTest">进行测试</a>
            <a href="<%=request.getContextPath()%>/queryGrade.jsp">成绩查询</a>
            <a href="<%=request.getContextPath()%>/onlineTest/students/getExcellent">查看高分成绩</a>
        </div>
    </div></div>
</body>
</html>
