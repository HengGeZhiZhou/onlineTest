<%@ page import="java.util.List" %>
<%@ page import="HelloSpringMVC.entity.Questions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test</title>
</head>
<body>

<% List<Questions> list = (List<Questions>) request.getAttribute("questions");
    String answers = (String) request.getAttribute("answers");
%>
<div style="text-align: center;"><h1> 欢迎进入网络测试系统 </h1></div>
<div>

    <form action="postResult" method="post" id="form1" onsubmit="return checkSubmit()">
        学号:<input type="text" id="stuNum" name="stuNum">&nbsp;&nbsp;&nbsp;&nbsp;
        姓名:<input type="text" name="stuName"><br><br>
        <input type="hidden" name="answers" value=<%=answers%>>
        <%
            for (int i = 0; i < list.size(); i++) {
        %>
        <%--<input type="hidden" value="<%=list.get(i).getQuestionId() %>">--%>
        <%=i + 1%>.<%=list.get(i).getQuestion()%><br>
        <input type="radio" name="question<%=i%>" value="A" id="<%=i%>A"/>
        <label for="<%=i%>A">A.<%=list.get(i).getChooseA()%>
        </label>
        <br/>
        <input type="radio" name="question<%=i%>" value="B" id="<%=i%>B"/>
        <label for="<%=i%>B">B.<%=list.get(i).getChooseB()%>
        </label>
        <br/>
        <input type="radio" name="question<%=i%>" value="C" id="<%=i%>C"/>
        <label for="<%=i%>C">C.<%=list.get(i).getChooseC()%>
        </label>
        <br/>
        <input type="radio" name="question<%=i%>" value="D" id="<%=i%>D"/>
        <label for="<%=i%>D">D.<%=list.get(i).getChooseD()%>
        </label>
        <br/>
        <br/>
        <br/>
        <%
            } %>
        <input type="submit" value="submit">
    </form>
</div>

</body>
<script language="javascript">
    function checkSubmit() {
        var radioname;
        var arrRadio = new Array();
        var inputs = document.getElementById("form1").getElementsByTagName("input");
        var stuNum=document.getElementById("stuNum").value;
        if (stuNum==null||stuNum==""){
            alert("请输入学号！！！");
            return false;
        }
        for (var i = 0; i < inputs.length; i++) {
            if (inputs[i].type == "radio") {
                //这里将所有的radio根据name分组,便于之后按组判断是否选中(一组选中一个即可)
                if (radioname != inputs[i].name) {
                    arrRadio.push(inputs[i].name);
                    radioname = inputs[i].name
                }
            }
        }

        for (var i = 0; i < arrRadio.length; i++) {
            var bRadio = false;
            for (var j = 0; j < document.getElementsByName(arrRadio[i]).length; j++) {
                if (document.getElementsByName(arrRadio[i]).item(j).checked == true) {
                    bRadio = true;
                    continue;
                }
            }
            if (!bRadio) {
                alert("请完成所有选项！");
                return false;
            }
        }

        var params = "";
        for (var i = 0; i < arrRadio.length; i++) {
            for (var j = 0; j < document.getElementsByName(arrRadio[i]).length; j++) {
                if (document.getElementsByName(arrRadio[i]).item(j).checked == true) {
                    if (params != "") {
                        params = params + ",";
                    }
                    //params += arrRadio[i] +"|"+document.getElementsByName(arrRadio[i]).item(j).value;
                    params += document.getElementsByName(arrRadio[i]).item(j).value;
                    continue;
                }
            }
        }
    }
</script>
</html>
