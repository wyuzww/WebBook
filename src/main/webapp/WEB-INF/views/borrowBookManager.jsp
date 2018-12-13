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


        function searchBook() {
            $('#dg').datagrid('load', {
                book_ISBN: $('#f_book_ISBN').val(),
                book_name: $('#f_book_name').val(),
                book_author: $('#f_book_author').val(),
                book_catagoryId: $('#f_book_catagoryId').combobox("getValue")
            });
        }

        //
        // function openAddBookDialog() {
        //     $("#dlg").dialog("open").dialog("setTitle", "添加图书");
        //     resetValue();
        //     url = "book?flagText=add";
        // }

        function borrowBook() {
            $("#fm").form("submit", {
                url: url,
                onSubmit: function () {

                    if ($('#borrowcard_id').combobox("getValue") == "") {
                        $.messager.alert("系统提示", "请选择借阅证编号");
                        return false;
                    }

                    if ($('#borrow_borrowDate').datebox("getValue") == "") {
                        $.messager.alert("系统提示", "请选择借阅日期");
                        return false;
                    }

                    if ($('#borrow_borrowDate').datebox("getValue").toString() > (new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + (new Date().getDate()))) {
                        $.messager.alert("系统提示", "不能使用未来日期");
                        return false;
                    }

                    if ($('#borrow_borrowDate').datebox("getValue") < $('#book_storageDate').datebox("getValue")) {
                        $.messager.alert("系统提示", "借阅日期不能早于入库日期");
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
            $("#borrowcard_id").combobox("setValue", "");
            $("#borrow_borrowDate").datebox("setValue", "");

        }

        function closeDialog() {
            $("#dlg").dialog("close");
            resetValue();
        }

        function openBorrowBookDialog() {
            resetValue();
            $('#borrowcard_id').combobox({
                url: 'borrowcard?flagText=allBorrowCardList',
                //mode:'local',//默认即使local
                valueField: 'borrowcard_id',
                textField: 'borrowcard_id',
                formatter: function (row) {
                    return '<span class="item-text">' + row.borrowcard_id + "---" + row.user_name + '</span>';
                }
            });
            var selectedRows = $("#dg").datagrid('getSelections');
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一本要借的书！");
                return;
            }
            var row = selectedRows[0];

            if (row.book_inNumber <= 0) {
                $.messager.alert("系统提示", "该书已借完！");
                return;
            }

            $("#dlg").dialog("open").dialog("setTitle", "借书操作");
            $("#fm").form("load", row);

            url = "borrow?flagText=borrowBook";
        }
    </script>
</head>
<body>
<table id="dg" title="借书操作" class="easyui-datagrid" fitColumns="true"
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
        <th field="book_inNumber" width="50" align="center">可借数量</th>
        <th field="book_storageDate" width="100" align="center">入库日期</th>
    </tr>
    </thead>
</table>

<div id="tb" align="center">
    <div>
        <a href="javascript:openBorrowBookDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">借书</a>
    </div>
    <div>&nbsp;ISBN：&nbsp;<input type="text" name="f_book_ISBN" id="f_book_ISBN" size="10"/>
        &nbsp;书名：&nbsp;<input type="text" name="f_book_name" id="f_book_name" size="10"/>
        &nbsp;作者：&nbsp;<input type="text" name="f_book_author" id="f_book_author" size="10"/>
        &nbsp;类别：&nbsp;<input class="easyui-combobox" name="f_book_catagoryId" id="f_book_catagoryId" size="20"
                              data-options="panelHeight:'auto',editable:false,valueField:'bookcatagory_id',textField:'bookcatagory_name',url:'bookcatagory?flagText=allBookCatagoryList'"/>
        <a href="javascript:searchBook()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a></div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 500px;height: 400px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="10px;">
            <tr>
                <td>借书证编号：</td>
                <td><input class="easyui-combobox" required="true" id="borrowcard_id" name="borrowcard_id" size="20"/>
                </td>
            </tr>
            <tr style="height: 20px">
                <td>

                </td>
            </tr>
            <tr>
                <td>ISBN：</td>
                <td><input type="text" name="book_ISBN" id="book_ISBN" class="easyui-validatebox" required="true"
                           readonly="true"/></td>
            </tr>
            <tr>
                <td>书名：</td>
                <td><input type="text" name="book_name" id="book_name" class="easyui-validatebox" required="true"
                           readonly="true"/></td>
            </tr>
            <tr>
                <td>入库日期：</td>
                <td>
                    <input class="easyui-datebox" id="book_storageDate" name="book_storageDate" readonly="true"
                           editable="false" hidden="hidden"/>
                </td>
                </td>
            </tr>
            <tr style="height: 15px">
                <td>

                </td>
            </tr>
            <tr>
                <td>借书日期：</td>
                <td>
                    <input class="easyui-datebox" id="borrow_borrowDate" name="borrow_borrowDate" required="true"
                           editable="false"/>
                </td>

            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:borrowBook()" class="easyui-linkbutton" iconCls="icon-ok">借书</a>
    <a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>
