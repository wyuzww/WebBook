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


        function deleteBookCatagory() {
            var selectedRows = $("#dg").datagrid('getSelections');
            if (selectedRows.length == 0) {
                $.messager.alert("系统提示", "请选择要删除的数据！");
                return;
            }
            if (true) {
                $.messager.alert("系统提示", "存在书本数据，不能删除！");
                return;
            }
            var strIds = [];
            for (var i = 0; i < selectedRows.length; i++) {
                strIds.push(selectedRows[i].bookcatagory_id);
            }
            var ids = strIds.join(",");
            $.messager.confirm("系统提示", "您确认要删掉这<font color=red>" + selectedRows.length + "</font>条数据吗？", function (r) {
                if (r) {
                    $.post("bookCatagory", {delIds: ids, flagText: "delete"}, function (result) {
                        // var json = $.parseJSON(result);
                        if (result.success == "true") {
                            $.messager.alert("系统提示", "您已成功删除<font color=red>" + result.delNums + "</font>条数据！");
                            $("#dg").datagrid("reload");
                        } else {
                            $.messager.alert('系统提示', result.msg);
                        }
                    }, "json");
                }
            });
        }

        function searchBookCatagory() {
            $('#dg').datagrid('load', {
                bookcatagory_name: $('#f_bookcatagory_name').val(),
            });
        }

        function openAddBookCatagoryDialog() {
            $('#bookcatagory_brid').combobox({
                url: 'bookroom?flagText=allBookRoomList',
                valueField: 'bookroom_id',
                textField: 'bookroom_name'
            });
            $("#dlg").dialog("open").dialog("setTitle", "添加书本类别");
            resetValue();
            url = "bookcatagory?flagText=add";
        }

        function saveBookCatagory() {
            $("#fm").form("submit", {
                url: url,
                onSubmit: function () {
                    if ($('#bookcatagory_brid').combobox("getValue") == -1) {
                        $.messager.alert("系统提示", "请选择书库");
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
                        $.messager.alert("系统提示", json.msg);
                        resetValue();
                        $("#dlg").dialog("close");
                        $("#dg").datagrid("reload");
                    }
                }
            });
        }

        function resetValue() {
            $("#bookcatagory_name").val("");
            $("#bookcatagory_brid").combobox("setValue", "");
            $("#bookcatagory_demo").val("");
        }

        function closeDialog() {
            $("#dlg").dialog("close");
            resetValue();
        }

        function openUpdateBookCatagoryDialog() {
            $('#bookcatagory_brid').combobox({
                url: 'bookroom?flagText=allBookRoomList',
                valueField: 'bookroom_id',
                textField: 'bookroom_name'
            });
            var selectedRows = $("#dg").datagrid('getSelections');
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条要编辑的数据！");
                return;
            }
            var row = selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle", "编辑类别信息");
            $("#fm").form("load", row);

            url = "bookcatagory?flagText=update&bookcatagory_id=" + row.bookcatagory_id;
        }
    </script>
</head>
<body>
<table id="dg" title="管理图书类别" class="easyui-datagrid" fitColumns="true"
       pagination="true" url="bookcatagory?flagText=allBookCatagory" fit="true" toolbar="#tb" .>
    <thead>
    <tr>
        <th field="cb" checkbox="true"></th>
        <th field="bookcatagory_id" width="50" align="center">编号</th>
        <th field="bookcatagory_name" width="100" align="center">类别名称</th>
        <th field="bookcatagory_brid" width="50" align="center" hidden="true">书库编号</th>
        <th field="bookroom_name" width="100" align="center">所属书库</th>
        <th field="bookcatagory_demo" width="200" align="center">类别说明</th>
    </tr>
    </thead>
</table>

<div id="tb" align="center">
    <div>
        <a href="javascript:openAddBookCatagoryDialog()" class="easyui-linkbutton" iconCls="icon-add"
           plain="true">添加</a>
        <a href="javascript:openUpdateBookCatagoryDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:deleteBookCatagory()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <div>
        &nbsp;类别名称：&nbsp;<input type="text" name="f_bookcatagory_name" id="f_bookcatagory_name" size="10"/>
        <a href="javascript:searchBookCatagory()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 400px;height: 350px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="10px;">
            <tr>
                <td>类别名称：</td>
                <td><input type="text" name="bookcatagory_name" id="bookcatagory_name" class="easyui-validatebox"
                           required="true" width="300px"/></td>
            </tr>
            <tr>
                <td>所属书库：</td>
                <td><input class="easyui-combobox" required="true" name="bookcatagory_brid" id="bookcatagory_brid"
                           size="20"
                           data-options="panelHeight:'auto',editable:false,valueField:'bookroom_id',textField:'bookroom_name',url:'bookroom?flagText=allBookRoomList'"/>
                </td>
            </tr>
            <tr>
                <td valign="top">类别说明：</td>
                <td><textarea class="easyui-validatebox" name="bookcatagory_demo" id="bookcatagory_demo"
                              style="width: 173px;height:100px;resize: none"></textarea></td>
            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:saveBookCatagory()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>
