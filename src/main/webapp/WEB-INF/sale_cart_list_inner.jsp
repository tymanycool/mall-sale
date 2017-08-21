<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<script type="text/javascript" src="jquery/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function sale_checkout_order(){
		location="/sale_order.do";
	}
	function cart_list_inner_update_cart_shfxz(id,sku_id,shfxz) {		
		if(shfxz){
			shfxz=1;
		}else{
			shfxz=0;
		}
		$.post("update_cart.do",{id:id,sku_id:sku_id,shfxz:shfxz,tjshl:-1},function(data){
			$("#cart_list_inner").html(data);
		}); 
	}
	 
	function cart_list_inner_update_cart_tjshl(id,sku_id,tjshl) {
		//alert(id+":"+sku_id+":"+tjshl);
		if(tjshl==0){
			alert("亲,不能再少了~~~");
			return;
		}
		$.post("/update_cart.do",{id:id,sku_id:sku_id,shfxz:"",tjshl:tjshl},function(data){
			$("#cart_list_inner").html(data);
		});
	}
	function sale_delete_cart(id,sku_id,sku_mch){
		//alert(sku_id);
		if(!confirm("确定要删除["+sku_mch+"]吗？"))
			return;
		$.post("/delete_cart.do",{sku_id:sku_id,id:id},function(data){
			//alert(data);
			if(data==true){
				location="/goto_cart_list.do";
				/* $.post("/goto_cart_list.do",function(data){
					$("#cart_list_inner").html(data);
				}); */
			}
		});
	}
</script>
<title>硅谷商城</title>
</head>
<body>
	<div class="Cbox">
		<table class="table table-striped table-bordered table-hover">
		   <thead>
		     <tr>
		       <th><input type="checkbox"/>&nbsp;&nbsp;&nbsp;&nbsp;</th>
		       <th>商品图片</th>
		       <th>商品名称</th>
		       <th>商品属性</th>
		       <th>商品价格</th>
		       <th>商品合计</th>
		       <th>商品数量</th>
		       <th>操作</th>
		     </tr>
		   </thead>
		   <tbody>
		    <c:forEach items="${cart_list}" var="cart">
		     	<tr>
		     	   <td><input type="checkbox" ${cart.shfxz=="1"?"checked":""} onclick="cart_list_inner_update_cart_shfxz(${cart.id},${cart.sku_id},this.checked)"/></td>
			       <td><img src="upload_image/${cart.shp_tp}" width="50px;"></td>
			       <td>${cart.sku_mch}</td>
			       <td>
			       		颜色：<span style='color:#ccc'>白色</span><br>
			       		尺码：<span style='color:#ccc'>L</span>
			       </td>
			       <td>${cart.sku_jg}</td>
			       <td>${cart.hj}</td>
			       <td>
				       	<a href="javascript:cart_list_inner_update_cart_tjshl(${cart.id},${cart.sku_id},${cart.tjshl-1});">-</a>
			       		<input disabled="true" type="text" name="min" value="${cart.tjshl}" style="width:50px;text-align:center">
						<a  href="javascript:cart_list_inner_update_cart_tjshl(${cart.id},${cart.sku_id},${cart.tjshl+1});">+</a>
			       </td>
			       <td><a href="javascript:;" onclick="sale_delete_cart(${cart.id},${cart.sku_id},'${cart.sku_mch}')">删除</a></td>
			   
		     	</tr>
		      </c:forEach>
		   </tbody>
	 	</table>
	</div>
	<div class="Cprice">
		<div class="price">￥${sum }</div>
		<div class="jiesuan" onclick="sale_checkout_order()">结算</div>
	</div>
	<div class="footer">
		<div class="top"></div>
		<div class="bottom"><img src="images/foot.jpg" alt=""></div>
	</div>
</body>
</html>