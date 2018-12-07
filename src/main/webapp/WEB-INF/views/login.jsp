<%--
  Created by IntelliJ IDEA.
  User: Ethan
  Date: 2018/12/2
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书馆管理系统</title>
    <script type="text/javascript">
        function resetValue(){
            document.getElementById("account").value="";
            document.getElementById("password").value="";
        }
    </script>
</head>
<body background="resources/images/login2.jpg" style="background-size: 100%;background-repeat: no-repeat">
<div align="center" style="padding-top: 50px;" >
    <form action="user" method="post" >
        <input type="hidden" name="flagText" value="login">
        <table  width="698" height="509" border="0">
            <tr height="300">
                <td colspan="4"></td>
            </tr>
            <tr height="50">
                <td width="15%"></td>
                <td width="7%">帐 号:</td>
                <td><input style="height: 60%" type="text" value="${account }" name="account" id="account"/></td>
                <td width="30%"></td>
            </tr>
            <tr height="50">
                <td width="15%"></td>
                <td  width="7%">密 码:</td>
                <td><input style="height: 60%" type="password" value="${password }" name="password" id="password"/></td>
                <td width="30%"></td>
            </tr>
            <tr height="50">
                <td width="15%"></td>
                <td width="7%"style="text-align: right"></td>
                <td><input style="height: 60%;width: 50px;" type="submit" value="登录"/>
                    <input style="height: 60%;width: 50px;margin-left: 35px"  type="button" value="重置" onclick="resetValue()"/></td>
                <td width="30%"></td>
            </tr>
            <tr height="20">
                <td width="7%"></td>
                <td colspan="3">
                    <font color="red">${error }</font>
                </td>
            </tr>
            <tr >
                <td></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
