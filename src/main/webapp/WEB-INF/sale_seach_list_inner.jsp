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
	$(function(){
		$(".search_add_cart_a").click(function(){
			var params={
				sku_id:$(this).attr("sku_id"),
				tjshl:1,
				shp_tp:$(this).attr("shp_tp"),
				sku_mch:$(this).attr("sku_mch"),
				sku_jg:$(this).attr("sku_jg"),
				hj:$(this).attr("sku_jg"),
				kcdz:$(this).attr("kcdz"),
				shp_id:$(this).attr("shp_id"),
			};
			$.post("/add_cart.do",params,function(data){
				alert("添加成功~~~");
			})
			return false;
		});
	});
	function search_goto_sku_detail(sku_id,spu_id){
		alert(spu_id);
		// 在新页面中打开商品详情
		window.open("goto_sku_detail.do?sku_id="+sku_id+"&spu_id="+spu_id);
	}
	
</script>
<title>suser-test</title>
</head>
<body>
	<%-- <form action="/add_cart.do" method="post" id="cert_add_cert_form">
		<input type="hidden" name="sku_mch" value="${obj_sku.sku_mch}"/>
		<input type="hidden" name="sku_jg" value="${obj_sku.jg}"/>
		<input type="hidden" name="tjshl" value="1"/>
		<input type="hidden" name="hj" value="${obj_sku.jg}"/>
		<input type="hidden" name="shp_id" value="${obj_sku.shp_id}"/>
		<input type="hidden" name="sku_id" value="${obj_sku.id}"/>
		<input type="hidden" name="shp_tp" value="${obj_sku.spu.shp_tp}"/>
		<input type="hidden" name="kcdz" value="${obj_sku.kcdz}"/>
		<!-- <input type="submit" value="添加购物车"/> -->
	</form> --%>
	<div class="Sbox">
		<c:forEach items="${list_sku}" var="sku">
			<div class="list">
				<div onclick="search_goto_sku_detail(${sku.id},${sku.spu.id})" style="cursor:pointer;border:1px solid red;width:150px;height:250px;float:left;margin-left:20px;margin-top:20px;">
					<div class=""><img src="upload_image/${sku.spu.shp_tp}" width="150px" height="150"></div>
					<div class="title" >${sku.sku_mch}</div>
					<div class="price">价格：${sku.jg}</div>
					<div class="title">库存：${sku.kc}</div>
					<div class="title" >
						<a class="search_add_cart_a" 
							sku_id="${sku.id}" 
							shp_tp="${sku.spu.shp_tp}"
							sku_mch="${sku.sku_mch}"
							sku_jg="${sku.jg}"
							kcdz="${sku.kcdz}"
							shp_id="${sku.shp_id}"
							>加入购物车</a>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>
