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

        // function deleteBookCatagory() {
        //     var selectedRows = $("#dg").datagrid('getSelections');
        //     if (selectedRows.length == 0) {
        //         $.messager.alert("系统提示", "请选择要删除的数据！");
        //         return;
        //     }
        //     if (true) {
        //         $.messager.alert("系统提示", "存在书本数据，不能删除！");
        //         return;
        //     }
        //     var strIds = [];
        //     for (var i = 0; i < selectedRows.length; i++) {
        //         strIds.push(selectedRows[i].bookcatagory_id);
        //     }
        //     var ids = strIds.join(",");
        //     $.messager.confirm("系统提示", "您确认要删掉这<font color=red>" + selectedRows.length + "</font>条数据吗？", function (r) {
        //         if (r) {
        //             $.post("bookCatagory", {delIds: ids, flagText: "delete"}, function (result) {
        //                 // var json = $.parseJSON(result);
        //                 if (result.success == "true") {
        //                     $.messager.alert("系统提示", "您已成功删除<font color=red>" + result.delNums + "</font>条数据！");
        //                     $("#dg").datagrid("reload");
        //                 } else {
        //                     $.messager.alert('系统提示', result.msg);
        //                 }
        //             }, "json");
        //         }
        //     });
        // }

        function searchBorrowCard() {
            $('#dg').datagrid('load', {
                borrowcard_id: $('#f_borrowcard_id').val(),
                borrowcard_blid: $('#f_borrowcard_blid').combobox("getValue")
            });
        }

        // function openAddBookCatagoryDialog() {
        //     $("#dlg").dialog("open").dialog("setTitle", "添加书本类别");
        //     resetValue();
        //     url = "bookcatagory?flagText=add";
        // }

        function saveBorrowCard() {
            $("#fm").form("submit", {
                url: url,
                onSubmit: function () {
                    if ($('#borrowcard_blid').combobox("getValue") == -1) {
                        $.messager.alert("系统提示", "请选择等级");
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
            $("#borrowcard_blid").combobox("setValue", "");
        }

        function closeDialog() {
            $("#dlg").dialog("close");
            resetValue();
        }

        function openUpdateBorrowCardDialog() {
            var selectedRows = $("#dg").datagrid('getSelections');
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条要编辑的数据！");
                return;
            }
            var row = selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle", "编辑等级信息");
            $("#fm").form("load", row);

            url = "borrowcard?flagText=update&borrowcard_id=" + row.borrowcard_id;
        }
    </script>
</head>
<body>
<table id="dg" title="管理借阅证" class="easyui-datagrid" fitColumns="true"
       pagination="true" url="borrowcard?flagText=allBorrowCard" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true"></th>
        <th field="borrowcard_id" width="100" align="center">编号</th>
        <th field="borrowcard_rid" width="50" align="center" hidden="true">用户编号</th>
        <th field="user_account" width="100" align="center">用户帐号</th>
        <th field="user_name" width="100" align="center">用户姓名</th>
        <th field="borrowcard_quantity" width="50" align="center">已借书数量</th>
        <th field="borrowcard_blid" width="50" align="center" hidden="true">等级编号</th>
        <th field="borrowlevel_name" width="50" align="center">借阅等级</th>
        <th field="borrowlevel_demo" width="200" align="center">等级说明</th>
    </tr>
    </thead>
</table>

<div id="tb" align="center">
    <div>
        <%--<a href="javascript:openAddBookCatagoryDialog()" class="easyui-linkbutton" iconCls="icon-add"--%>
        <%--plain="true">添加</a>--%>
        <a href="javascript:openUpdateBorrowCardDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改等级</a>
        <%--<a href="javascript:deleteBookCatagory()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>--%>
    </div>
    <div>
        &nbsp;借阅证编号：&nbsp;<input type="text" name="f_borrowcard_id" id="f_borrowcard_id" size="20"/>
        &nbsp;借阅等级：&nbsp;<input class="easyui-combobox" name="f_borrowcard_blid" id="f_borrowcard_blid" size="10"
                                data-options="panelHeight:'auto',editable:false,valueField:'borrowlevel_id',textField:'borrowlevel_name',url:'borrowlevel?flagText=allBorrowLevelList'"/>
        <a href="javascript:searchBorrowCard()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 400px;height: 250px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="10px;">
            <tr>
                <td>选择等级：</td>
                <td><input class="easyui-combobox" required="true" name="borrowcard_blid" id="borrowcard_blid"
                           size="20"
                           data-options="panelHeight:'auto',editable:false,valueField:'borrowlevel_id',textField:'borrowlevel_name',url:'borrowlevel?flagText=allBorrowLevelList'"/>
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:saveBorrowCard()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>
