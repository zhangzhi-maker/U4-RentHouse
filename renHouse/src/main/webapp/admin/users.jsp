<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <title>区域管理</title>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyUI/css/demo.css">
    <script src="js/jquery-1.8.3.js" language="JavaScript" type="text/javascript"></script>
    <!--jquery.easyui.min.js包含了easyUI中的所有插件-->
    <script src="js/jquery.easyui.min.js" language="JavaScript" type="text/javascript"></script>
    <script>
        $(function () {
            $('#dg').datagrid({
                toolbar:'#ToolBar',
                url:'queryUsersByPage',
                pagination:true,
                pageList:[3,5,8,10,15],
                pageSize:3,
                columns:[[
                    {checkbox:true,align:'right'},
                    {field:'id',title:'编号',width:100,align:'right'},
                    {field:'name',title:'用户名',width:100,align:'right'},
                    {field:'telephone',title:'电话',width:100,align:'right'},
                    {field:'isadmin',title:'是否管理员',width:100,align:'right'},
                    {field:'age',title:'年龄',width:100,align:'right'},
                    {field:'opt',title:'修改|操作', width:80,
                        formatter: function(value,row,index){
                            return "<a href='#'>修改</a> | <a href='javascript:goToDel("+row.id+")'>删除</a>";
                        }
                    }
                ]]
            });
        })
    </script>
</head>
<body>
<div id="ToolBar">
    <div style="height: 40px;">
        <a href="javascript:goAdd()" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加</a>
        <a
                href="javascript:goUpdate()" class="easyui-linkbutton"
                iconCls="icon-edit" plain="true">修改</a>
        <a
                href="javascript:goDelMoreDistrict()" class="easyui-linkbutton"
                iconCls="icon-remove" plain="true">批量删除</a>
        名称：<input type="text" name="name" id="inputName">
        电话： <input type="text" name="tel" id="inputTel">
        <a id="btn" href="javascript:searchUsers();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
    </div>
</div>
<table id="dg"></table>
</body>
<script>
    //实现datagrid绑定查询条件
    function searchUsers() {
        var inputName=$("#inputName").val();
        var inputTel=$("#inputTel").val();

        $("#dg").datagrid("load",{
            //设置查询条件
            name:inputName,
            tel:inputTel
        });
    }
</script>
</html>