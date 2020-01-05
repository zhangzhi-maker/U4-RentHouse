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
                url:'/admin/queryAllDistrict',
                pagination:true,
                pageList:[3,5,8,10,15],
                pageSize:3,
                columns:[[
                    {checkbox:true,align:'right'},
                    {field:'id',title:'地区编号',width:100,align:'right'},
                    {field:'name',title:'地区名称',width:100,align:'right'},
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
    </div>
</div>
<table id="dg"></table>
<!--添加窗口-->
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true"  modal="true">
    <form id="addDialogForm" method="post">
        <table>
            <tr>
                <td>区域名称:</td>
                <td><input type="text" class="easyui-validatebox" required
                           name="name" id="bname" /></td>
            </tr>

        </table>
    </form>
</div>
<!--添加窗口的按钮-->
<div id="AddDialogButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a> <a href="javascript:CloseDialog()"
                                   class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>

<!--修改窗口-->
<div id="updateDialog" class="easyui-dialog" buttons="#UpdateDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true"  modal="true">
    <form id="updateDialogForm" method="post">

        <table>
            <tr>
                <td>区域编号:</td>
                <td><input type="text" class="easyui-validatebox" required
                           name="id" id="bname" /></td>
            </tr>
            <tr>
                <td>区域名称:</td>
                <td><input type="text" class="easyui-validatebox" required
                           name="name" id="bname" /></td>
            </tr>

        </table>
    </form>
</div>
<!--修改窗口的按钮-->
<div id="UpdateDialogButtons">
    <a href="javascript:UpdateDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">更新</a> <a href="javascript:CloseUpDialog()"
                                   class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
</body>
<script>
    function goAdd(){
        //打开对话框
        //$("#AddDialog").dialog("open");
        $("#AddDialog").dialog("open").dialog('setTitle',"添加区域");
    }

    function CloseDialog() {
        $("#AddDialog").dialog("close");
    }

function SaveDialog() {
    $('#addDialogForm').form('submit', {
        url:"addDistrict",
        success:function(data){
            var obj=$.parseJSON(data);//将json字符串转化为json对象
            if(obj.result>0){
                //成功就关闭窗口
                $("#AddDialog").dialog("close");
                $("#dg").datagrid("reload")
            }else {
                $.messager.alert('友情提示','添加失败','info');
            }
        }
    });
}

    function goUpdate(){
        //1.获取dategrid的选中行
        var selObjs= $("#dg").datagrid("getSelections");
        console.log(selObjs);
        //验证是否选中一行
        if (selObjs.length == 1) {
            //打开对话框
            //$("#AddDialog").dialog("open");
            $("#updateDialog").dialog("open").dialog('setTitle',"编辑区域");
            //回显
            //$("#updateDialogForm").form('load',json对象：{"表单对象名称":值})
            //$("#updateDialogForm").form('load',selObjs[0]);
            //使用post方式发送异步请求
            var id=selObjs[0].id;
            $.post("selectById",{"id":id},function (data) {
                $("#updateDialogForm").form('load',data);
            },"json")
        } else {
            $.messager.alert('友情提示','请选中一行!而不是多行或者不选','info');
        }
    }

    function CloseUpDialog() {
        $("#updateDialog").dialog("close");
    }

    function UpdateDialog() {
        $('#updateDialogForm').form('submit', {
            url:"updateDistrict",
            success:function(data){
                var obj=$.parseJSON(data);//将json字符串转化为json对象
                if(obj.updates>0){
                    $("#dg").datagrid("reload")
                    //成功就关闭窗口
                    $("#updateDialog").dialog("close");

                }else {
                    $.messager.alert('友情提示','更改失败','info');
                }
            }
        });
    }

    function goToDel(id) {
        $.messager.confirm('确认对话框', '确定删除？', function(r){
            if (r){
                $.post("deleteDistrict",{"id":id},function (data) {
                    if(data.integer>0){
                        $("#dg").datagrid("reload")
                        //成功就关闭窗口
                        $("#updateDialog").dialog("close");
                    }else {
                        $.messager.alert('友情提示','更改失败','info');
                    }
                },"json")
            }
        });
    }
    
    function goDelMoreDistrict() {
        //1.获取选中行
        var selObjs=$("#dg").datagrid("getSelections");
        //判断有没有选中项
        if (selObjs.length>0){
            $.messager.confirm('确认对话框', '确定删除？', function(r){
                if (r){
                    var  str="";
                    for(var i=0;i<selObjs.length;i++) {
                        str=str+selObjs[i].id+",";
                    }
                    str=str.substring(0,str.length-1);
                    $.post("delDistrictMore",{"ids":str},function (data) {
                        if(data.integer>0){
                            $("#dg").datagrid("reload")
                            //成功就关闭窗口
                            $("#updateDialog").dialog("close");
                        }
                    },"json")
                }
            });
            $("#dg").datagrid("reload")
        }else {
            $.messager.alert('友情提示','更改失败','info');
        }
    }
</script>
</html>