<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ page import="java.lang.*,com.udpUtil.*,java.util.*" %>

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
<style type="text/css">
	td{
		width: 300px;
		height: 150px;
		border-style: solid;
	}

</style>
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
	<br>
	<label>信号机实时灯态</label><br>
	<label class="xhj_address"></label>
	<div id="param2">
		<table style="border-style: solid;">
			<tr>
				<td class="north_west"></td>  <td class="north"></td>   <td class="north_east"></td>
			</tr>
			<tr>
				<td class="west"></td> 		  <td></td> 				<td class="east"></td>
			</tr>
			<tr>
				<td class="sourth_west"></td> <td class="sourth"></td>  <td class="sourth_east"></td>
			</tr>
		</table>
	</div>
	<label>相位驻留</label><br>
	<select class="phase">
		<option value="0">取消手动</option>
		<option value="1">相位一</option>
		<option value="2">相位二</option>
		<option value="3">相位三</option>
		<option value="4">相位四</option>
	</select>
	<button onclick="control(1)">操作</button>
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

function control(param){
	 $.ajax({
            type:'get',
            url : '<%=basePath%>/ControlAction',
            data :{COMMAND:param,phase:$(".phase").val()},        
            success  : function(data) {}
	});
}


setInterval(function(){ 
	$.ajax({
        type:'get',
        url : '<%=basePath%>/ColorAction',
        //data :{COMMAND:$(".t1").val()},        
        success  : function(data) {
        	var msg=data.substring(0,data.length-1);
        	console.log(msg); 
        	var sp=msg.split("*");
        	$(".sourth").html("");
        	$(".east").html("");
        	$(".west").html("");
        	$(".north").html("");
        	$(".north_east").html("");
        	$(".sourth_east").html("");
        	$(".north_west").html("");
        	$(".sourth_west").html("");
        	
        	$(".xhj_address").html(sp[sp.length-1]);
			console.log(sp);        	
        	for(var i=0;i<sp.length-1;i++){
        		var sub=sp[i].split("-");
        		switch (sub[1]) {
	        	    case "东":
	        	    	
	        	    	switch (sub[0]) {
	        	    		case "红": $(".east").append(sub[3]);$(".east").append("<label style=\"color: red;\">"+sub[2]+"</label></br>");break;
		        	   	 	case "黄": $(".east").append(sub[3]);$(".east").append("<label style=\"color: yellow;\">"+sub[2]+"</label></br>");break;
			        	   	case "绿": $(".east").append(sub[3]);$(".east").append("<label style=\"color: green;\">"+sub[2]+"</label></br>");break;
			        	   	case "不亮":$(".east").append(sub[3]); $(".east").append("<label style=\"color: gray;\">"+sub[2]+"</label></br>");break;
	        	    	}
	        	    	
	        	        break;
	        	    case "南":
	        	    	
	        	    	switch (sub[0]) {
	        	    		case "红": $(".sourth").append(sub[3]);$(".sourth").append("<label style=\"color: red;\">"+sub[2]+"</label></br>");break;
		        	   	 	case "黄": $(".sourth").append(sub[3]);$(".sourth").append("<label style=\"color: yellow;\">"+sub[2]+"</label></br>");break;
			        	   	case "绿": $(".sourth").append(sub[3]);$(".sourth").append("<label style=\"color: green;\">"+sub[2]+"</label></br>");break;
			        	   	case "不亮": $(".sourth").append(sub[3]);$(".sourth").append("<label style=\"color: gray;\">"+sub[2]+"</label></br>");break;
        	    		}
	        	    	
	        	         break;
	        	    case "西":
	        	    	
	        	    	switch (sub[0]) {
	        	    		case "红":$(".west").append(sub[3]); $(".west").append("<label style=\"color: red;\">"+sub[2]+"</label></br>");break;
		        	   	 	case "黄":$(".west").append(sub[3]); $(".west").append("<label style=\"color: yellow;\">"+sub[2]+"</label></br>");break;
			        	   	case "绿":$(".west").append(sub[3]); $(".west").append("<label style=\"color: green;\">"+sub[2]+"</label></br>");break;
			        	   	case "不亮": $(".west").append(sub[3]);$(".west").append("<label style=\"color: gray;\">"+sub[2]+"</label></br>");break;
        	    		}
	        	         break;
	        	    case "北":
	        	    	
	        	    	switch (sub[0]) {
	        	    		case "红": $(".north").append(sub[3]);$(".north").append("<label style=\"color: red;\">"+sub[2]+"</label></br>");break;
		        	   	 	case "黄":$(".north").append(sub[3]); $(".north").append("<label style=\"color: yellow;\">"+sub[2]+"</label></br>");break;
			        	   	case "绿": $(".north").append(sub[3]);$(".north").append("<label style=\"color: green;\">"+sub[2]+"</label></br>");break;
			        	   	case "不亮":$(".north").append(sub[3]); $(".north").append("<label style=\"color: gray;\">"+sub[2]+"</label></br>");break;
        	    		}
	        	         break;
	        	    case "东北":
	        	    	
	        	    	switch (sub[0]) {
	        	    		case "红": $(".north_east").append(sub[3]);$(".north_east").append("<label style=\"color: red;\">"+sub[2]+"</label></br>");break;
		        	   	 	case "黄": $(".north_east").append(sub[3]);$(".north_east").append("<label style=\"color: yellow;\">"+sub[2]+"</label></br>");break;
			        	   	case "绿": $(".north_east").append(sub[3]);$(".north_east").append("<label style=\"color: green;\">"+sub[2]+"</label></br>");break;
			        	   	case "不亮":$(".north_east").append(sub[3]); $(".north_east").append("<label style=\"color: gray;\">"+sub[2]+"</label></br>");break;
        	    		}
	        	         break;
	        	    case "东南":
	        	    	
	        	    	switch (sub[0]) {
	        	    		case "红": $(".sourth_east").append(sub[3]);$(".sourth_east").append("<label style=\"color: red;\">"+sub[2]+"</label></br>");break;
		        	   	 	case "黄": $(".sourth_east").append(sub[3]);$(".sourth_east").append("<label style=\"color: yellow;\">"+sub[2]+"</label></br>");break;
			        	   	case "绿": $(".sourth_east").append(sub[3]);$(".sourth_east").append("<label style=\"color: green;\">"+sub[2]+"</label></br>");break;
			        	   	case "不亮":$(".sourth_east").append(sub[3]); $(".sourth_east").append("<label style=\"color: gray;\">"+sub[2]+"</label></br>");break;
        	    		}
	        	         break;
	        	    case "西北":
	        	    	
	        	    	switch (sub[0]) {
	        	    		case "红": $(".north_west").append(sub[3]);$(".north_west").append("<label style=\"color: red;\">"+sub[2]+"</label></br>");break;
		        	   	 	case "黄": $(".north_west").append(sub[3]);$(".north_west").append("<label style=\"color: yellow;\">"+sub[2]+"</label></br>");break;
			        	   	case "绿": $(".north_west").append(sub[3]);$(".north_west").append("<label style=\"color: green;\">"+sub[2]+"</label></br>");break;
			        	   	case "不亮": $(".north_west").append(sub[3]);$(".north_west").append("<label style=\"color: gray;\">"+sub[2]+"</label></br>");break;
        	    		}
	        	        break;
	        	    case "西南":
	        	    	
	        	    	switch (sub[0]) {
	        	    		case "红": $(".sourth_west").append(sub[3]);$(".sourth_west").append("<label style=\"color: red;\">"+sub[2]+"</label></br>");break;
		        	   	 	case "黄": $(".sourth_west").append(sub[3]);$(".sourth_west").append("<label style=\"color: yellow;\">"+sub[2]+"</label></br>");break;
			        	   	case "绿": $(".sourth_west").append(sub[3]);$(".sourth_west").append("<label style=\"color: green;\">"+sub[2]+"</label></br>");break;
			        	   	case "不亮":$(".sourth_west").append(sub[3]); $(".sourth_west").append("<label style=\"color: gray;\">"+sub[2]+"</label></br>");break;
        	    		}
	        	        break;
        		} 
        	}
        	
        }
	});
}, 1000);
</script>
</html>