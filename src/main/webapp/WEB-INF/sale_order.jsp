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
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" type="text/css" href="css/list.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function b(){}
</script>
<title>硅谷商城</title>
</head>
<body>
	<jsp:include page="/WEB-INF/sale_index_header.jsp"></jsp:include>
	
	<div class="list">
	 <h3>用户确认订单页面</h3>
	 请选择地址:<br>
	<form action="/save_order.do" method="post">
		<c:forEach items="${list_address}" var="address">
			<input type="radio" name="address_id" value="${address.id}"/>${address.yh_dz}     ${address.shjr}<br>
		</c:forEach>
		<hr>
		<c:forEach items="${list_order}" var="order">
			<h4>总金额:${order.zje}:</h4>
			<div style="border:red 1px solid;">
			<c:forEach items="${order.list_order_info}" var="info">
				<img src="upload_image/${info.shp_tp}" width="50px"/>
				${info.sku_mch}&nbsp;价格:${info.sku_jg}&nbsp;数量:${info.sku_shl}
				<br>
			</c:forEach>
			</div>
			<br>
		</c:forEach>
	<hr>
	<input type="submit" value="提交订单"/>
	<br><br><br><br><br>
	</form>
	</div>
</body>
</html>