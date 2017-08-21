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
<script type="text/javascript">
	function mini_cart_show(){
		$.post("/mini_cart.do",function(data){
			$("#mini_cart_inner").html(data);
		});
		$.post("/mini_cart_count.do",function(data){
			$("#sale_cart_count").html(data);
		});
		$("#mini_cart_inner").show();
	}
	
	function mini_cart_hide(){
		$("#mini_cart_inner").hide();
	}
</script>
<title>硅谷商城</title>
</head>
<body>
	
	<div class="card">
		<a href="goto_cart_list.do" onmouseover="mini_cart_show()"><div class="num" id="sale_cart_count"></div>购物车</a>
		<!--购物车商品-->
		<!-- onmouseout不能用？ -->
		<div id="mini_cart_inner" onmouseleave="mini_cart_hide()" >
	
		</div>
	</div>

</body>
</html>