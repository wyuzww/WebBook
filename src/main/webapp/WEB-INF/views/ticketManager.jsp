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

        function deleteTicket() {
            var selectedRows = $("#dg").datagrid('getSelections');
            if (selectedRows.length == 0) {
                $.messager.alert("系统提示", "请选择要缴费的记录！");
                return;
            }

            var strIds = [];
            for (var i = 0; i < selectedRows.length; i++) {
                strIds.push(selectedRows[i].ticket_id);
            }
            var ids = strIds.join(",");
            $.messager.confirm("系统提示", "是否确认缴费？", function (r) {
                if (r) {
                    $.post("ticket", {delIds: ids, flagText: "delete"}, function (result) {
                        // var json = $.parseJSON(result);
                        if (result.success == "true") {
                            $.messager.alert("系统提示", "成功缴费<font color=red>" + result.delNums + "</font>条记录！");
                            $("#dg").datagrid("reload");
                        } else {
                            $.messager.alert('系统提示', result.msg);
                        }
                    }, "json");
                }
            });
        }

        function searchTicket() {
            $('#dg').datagrid('load', {
                ticket_bcid: $('#f_ticket_bcid').val(),
            });
        }

    </script>
</head>
<body>
<table id="dg" title="管理罚款信息" class="easyui-datagrid" fitColumns="true"
       pagination="true" url="ticket?flagText=allTicket" fit="true" toolbar="#tb" .>
    <thead>
    <tr>
        <th field="cb" checkbox="true"></th>
        <th field="ticket_id" width="50" align="center">罚款单编号</th>
        <th field="ticket_bcid" width="100" align="center">借阅证编号</th>
        <th field="ticket_ISBN" width="100" align="center">图书ISBN</th>
        <th field="ticket_fineMoney" width="50" align="center">应缴罚款(元)</th>
        <th field="ticket_fineDate" width="100" align="center">罚款日期</th>
    </tr>
    </thead>
</table>

<div id="tb" align="center">
    <div>
        <a href="javascript:deleteTicket()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">缴费（delete）</a>
    </div>
    <div>
        &nbsp;借阅证编号：&nbsp;<input type="text" name="f_ticket_bcid" id="f_ticket_bcid" size="20"/>
        <a href="javascript:searchTicket()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a></div>
</div>

</div>
</body>
</html>
