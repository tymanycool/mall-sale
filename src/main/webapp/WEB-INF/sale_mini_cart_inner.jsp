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
	function sale_mimi_goto_cart_list(){
		location="/goto_cart_list.do"
		
	}
	function sale_delete_cart(id,sku_id,sku_mch){
		//alert(sku_id);
		if(!confirm("确定要删除["+sku_mch+"]吗？"))
			return;
		$.post("/delete_cart.do",{sku_id:sku_id,id:id},function(data){
			//alert(data);
			if(data==true){
				$.post("/mini_cart.do",function(data){
					$("#mini_cart_inner").html(data);
				});
			}
		});
	}
</script>
<title>硅谷商城后台</title>
</head>
<body>

	<div class="cart_pro">
		<h6>最新加入的商品</h6>
		<c:forEach items="${cart_list}" var="cart">
			<div class="one">
				<img src="upload_image/${cart.shp_tp}" width="50px"/>
				<span class="one_name">
					${cart.sku_mch}&nbsp;数量:${cart.tjshl }
				</span>
				<span class="one_prece">
					<b>￥${cart.hj}</b><br/>
					<span style="cursor:pointer" onclick="sale_delete_cart(${cart.id},${cart.sku_id},'${cart.sku_mch}')">删除</span>
				</span>
			</div>
		</c:forEach>

		<div class="gobottom">
			共<span>${empty num?0:num}</span>件商品&nbsp;&nbsp;&nbsp;&nbsp;
			共计￥<span>${empty sum?0:sum }</span>
			<button id="sale_mimi_goto_cart_list" class="goprice" onclick="sale_mimi_goto_cart_list()">去购物车</button>
		</div>
	</div>

</body>
</html>