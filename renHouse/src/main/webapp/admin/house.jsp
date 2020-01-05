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
                url:'queryAllHouse',
                pagination:true,
                pageList:[3,5,8,10,15],
                pageSize:3,
                columns:[[
                    {checkbox:true,align:'right'},
                    {field:'id',title:'编号',width:100,align:'right'},
                    {field:'title',title:'标题',width:100,align:'right'},
                    {field:'dname',title:'区域',width:100,align:'right'},
                    {field:'sname',title:'街道',width:100,align:'right'},
                    {field:'tname',title:'类型',width:100,align:'right'},
                    {field:'price',title:'价格',width:100,align:'right'},
                    {field:'floorage',title:'面积',width:100,align:'right'},
                    {field:'ispass',title:'状态',width:100,align:'right',
                        formatter: function(value,row,index){
                            return value==0?"未审核":"已审核";
                        }
                    },
                    {field:'opt',title:'修改|操作', width:80,
                        formatter: function(value,row,index){
                            return "<a href='javascript:goPass("+row.id+")'>确认审核</a> | <a href='javascript:goToDel("+row.id+")'>详情</a>";
                        }
                    }
                ]]
            });
        })
        
        
        //通过审核出租房
        function goPass(id) {
            $.post("updateState",{"id":id,"state":1},function (data) {
                if (data.result > 0) {
                    $("#dg").datagrid('reload');//刷新
                }else {
                    $.messager.alert('友情提示','审核失败','info');
                }
            },"json")
        }
    </script>
</head>
<body>
<div id="ToolBar">
    <div style="height: 40px;">
        <a
                href="javascript:goDelMoreDistrict()" class="easyui-linkbutton"
                iconCls="icon-remove" plain="true">批量审核</a>
        区域<select></select>---街道<select></select>---类型<select></select>---审核状态<select>
        <option value="0">未审核</option><option value="1">已审核</option>
    </select>
        标题:<input type="text" name="title" id="">--价格 <input type="text" name="" id="" size="10">-<input type="text" name="" id="" size="10">
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