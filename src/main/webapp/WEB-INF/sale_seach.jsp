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
<title>检索</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<script type="text/javascript">
	$(function(){
		
	});
	
	
	
	//移除筛选条件
	function search_parm_down(obj,shxm_id){
		alert("param down...");
		$(obj).remove();
		$("#search_attr_"+shxm_id).show();
		
		$("#search_id_hide :input[id="+shxm_id+"]").remove();
		//alert(shxm_id);
		search_get_sku_by_attr();
	}
	//添加筛选条件
	function search_parm_up(shxm_id,shxzh_id,shzh_mch){
    	// 保存属性和属性名的id
    	var hide ="<input type='hidden' name='list_av' id=\'"+shxm_id+"\' value='{\"shxm_id\":"+shxm_id+",\"shxzh_id\":"+shxzh_id+"}'/>";
    	$("#search_id_hide").append(hide);
    	$("#search_attr_"+shxm_id).hide();
    	// 显示被选中属性值
    	//$("#search_mch_show").append('<input type="button" value=\"'+shzh_mch+'\" onclick="search_parm_down(this,'+shxm_id+')">');
    	//alert(shzh_mch);
    	$("#search_mch_show").append('<div onclick="search_parm_down(this,'+shxm_id+')"><span class="gt">&gt</span><div class="filter_div" >'+shzh_mch+'</div></div>');
    	
    	search_get_sku_by_attr();
    }
	
	function search_get_sku_by_attr(){
		var class_2_id = "${class_2_id}";
		var inputs = $(":input[name='list_av']");
		var data="";
		$(inputs).each(function(i,json){
			var json = json.value;
			json = $.parseJSON(json);
			data+="list_av["+i+"].shxm_id="+json.shxm_id+"&list_av["+i+"].shxzh_id="+json.shxzh_id+"&";
		});
		
		data += "class_2_id="+class_2_id+"&order="+$("#search_order_hide input").val();
		
		alert(data);
		
 		$.ajax({
 			url:"/get_sku_by_attr.do",
 			data:data,
 			success:function(data){
 				//alert("ok...");
				$("#search_list_inner").html(data);
			}
 		});  
	}
	
	
	function search_order(obj,new_order){
		$(".list_span").attr("id","");
		$(obj).parent().attr("id","list_beas");
    	old_order = $("#search_order_hide input").val();
    	if(new_order==old_order){
    		new_order += " desc "; 
    	}
    	$("#search_order_hide").html("<input type='hidden' value='"+new_order+"'/>");
    	search_get_sku_by_attr();
    }
	
</script>
</head>
<body>
	<!-- <div class="top">
		<div class="top_text">
			<a href="">用户登录</a>
			<a href="">用户注册</a>
			<a href="">供应商登录</a>
			<a href="">供应商注册</a>
		</div>
	</div> -->
	<jsp:include page="/WEB-INF/sale_index_header.jsp"></jsp:include>
	
	<div class="top_img">
		<img src="./images/top_img.jpg" alt="">
	</div>
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
	</div>
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
	
	<!--筛选阶段-->
	<div class="filter">
		<h2>${class_2_name}</h2>
		<div id="search_mch_show">
			<!-- <span class="gt">&gt</span>
			<div class="filter_div">
				电脑整机
			</div>
			<span class="gt">&gt</span>
			<div class="filter_div">
				游戏本
			</div> -->
		</div>
		<div class="filter_clear">
			清空筛选
		</div>
				
	</div>
	
	
	
	
	<div id="search_id_hide"></div>
	<div id="search_order_hide">
		<input type="hidden" value=" order by jg "/>
	</div>
	
	<div class="Sscreen">
		<div class="title">
			平板电视 商品筛选 共1205个商品
		</div>
		<!-- 分类属性列表:<br> -->
		<div class="list">
			<span>品牌：</span>
			<a href="">乐视TV(Letv)</a>
			<a href="">乐视TV(Letv)</a>
			<a href="">乐视TV(Letv)</a>
			<a href="">乐视TV(Letv)</a>
			<a href="">乐视TV(Letv)</a>
		</div>
		<c:forEach items="${list_attr}" var="attr">
			<div class="list" id="search_attr_${attr.id}">
				<span>${attr.shxm_mch}：</span>
				<c:forEach items="${attr.list_value}" var="val">
					<a href="javascript:;" onclick="search_parm_up(${attr.id},${val.id},'${val.shxzh}${val.shxzh_mch}')">${val.shxzh}${val.shxzh_mch}</a>
				</c:forEach>
			</div>
		</c:forEach>
		<div class="list">
			<span class="list_span" id="list_beas"><a href="javascript:;" onclick="search_order(this,' order by jg ')">价格</a></span>
			<span class="list_span"><a href="javascript:;" onclick="search_order(this,' order by sku_xl ')">销量 </a></span>
			<span class="list_span"><a href="javascript:;" onclick="search_order(this,' order by sku.chjshj ')">上架时间</a></span>
			<span class="list_span"><a href="javascript:;" onclick="search_order(this,' order by plsh ')">评论数</a> </span>
		</div>
	</div>
	
	<div id="search_list_inner">
		<jsp:include page="/WEB-INF/sale_seach_list_inner.jsp"></jsp:include>
	</div>

	<div class="footer">
		<div class="top"></div>
		<div class="bottom"><img src="images/foot.jpg" alt=""></div>
	</div>
</body>
</html>
