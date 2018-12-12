<%--
  Created by IntelliJ IDEA.
  User: Ethan
  Date: 2018/12/7
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 权限验证
    if (session.getAttribute("currentUser") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<link rel="stylesheet" type="text/css"
      href="${pageContext.request.contextPath}/resources/jquery-easyui-1.6.10/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/default.css">
<link rel="stylesheet" type="text/css"
      href="${pageContext.request.contextPath}/resources/jquery-easyui-1.6.10/themes/icon.css">
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/jquery-easyui-1.6.10/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/jquery-easyui-1.6.10/jquery.easyui.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/jquery-easyui-1.6.10/locale/easyui-lang-zh_CN.js"></script>
<html>
<script type="text/javascript">

</script>
<head>
    <title>个人信息</title>
</head>
<body>
<div id="p" class="easyui-panel" title="个人信息" style="width:1200px;padding:10px;fit:true">
    <table border="0" align="center" style="width:100%">
        <tr>
            <td align="right">帐号：</td>
            <td align="left"><label>3116004259</label></td>
            <td align="right">姓名：</td>
            <td align="left"><label>钟文武</label></td>
            <td align="right">性别：</td>
            <td align="left"><label>男</label></td>

        </tr>
        <tr>
            <td align="right">电话：</td>
            <td><label>13612250853</label></td>
            <td align="right"></td>
            <td><label></label></td>
            <td align="right">权限：</td>
            <td align="left"><label>超级管理员</label></td>
        </tr>
    </table>
</div>

</body>
</html>
