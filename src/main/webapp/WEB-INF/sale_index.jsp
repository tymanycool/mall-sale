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
		$.getJSON("json/class_1.js",function(data){
			$(data).each(function(i,json){
				var content = "";
				//onmouseover不能用？
				content+="<li value="+json.id+" onmouseenter='index_get_class_2_by_class_1_id(this.value)'>";
				content+="	<a>"+json.flmch1+"</a>";
				content+="	<div class='two_nav' id='index_class_2_ul_"+json.id+"'></div>";
				content+="</li>";
				$("#index_class_1_ul").append(content);
			});
		});
	});
	
	function index_get_class_2_by_class_1_id(class_1_id){
		$.getJSON("json/class_2_"+class_1_id+".js",function(data){
			$(".two_nav").hide();
			$("#index_class_2_ul_"+class_1_id).empty();
			$(data).each(function(i,json){
				//$("#index_class_2_ul_"+class_1_id).append("<span value="+json.id+"><a href='get_sku_by_class_2.do?class_2_id="+json.id+"&class_2_name="+json.flmch2+"'>"+json.flmch2+"</a></span>");
				$("#index_class_2_ul_"+class_1_id).append("<a href='get_sku_by_class_2.do?class_2_id="+json.id+"&class_2_name="+json.flmch2+"' onclick = 'test()'>"+json.flmch2+"</a>");
				//var content = '<a href="javascript:;" onclick="alert(3)">11111</a>';
				//$("#index_class_2_ul_"+class_1_id).append(content);
			});
			$("#index_class_2_ul_"+class_1_id).show();
		});
	}
	


</script>
<title>硅谷商城后台</title>
</head>
<body>
	<jsp:include page="/WEB-INF/sale_index_header.jsp"></jsp:include>
	<div class="search">
		<div class="logo"><img src="./images/logo.jpg" alt=""></div>
		<div class="search_on">
			<div class="se">
				<input type="text" name="search" class="lf">
				<input type="submit" class="clik" value="搜索">
			</div>
			<div class="se">
				<a href="">取暖神奇</a>
				<a href="">1元秒杀</a>
				<a href="">吹风机</a>
				<a href="">玉兰油</a>
			</div>
		</div>
		<jsp:include page="/WEB-INF/sale_mini_cart.jsp"></jsp:include>
	</div>
	
	
	<div class="menu">
		<div class="nav">
			<div class="navs">
				<div class="left_nav">
					全部商品分类
					<div class="nav_mini">
						<ul id="index_class_1_ul"></ul>
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
	<div class="banner">
		<div class="ban">
			<img src="./images/banner.jpg" width="980" height="380" alt="">
		</div>
	</div>
</body>
</html>