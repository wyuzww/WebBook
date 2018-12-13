<%--
  Created by IntelliJ IDEA.
  User: Ethan
  Date: 2018/12/6
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <%
        // 权限验证
        if (session.getAttribute("currentUser") == null) {
            response.sendRedirect("index.jsp");
            return;
        }
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
    <%--<script type="text/javascript" src="resources/js/default.js"></script>--%>
    <script type="text/javascript">
        var url;

        function searchBorrow() {
            $('#dg').datagrid('load', {
                borrow_bcid: $('#f_borrow_bcid').val(),
            });
        }

    </script>
</head>
<body>
<table id="dg" title="借书记录信息" class="easyui-datagrid" fitColumns="true"
       pagination="true" url="borrow?flagText=allBorrow" fit="true" toolbar="#tb" .>
    <thead>
    <tr>
        <th field="cb" checkbox="true"></th>
        <th field="borrow_id" width="100" align="center">借书记录编号</th>
        <th field="borrow_bcid" width="100" align="center">借阅证编号</th>
        <th field="user_name" width="100" align="center">读者姓名</th>
        <th field="borrow_ISBN" width="100" align="center">图书ISBN</th>
        <th field="book_name" width="100" align="center">图书名字</th>
        <th field="borrow_borrowDate" width="100" align="center">借书日期</th>
        <th field="borrow_expireDate" width="100" align="center">应还日期</th>
        <th field="borrow_dueDate" width="100" align="center">还书日期</th>
    </tr>
    </thead>
</table>

<div id="tb" align="center">
    <div>
        &nbsp;借阅证编号：&nbsp;<input type="text" name="f_borrow_bcid" id="f_borrow_bcid" size="20"/>
        <a href="javascript:searchBorrow()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a></div>
</div>

</div>
</body>
</html>
