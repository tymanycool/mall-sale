<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<script type="text/javascript" src="jquery/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/css.css">
<script type="text/javascript">
	$(function (){
		var yh_nch = getMyCookie("yh_mch");
		if(yh_nch){
			$("#index_header_sapn").html(yh_nch+"你好!");
		}
		
	});
	
	function getMyCookie(key){
		var cookie = document.cookie;
		var cookie_r = cookie.replace(/\s/,"");
		var cookie_s = cookie_r.split(";");
		var val;
		for(i=0;i<cookie_s.length;i++){
			var cookie_ss = cookie_s[i].split("=");
			if(cookie_ss[0]==key){
				val = decodeURIComponent(cookie_ss[1]);
			}
		}
		return val;
	}
</script>
<title>硅谷商城后台</title>
</head>
<body>
	<div class="top">
		<div class="top_text">
			
			<c:if test="${empty user}">
				 <a><span id="index_header_sapn"></span></a> 
				 <a href="/goto_login.do">用户登录</a> 
				 <a href="/goto_regist.do">用户注册</a>
			</c:if>
			<c:if test="${not empty user}">
				<a href="javascript:;">欢迎 ${not empty user.yh_nch?user.yh_nch:user.yh_mch}</a> 
				<a href="/logout.do">登出</a> 
				<a href="goto_login.do">我的订单</a> 
			</c:if>
			
		</div>
	</div>
</body>
</html>