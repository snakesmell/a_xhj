<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ page import="java.lang.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
    + request.getServerName() + ":" + request.getServerPort()
    + path + "/";
%>
<%-- <%@ page import="java.util.regex.*"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
	<select class="t1">
		<option value="1">工作状态查询</option>
		<option value="2">灯色状态查询</option>
		<option value="3">时间查询</option>
		<option value="4">灯组查询</option>
		<option value="5">相位查询</option>
		<option value="6">配时查询</option>
		<option value="7">调度计划查询</option>
		<option value="8">故障查询</option>
		<option value="9">信号机版本查询</option>
		<option value="10">特征版本查询</option>
		<option value="11">识别码查询</option>
		<option value="12">检测器查询</option>
		<option value="13">相序表查询</option>
		<option value="14">方案表查询</option>
		<option value="15">动作查询</option>
		<option value="16">时段查询</option>
		<option value="17">跟随相位查询</option>
		<option value="18">单元参数查询</option>
		<option value="19">工作方式查询</option>
	</select>
	<button onclick="show()">操作</button>
</body>
<script>
$(document).ready(function(){
});


function show(){
	 $.ajax({
             type:'get',
             url : '<%=basePath%>/ClientAction',
             data :{COMMAND:$(".t1").val()},        
             success  : function(data) {}
	});
}

</script>
</html>