package com.atguigu.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.soap.SOAPFaultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.service.IndexServiceInf;
import com.atguigu.service.ShoppingCartServiceInf;
import com.atguigu.service.UserServiceInf;
import com.atguigu.util.MyJsonUtil;

@Controller
public class IndexController {
	
	@Autowired
	IndexServiceInf indexServiceInf;
	
	@Autowired
	UserServiceInf userServiceInf;
	
	@Autowired
	ShoppingCartServiceInf shoppingCartServiceImp;
	
	@RequestMapping("/test")
	public String test(){
		int test = indexServiceInf.test();
		System.out.println(test);
		return "test";
	}
	
	@RequestMapping("/validate")
	public String validate() {
		
		return "validate";
	}
	@RequestMapping("/sale_index")
	public String index(HttpServletRequest request, ModelMap map) {
		
		return "sale_index";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "sale_index";
	}
	
	@RequestMapping("/goto_login")
	public String goto_login() {
		return "sale_login";
	}
	@RequestMapping("/goto_regist")
	public String goto_regist() {
		
		return "sale_regist";
	}
	@ResponseBody
	@RequestMapping("/doregist")
	public Object doregist(T_MALL_USER_ACCOUNT user,HttpSession session) {
		boolean regist = userServiceInf.regist(user);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", false);
		if(regist){
			T_MALL_USER_ACCOUNT db_user = userServiceInf.get_user_account(user);
			session.setAttribute("user", db_user);
			
			map.put("success", true);
			map.put("yh_mch", db_user.getYh_mch());
		}
		return map;
	}
	
	@RequestMapping("/login.do")
	public String login(@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie,
			T_MALL_USER_ACCOUNT user,HttpServletResponse res,HttpSession session){
		
		//T_MALL_USER_ACCOUNT db_user = indexServiceImpl.get_user_account(user);
		//调用sebservice
		T_MALL_USER_ACCOUNT db_user = null;
		try {
			db_user = userServiceInf.get_user_account(user);
		} catch (Exception e){
			e.printStackTrace();
			return "redirect:/goto_login.do";
		} 
		if(db_user!=null){
			String yh_nch = db_user.getYh_mch();
			try {
				yh_nch = URLEncoder.encode(yh_nch, "utf-8");// 转译成url编码
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			Cookie cookie = new Cookie("yh_mch",yh_nch);
			cookie.setMaxAge(60*2);
			res.addCookie(cookie);
			
			session.setAttribute("user", db_user);
			merge_cart(list_cart_cookie, session, db_user.getId(), res);
			
			return "redirect:/sale_index.htm";
		}else{
			return "redirect:/goto_login.do";
		}
		
	}
	
	/**
	 * 合并数据库与cookie中的数据，并把结果放在session中，清空cookie中的数据
	 * @param list_cart_cookie_parm
	 * @param session
	 * @param yh_id
	 * @param response
	 * @return
	 */
	private boolean merge_cart(String list_cart_cookie_parm, HttpSession session, int yh_id,
			HttpServletResponse response) {
		//得到Cookie中的购物车集合
		List<T_MALL_SHOPPINGCAR> list_cart_cookie = JSON.parseArray(list_cart_cookie_parm, T_MALL_SHOPPINGCAR.class);
		//得到数据库中的购物车集合
		List<T_MALL_SHOPPINGCAR> list_cart_db = shoppingCartServiceImp.get_list_cart_by_user_id(yh_id);
		
		//得到session中的用户信息
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT)session.getAttribute("user");
		
		if(list_cart_cookie==null||list_cart_cookie.size()==0){//如果Cookie购物车集合没有数据不做任何操作
			
		}else{//如果购物车中的数据购物车不为空
			if(list_cart_db==null||list_cart_db.size()==0){//如果数据库中没有数据则直接添加
				for(T_MALL_SHOPPINGCAR sc:list_cart_cookie){
					//设置用户的id
					sc.setYh_id(user.getId());
					shoppingCartServiceImp.add_cart(sc);
				}
			}else{//如果数据库有数据
				for(T_MALL_SHOPPINGCAR sc:list_cart_cookie){
					if(if_new_car(list_cart_db,sc)){//如果购物车中没有相同的数则直接添加
						//设置用户的id
						sc.setYh_id(user.getId());
						shoppingCartServiceImp.add_cart(sc);
					}else{//如果购物车中有相同的数则更新数据库中数量和合计即可
						for(T_MALL_SHOPPINGCAR cd:list_cart_db){
							if(cd.getSku_id()==sc.getSku_id()){
								//设置用户的id
								sc.setYh_id(user.getId());
								//更新数量
								sc.setTjshl(sc.getTjshl()+cd.getTjshl());
								//更新合计
								sc.setHj(sc.getHj()+cd.getHj());
								shoppingCartServiceImp.update_cart(sc);
							}
						}
					}
					
				}
			}
		}
		//添加完成将Cookie中的数据置空
		Cookie cookie = new Cookie("list_cart_cookie","");
		response.addCookie(cookie);
		//从数据库中重新查询该用户的购物车，并放在session中
		list_cart_db = shoppingCartServiceImp.get_list_cart_by_user_id(yh_id);
		session.setAttribute("list_cart_session", list_cart_db);
		
		return true;
	}
	/**
	 * 判断car是不是一个新的，如果在list_cart中存在则，返回false否则返回true
	 * @param list_cart
	 * @param car
	 * @return
	 */
	private boolean if_new_car(List<T_MALL_SHOPPINGCAR> list_cart, T_MALL_SHOPPINGCAR car) {
		boolean b = true;

		for (int i = 0; i < list_cart.size(); i++) {
			if (car.getSku_id() == list_cart.get(i).getSku_id()) {
				b = false;
			}
		}
		return b;
	}
	
	
}
