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


        function deleteBook() {
            var selectedRows = $("#dg").datagrid('getSelections');
            if (selectedRows.length == 0) {
                $.messager.alert("系统提示", "请选择要删除的数据！");
                return;
            }
            // if (selectedRows.length != 1) {
            //     $.messager.alert("系统提示", "请选择要删除的一条数据！");
            //     return;
            // }
            var strIds = [];
            for (var i = 0; i < selectedRows.length; i++) {
                strIds.push(selectedRows[i].book_ISBN);
            }
            var ids = strIds.join(",");
            $.messager.confirm("系统提示", "您确认要删掉这<font color=red>" + selectedRows.length + "</font>条数据吗？", function (r) {
                if (r) {
                    $.post("book", {delIds: ids, flagText: "delete"}, function (result) {
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

        function searchBook() {
            $('#dg').datagrid('load', {
                book_ISBN: $('#f_book_ISBN').val(),
                book_name: $('#f_book_name').val(),
                book_author: $('#f_book_author').val(),
                book_catagoryId: $('#f_book_catagoryId').combobox("getValue")
            });
        }


        function openAddBookDialog() {
            $('#book_catagoryId').combobox({
                valueField: 'bookcatagory_id',
                textField: 'bookcatagory_name',
                url: 'bookcatagory?flagText=allBookCatagoryList'
            });
            $("#dlg").dialog("open").dialog("setTitle", "添加图书");
            resetValue();
            url = "book?flagText=add";
        }

        function saveBook() {
            $("#fm").form("submit", {
                url: url,
                onSubmit: function () {
                    // alert($('#book_publishDate').datebox("getValue"));
                    // alert(new Date().getFullYear()+"-"+(new Date().getMonth()+1)+"-"+(new Date().getDate()));


                    if ($('#book_catagoryId').combobox("getValue") == -1) {
                        $.messager.alert("系统提示", "请选择类别");
                        return false;
                    }

                    // alert($('#book_stockNumber').validatebox("getValue"));

                    if (parseInt($('#book_stockNumber').val()) < parseInt($('#book_inNumber').val())) {
                        $.messager.alert("系统提示", "可借数量不能大于入库数量");
                        return false;
                    }

                    if ($('#book_publishDate').datebox("getValue") > $('#book_storageDate').datebox("getValue")) {
                        $.messager.alert("系统提示", "入库日期不能早于出版日期");
                        return false;
                    }

                    if ($('#book_publishDate').datebox("getValue").toString() > (new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + (new Date().getDate())) || $('#book_storageDate').datebox("getValue").toString() > (new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + (new Date().getDate()))) {
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
                        $.messager.alert("系统提示", json.msg);
                        resetValue();
                        $("#dlg").dialog("close");
                        $("#dg").datagrid("reload");
                    }
                }
            });
        }

        function resetValue() {
            $("#book_ISBN").val("");
            $("#book_name").val("");
            $("#book_author").val("");
            $("#book_catagoryId").combobox("setValue", "");
            $("#book_price").val("");
            $("#book_publish").val("");
            $("#book_stockNumber").val("");
            $("#book_publishDate").datebox("setValue", "");
            $("#book_storageDate").datebox("setValue", "");
            $("#book_inNumber").val("");

        }

        function closeDialog() {
            $("#dlg").dialog("close");
            resetValue();
        }

        function openUpdateBookDialog() {
            $('#book_catagoryId').combobox({
                valueField: 'bookcatagory_id',
                textField: 'bookcatagory_name',
                url: 'bookcatagory?flagText=allBookCatagoryList'
            });
            var selectedRows = $("#dg").datagrid('getSelections');
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条要编辑的数据！");
                return;
            }
            var row = selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle", "编辑图书信息");
            $("#fm").form("load", row);

            url = "book?flagText=update&book_ISBN=" + row.book_ISBN;
        }
    </script>
</head>
<body>
<table id="dg" title="管理图书信息" class="easyui-datagrid" fitColumns="true"
       pagination="true" url="book?flagText=allBook" fit="true" toolbar="#tb" .>
    <thead>
    <tr>
        <th field="cb" checkbox="true"></th>
        <th field="book_ISBN" width="100" align="center">ISBN</th>
        <th field="book_name" width="100" align="center">书名</th>
        <th field="book_catagoryId" width="50" align="center" hidden="true">书本类别号</th>
        <th field="bookcatagory_name" width="100" align="center">书本类别</th>
        <th field="book_author" width="100" align="center">作者</th>
        <th field="book_price" width="50" align="center">价格</th>
        <th field="book_publish" width="100" align="center">出版商</th>
        <th field="book_publishDate" width="100" align="center">出版日期</th>
        <th field="book_stockNumber" width="50" align="center">入库数量</th>
        <th field="book_storageDate" width="100" align="center">入库日期</th>
        <th field="book_inNumber" width="50" align="center">可借数量</th>

    </tr>
    </thead>
</table>

<div id="tb" align="center">
    <div>
        <a href="javascript:openAddBookDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:openUpdateBookDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:deleteBook()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <div>&nbsp;ISBN：&nbsp;<input type="text" name="f_book_ISBN" id="f_book_ISBN" size="10"/>
        &nbsp;书名：&nbsp;<input type="text" name="f_book_name" id="f_book_name" size="10"/>
        &nbsp;作者：&nbsp;<input type="text" name="f_book_author" id="f_book_author" size="10"/>
        &nbsp;类别：&nbsp;<input class="easyui-combobox" name="f_book_catagoryId" id="f_book_catagoryId" size="20"
                              data-options="panelHeight:'auto',editable:false,valueField:'bookcatagory_id',textField:'bookcatagory_name',url:'bookcatagory?flagText=allBookCatagoryList'"/>
        <a href="javascript:searchBook()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a></div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 650px;height: 350px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="10px;">
            <tr>
                <td>ISBN：</td>
                <td><input type="text" name="book_ISBN" id="book_ISBN" class="easyui-validatebox" required="true"/></td>
                <td>书名：</td>
                <td><input type="text" name="book_name" id="book_name" class="easyui-validatebox" required="true"/></td>
            </tr>
            <tr>
                <td>作者：</td>
                <td><input type="text" name="book_author" id="book_author" class="easyui-validatebox" required="true"/>
                </td>
                <td>书本类别：</td>
                <td><input class="easyui-combobox" required="true" name="book_catagoryId" id="book_catagoryId" size="20"
                           data-options="panelHeight:'auto',editable:false,valueField:'bookcatagory_id',textField:'bookcatagory_name',url:'bookcatagory?flagText=allBookCatagoryList'"/>
                </td>
            </tr>
            <tr>
                <td>价格：</td>
                <td><input type="number" name="book_price" id="book_price" class="easyui-validatebox" required="true"/>
                </td>
                <td>出版商：</td>
                <td><input type="text" name="book_publish" id="book_publish" class="easyui-validatebox"
                           required="true"/></td>
            </tr>
            <tr>
                <td>出版日期：</td>
                <td><input class="easyui-datebox" name="book_publishDate" id="book_publishDate" required="true"
                           editable="false"/></td>
                <td>入库日期：</td>
                <td><input class="easyui-datebox" name="book_storageDate" id="book_storageDate" required="true"
                           editable="false"/></td>
            </tr>
            <tr>
                <td>入库数量：</td>
                <td><input type="number" name="book_stockNumber" id="book_stockNumber" class="easyui-validatebox"
                           required="true"/></td>
                <td>可借数量：</td>
                <td><input type="number" name="book_inNumber" id="book_inNumber" class="easyui-validatebox"
                           required="true"/></td>
            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:saveBook()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>
