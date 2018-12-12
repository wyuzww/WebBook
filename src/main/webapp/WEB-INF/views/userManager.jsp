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
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/md5.js"></script>
    <%--<script type="text/javascript" src="resources/js/default.js"></script>--%>
    <script type="text/javascript">
        var user_level = "${currentUser.user_level}";

        var url;


        function deleteUser() {
            if (user_level == "超级管理员") {

            } else {
                $.messager.alert("系统提示", "您没有这个权限！");
                return;
            }


            var selectedRows = $("#dg").datagrid('getSelections');
            if (selectedRows.length == 0) {
                $.messager.alert("系统提示", "请选择要删除的数据！");
                return;
            }
            var strIds = [];
            for (var i = 0; i < selectedRows.length; i++) {
                strIds.push(selectedRows[i].user_id);
            }
            var ids = strIds.join(",");
            $.messager.confirm("系统提示", "您确认要删掉这<font color=red>" + selectedRows.length + "</font>条数据吗？", function (r) {
                if (r) {
                    $.post("user", {delIds: ids, flagText: "delete"}, function (result) {
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

        function searchUser() {
            $('#dg').datagrid('load', {
                user_account: $('#f_account').val(),
                user_name: $('#f_name').val(),
            });
        }


        function openAddUserDialog() {
            $("#dlg").dialog("open").dialog("setTitle", "添加帐号信息");
            resetValue();

            if (user_level == "超级管理员") {
                $("#user_account").validatebox().attr("readonly", false);
                $("#user_password").validatebox().attr("readonly", false);
                $("#user_level").combobox({
                    disabled: false,
                });
            } else if (user_level == "管理员") {
                $("#user_account").validatebox().attr("readonly", false);
                $("#user_password").validatebox().attr("readonly", false);
                $("#user_level").combobox({
                    disabled: true,
                });
            } else {
                $("#user_level").combobox({
                    disabled: true,
                });
            }
            url = "user?flagText=add";
        }

        function saveUser() {
            var write_password = $("#user_password").val();
            $("#fm").form("submit", {
                url: url,
                onSubmit: function () {
                    // var write_password = $("#user_password").val();
                    $("#user_password").val(md5($("#user_password").val()));
                    if ($('#user_sex').combobox("getValue") == "") {
                        $.messager.alert("系统提示", "请选择性别");
                        $("#user_password").val(write_password);
                        return false;
                    }
                    if ($('#user_level').combobox("getValue") == "") {
                        $.messager.alert("系统提示", "请选择权限");
                        $("#user_password").val(write_password);
                        return false;
                    }
                    if ($('#user_account').val() == "" || $('#user_password').val() == "" || $('#user_name').val() == "") {
                        $.messager.alert("系统提示", "请输入必填内容！");
                        $("#user_password").val(write_password);
                        return false;
                    }

                    return $(this).form("validate");
                },

                success: function (result) {
                    var json = $.parseJSON(result);
                    console.info(json)
                    if (json.success == "false") {
                        $.messager.alert("系统提示", json.msg);
                        $("#user_password").val(write_password);
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
            $("#user_account").val("");
            $("#user_password").val("");
            $("#user_name").val("");
            $("#user_sex").combobox("setValue", "");
            $("#user_phone").val("");
            $("#user_level").combobox("setValue", "");
        }

        function closeDialog() {
            $("#dlg").dialog("close");
            resetValue();
        }

        function openUpdateUserDialog() {
            var selectedRows = $("#dg").datagrid('getSelections');
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条要编辑的数据！");
                return;
            }
            var row = selectedRows[0];

            if (user_level == "超级管理员") {
                $("#user_account").validatebox().attr("readonly", false);
                $("#user_password").validatebox().attr("readonly", false);
                $("#user_level").combobox({
                    disabled: false
                });
            } else {
                $("#user_account").validatebox().attr("readonly", true);
                $("#user_password").validatebox().attr("readonly", true);
                $("#user_level").combobox({
                    disabled: true,
                });
            }

            $("#dlg").dialog("open").dialog("setTitle", "编辑帐号信息");
            $("#fm").form("load", row);

            url = "user?flagText=update&old_user=" + row.user_account + "&old_password=" + row.user_password + "&user_id=" + row.user_id;
        }
    </script>
</head>
<body>
<table id="dg" title="管理帐号信息" class="easyui-datagrid" fitColumns="true"
       pagination="true" rownumbers="true" url="user?flagText=allUser" fit="true" toolbar="#tb" .>
    <thead>
    <tr>
        <th field="cb" checkbox="true"></th>
        <th field="user_id" width="50" align="center">编号</th>
        <th field="user_account" width="100" align="center">帐号</th>
        <th field="user_password" width="100" align="center">密码</th>
        <th field="user_name" width="100" align="center">姓名</th>
        <th field="user_sex" width="100" align="center">性别</th>
        <th field="user_phone" width="100" align="center">电话</th>
        <th field="user_level" width="100" align="center">权限</th>
    </tr>
    </thead>
</table>

<div id="tb" align="center">
    <div>
        <a href="javascript:openAddUserDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:openUpdateUserDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:deleteUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <div>&nbsp;帐号：&nbsp;<input type="text" name="f_account" id="f_account" size="10"/>
        &nbsp;姓名：&nbsp;<input type="text" name="f_name" id="f_name" size="10"/>
        <a href="javascript:searchUser()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a></div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 570px;height: 350px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="5px;">
            <tr>
                <td>帐号：</td>
                <td><input type="text" name="user_account" id="user_account" class="easyui-validatebox" readonly="true"
                           required="true"/></td>
                <td>密码：</td>
                <td><input type="text" name="user_password" id="user_password" class="easyui-validatebox"
                           readonly="true"
                           required="true"/></td>
            </tr>
            <tr>
                <td>姓名：</td>
                <td><input type="text" name="user_name" id="user_name" class="easyui-validatebox" required="true"/></td>
                <td>性别：</td>
                <td>
                    <select class="easyui-combobox" id="user_sex" name="user_sex" editable="false" panelHeight="auto"
                            style="width: 155px">
                        <option value="">请选择...</option>
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>电话：</td>
                <td><input type="text" name="user_phone" id="user_phone" class="easyui-validatebox"/></td>
                <td>权限：</td>
                <td>
                    <select class="easyui-combobox" id="user_level" name="user_level" editable="false" disabled="true"
                            panelHeight="auto" required="true"
                            style="width: 155px">
                        <option value="读者">读者</option>
                        <option value="管理员">管理员</option>
                        <option value="超级管理员">超级管理员</option>
                    </select>
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:saveUser()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>
