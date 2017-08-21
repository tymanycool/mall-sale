<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>商品详情</title>
<script type="text/javascript" src="jquery/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/css.css">

<script type="text/javascript">
	$(function(){
		
	});
	function goto_add_cert(){
		alert("goto_add_cert");
		$("#cert_add_cert_form").submit();
	}
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/sale_index_header.jsp"></jsp:include>
	<div class="search searchBac">
		<div class="logo"><img src="./images/logo.png" alt=""></div>
		<jsp:include page="/WEB-INF/sale_mini_cart.jsp"></jsp:include>
	</div>
	<form action="/add_cart.do" method="post" id="cert_add_cert_form">
		<input type="hidden" name="sku_mch" value="${obj_sku.sku_mch}"/>
		<input type="hidden" name="sku_jg" value="${obj_sku.jg}"/>
		<input type="hidden" name="tjshl" value="1"/>
		<input type="hidden" name="hj" value="${obj_sku.jg}"/>
		<input type="hidden" name="shp_id" value="${obj_sku.shp_id}"/>
		<input type="hidden" name="sku_id" value="${obj_sku.id}"/>
		<input type="hidden" name="shp_tp" value="${obj_sku.spu.shp_tp}"/>
		<input type="hidden" name="kcdz" value="${obj_sku.kcdz}"/>
		<!-- <input type="submit" value="添加购物车"/> -->
	</form>
	
	<div class="menu">
		<div class="nav">
			<div class="navs">
				<div class="left_nav">
					全部商品分类
					<div class="nav_mini" style="display:none">
						<ul>
							<li>
								<a href="">家用电器</a>
								<div class="two_nav">
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
								</div>
							</li>
							<li><a href="">手机、数码、通信</a></li>
							<li><a href="">电脑、办公</a></li>
							<li><a href="">家具、家居、家装</a></li>
							<li><a href="">男装、女装、内衣</a></li>
							<li><a href="">个户化妆</a></li>
							<li><a href="">鞋靴</a></li>
							<li><a href="">户外运动</a></li>
							<li><a href="">汽车</a></li>
							<li><a href="">母婴</a></li>
							<li><a href="">食品</a></li>
							<li><a href="">营养保健</a></li>
							<li><a href="">图书</a></li>
							<li><a href="">彩票</a></li>
							<li><a href="">理财</a></li>
						</ul>
					</div>
				</div>
				<ul>
					<li><a href="">服装城</a></li>
					<li><a href="">美妆馆</a></li>
					<li><a href="">超市</a></li>
					<li><a href="">全球购</a></li>
					<li><a href="">闪购</a></li>
					<li><a href="">团购</a></li>
					<li><a href="">拍卖</a></li>
					<li><a href="">金融</a></li>
					<li><a href="">智能</a></li>
				</ul>
			</div>
		</div>
	</div>
	
	<div class="Dbox">
		<div class="box">
			<div class="left">
				<div class="timg"><img src="upload_image/${obj_sku.spu.shp_tp }" alt="" style="width:300px;height:300px;"></div>
				<div class="timg2">
					<div class="lf"><img src="images/lf.jpg" alt=""></div>
					<div class="center">
						<c:forEach items="${obj_sku.list_image}" var="img" varStatus="status">
							<c:if test="${status.index!=0 }">
								<img src="upload_image/${img.url}" style="width:70px;height:70px;">
							</c:if>
						</c:forEach>
					</div>
					<div class="rg"><img src="images/rg.jpg" alt=""></div>
				</div>
				<div class="goods"><img src="images/img_6.jpg" alt=""></div>
			</div>
			<div class="cent">
				<div class="title">${obj_sku.spu.shp_mch }${obj_sku.spu.shp_msh}</div>
				<div class="bg">
					<p>市场价：<strong>￥${obj_sku.jg}</strong></p>
					<p>促销价：<strong>￥${obj_sku.jg}</strong></p>
				</div>
				
				<div class="clear">
					<div class="min_t">选择版本：</div>
					<c:forEach items="${list_sku}" var="sku">
						<div class="min_mx">${sku.sku_mch }</div>
					</c:forEach>
				</div>
				<div class="clear" style="margin-top:20px;">
					<div class="min_t" style="line-height:36px">数量：</div>
					<div class="num_box">
						<input type="text" name="num" value="1" style="width:40px;height:32px;text-align:center;float:left">
						<div class="rg">
							<img src="images/jia.jpg" id='jia' alt="">
							<img src="images/jian.jpg" id='jian' alt="">
						</div>
					</div>
				</div>
				<div class="clear" style="margin-top:20px;">
					<img src="images/mai.jpg" alt="">
					<img src="images/shop.jpg" alt=""  onclick="goto_add_cert()">
				</div>
			</div>
		</div>
	</div>
	<div class="Dbox1">
		<div class="boxbottom">
			<div class="top">
				<span>商品详情</span>
				<span>评价</span>
			</div>
			<div class="btm">
				<c:forEach items="${obj_sku.list_av_name}" var="av">
					${av.shxm_mch}:${av.shxzh_mch}<br>
				</c:forEach>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="top"></div>
		<div class="bottom"><img src="images/foot.jpg" alt=""></div>
	</div>
</body>
</html>