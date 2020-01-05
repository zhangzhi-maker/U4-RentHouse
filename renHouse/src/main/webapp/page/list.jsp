<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 首页</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="../css/style.css">
  <script src="${pageContext.request.contextPath}/admin/js/jquery-1.8.3.js" language="JavaScript" type="text/javascript"></script>
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
  <script>
    $(function () {
      $.post("queryAllType",null,function (data) {
        //加载类型
        for (var i=0; i< data.length;i++) {
          //创建option
          var optionnode=$("<option value="+data[i].id+">"+data[i].name+"</option>")
          //将option添加到下拉列表
          $("#typeId").append(optionnode);
        }

        //设置选中项
        $("#typeId").val(${searchCondition.tid});
      },"json");

      $.post("queryDistrictAll",null,function (data) {
        //加载类型
        for (var i=0; i< data.length;i++) {
          //创建option
          var optionnode=$("<option value="+data[i].id+">"+data[i].name+"</option>")
          //将option添加到下拉列表
          $("#district_id").append(optionnode);
        }

        $("#district_id").val(${searchCondition.did});

        //加载对应区域下的街道
        loadStreet();
        //设置街道选中项
        $("#street_id").val(${searchCondition.sid});
      },"json");

      //二级联动给区域下拉列表添加选项改变事件
      $("#district_id").change(function () {
        loadStreet();
      })
    })

    //通过区域加载街道
    function loadStreet() {
      //取当前区域选中项的id
      var did=$("#district_id").val();
      //清空原有选项
      $("#street_id>option:gt(0)").remove();
      //加载街道类型
      if(did!=""){
        $.post("queryStreetById",{"did":did},function (data) {
          //加载类型
          for (var i=0; i< data.length;i++) {
            //创建option
            var optionnode=$("<option value="+data[i].id+">"+data[i].name+"</option>")
            //将option添加到下拉列表
            $("#street_id").append(optionnode);
          }
        },"json");
      }
    }
  </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=navbar class=wrap>
<DL class="search clearfix">
  <FORM id=sform method=post action="queryHouseByCondition">
    <input type="hidden" name="page" id="savePage" value="1">
    标题：<input type="text" name="title" id="title" value="${searchCondition.title}">
    区域: <select name="did" id="district_id"><option value="">请选择</option></select>
    街道：<select name="sid" id="street_id"><option value="">请选择</option></select>
    类型：<select name="tid" id="typeId"><option value="">请选择</option></select>
    价格:<input type="text" name="startPrice" id="" value="${searchCondition.startPrice}">-
    <input type="text" name="endPrice" id="" value="${searchCondition.endPrice}">
    <input type="submit" value="搜索" name="submit">
  </FORM></DL></DIV>
<DIV class="main wrap">
<TABLE class=house-list>
  <TBODY>
  <c:forEach items="${pageInfo.list}" var="h">
  <TR>
    <TD class=house-thumb><span><A href="details.htm" target="_blank"><img src="http://localhost:80/${h.path}" width="100" height="75" alt=""></a></span></TD>
    <TD>
      <DL>
        <DT><A href="details.htm" target="_blank">${h.title}</A></DT>
        <DD>${h.dname}-${h.sname},${h.floorage}平米<BR>联系方式：${h.contact} </DD></DL></TD>
    <TD class=house-type>${h.tname}</TD>
    <TD class=house-price><SPAN>${h.price}</SPAN>元/月</TD>
    </TR>
  </c:forEach>
  </TBODY>
</TABLE>
<DIV class=pager>
<UL>
  <LI class=current><A href="javascript:goPage(1)">首页</A></LI>
  <LI><A href="javascript:goPage(${pageInfo.prePage})">上一页</A></LI>
  <LI><A href="javascript:goPage(${pageInfo.nextPage==0?pageInfo.pages:pageInfo.nextPage})">下一页</A></LI>
  <LI><A href="javascript:goPage(${pageInfo.pages})">末页</A></LI></UL><SPAN
class=total>1/2页</SPAN> </DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>

<script>
  function goPage(pageNum) {
    $("#savePage").val(pageNum);
    $("#sform").submit;
  }
</script>
