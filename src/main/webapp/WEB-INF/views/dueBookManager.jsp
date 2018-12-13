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

        function dueBook() {
            $("#fm").form("submit", {
                url: url,
                onSubmit: function () {
                    if ($('#borrow_dueDate').datebox("getValue") == "") {
                        $.messager.alert("系统提示", "请选择还书日期");
                        return false;
                    }

                    if ($('#borrow_borrowDate').datebox("getValue") > $('#borrow_dueDate').datebox("getValue")) {
                        $.messager.alert("系统提示", "还书日期不能早于借阅日期");
                        return false;
                    }

                    if ($('#borrow_dueDate').datebox("getValue").toString() > (new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + (new Date().getDate()))) {
                        $.messager.alert("系统提示", "不能使用未来日期");
                        return false;
                    }

                    return $(this).form("validate");
                },

                success: function (result) {
                    var json = $.parseJSON(result);
                    console.info(json)
                    if (json.success == "false") {
                        $.messager.alert("系统提示", json.msg);
                        return;
                    } else {
                        $("#dlg").dialog("close");
                        if ($('#borrow_expireDate').datebox("getValue") < $('#borrow_dueDate').datebox("getValue")) {
                            $.messager.alert("系统提示", "该书借阅时间超时，详情看罚款单！");
                            // return false;
                        }
                        $.messager.alert("系统提示", json.msg);


                        resetValue();
                        $("#dg").datagrid("reload");
                    }
                }
            });
        }

        function resetValue() {
            $("#borrow_bcid").val("");
            $("#book_name").val("");
            $("#borrow_ISBN").val("");
            $("#user_name").val("");
            $("#borrow_borrowDate").datebox("setValue", "");
            $("#borrow_expireDate").datebox("setValue", "");

            $("#borrow_dueDate").datebox("setValue", "");
        }

        function closeDialog() {
            $("#dlg").dialog("close");
            resetValue();
        }

        function openDueBookDialog() {
            resetValue();
            var selectedRows = $("#dg").datagrid('getSelections');
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条数据！");
                return;
            }
            var row = selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle", "还书操作");
            $("#fm").form("load", row);

            url = "borrow?flagText=dueBook&borrow_id=" + row.borrow_id;
        }

        function searchBorrow() {
            $('#dg').datagrid('load', {
                borrow_bcid: $('#f_borrow_bcid').val(),
            });
        }

    </script>
</head>
<body>
<table id="dg" title="还书操作" class="easyui-datagrid" fitColumns="true"
       pagination="true" url="borrow?flagText=notDueBorrow" fit="true" toolbar="#tb" .>
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
    </tr>
    </thead>
</table>

<div id="tb" align="center">
    <div>
        <a href="javascript:openDueBookDialog()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">还书</a>
    </div>
    <div>
        &nbsp;借阅证编号：&nbsp;<input type="text" name="f_borrow_bcid" id="f_borrow_bcid" size="20"/>
        <a href="javascript:searchBorrow()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a></div>
</div>


<div id="dlg" class="easyui-dialog" style="width: 550px;height: 450px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="10px;">
            <tr>
                <td>借书证编号：</td>
                <td>
                    <input type="text" class="easyui-validatebox" required="true" id="borrow_bcid" name="borrow_bcid"
                           readonly="true"/>
                </td>
                <td>图书ISBN：</td>
                <td>
                    <input type="text" name="borrow_ISBN" id="borrow_ISBN" class="easyui-validatebox" required="true"
                           readonly="true"/>
                </td>
            </tr>
            <tr>
                <td>读者名：</td>
                <td><input type="text" name="user_name" id="user_name" class="easyui-validatebox" required="true"
                           readonly="true"/></td>
                <td>书名：</td>
                <td><input type="text" name="book_name" id="book_name" class="easyui-validatebox" required="true"
                           readonly="true"/></td>
            </tr>
            <tr>
                <td>借书日期：</td>
                <td>
                    <input class="easyui-datebox" id="borrow_borrowDate" name="borrow_borrowDate" required="true"
                           editable="false" readonly="true"/>
                </td>
                <td>应还日期：</td>
                <td>
                    <input class="easyui-datebox" id="borrow_expireDate" name="borrow_expireDate" required="true"
                           editable="false" readonly="true"/>
                </td>
            </tr>
            <tr style="height: 15px">
                <td>

                </td>
            </tr>
        </table>
        <div align="center">
            <table>
                <tr>
                    <td colspan="2" align="right">还书日期：</td>
                    <td colspan="2" align="left">
                        <input class="easyui-datebox" id="borrow_dueDate" name="borrow_dueDate" required="true"
                               editable="false"/>
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:dueBook()" class="easyui-linkbutton" iconCls="icon-ok">还书</a>
    <a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

</body>
</html>
