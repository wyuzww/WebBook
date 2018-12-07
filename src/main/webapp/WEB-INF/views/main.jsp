<%--
  Created by IntelliJ IDEA.
  User: Ethan
  Date: 2018/12/3
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书馆管理系统</title>
    <%
        // 权限验证
        if(session.getAttribute("currentUser")==null){
            response.sendRedirect("index.jsp");
            return;
        }
    %>
    <link rel="stylesheet" type="text/css" href="resources/jquery-easyui-1.6.10/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="resources/css/default.css">
    <link rel="stylesheet" type="text/css" href="resources/jquery-easyui-1.6.10/themes/icon.css">
    <script type="text/javascript" src="resources/jquery-easyui-1.6.10/jquery.min.js"></script>
    <script type="text/javascript" src="resources/jquery-easyui-1.6.10/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="resources/jquery-easyui-1.6.10/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="resources/js/default.js"></script>
<script type="text/javascript">

</script>
</head>
<body class="easyui-layout">
<div region="north" style="height: 85px;background-color: #E0EDFF">

    <div style=" float: right;padding-top: 60px;padding-right: 20px;">当前用户：&nbsp;<font color="red" >${currentUser.name }</font></div>
    <div align="left" style="height: 100%"><img src="resources/images/main1.jpg"></div>
</div>
<div data-options="region: 'center'" >
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="首页" >
        <div align="center" style="padding-top: 100px;"><font color="red" size="10">欢迎使用图书馆管理系统</font></div>
        </div>
    </div>
</div>

<div region="west" style="width: 150px;" title="图书馆管理中心" split="true">
    <div id="nav" class="easyui-accordion" fit="true" border="false">

    </div>
</div>
<%--<div region="south" style="height: 25px;" align="center">版权所有<a href="#"></a></div>--%>
</body>
</html>
