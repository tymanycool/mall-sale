package com.atguigu.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.service.ShoppingCartServiceImp;
import com.atguigu.service.ShoppingCartServiceInf;
import com.atguigu.util.MyJsonUtil;

@Controller
public class ShoppingCartController {

	@Autowired
	ShoppingCartServiceInf shoppingCartServiceImp;

	@RequestMapping("/mini_cart.do")
	public String mini_cart(ModelMap map, HttpSession session,
			@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie) {
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> cart_list = new ArrayList<T_MALL_SHOPPINGCAR>();

		if (user == null) {
			if (StringUtils.isNotBlank(list_cart_cookie)) {
				cart_list = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
			}
		} else {
			cart_list = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
		}
		map.put("cart_list", cart_list);
		int num =0;
		BigDecimal sum = new BigDecimal("0");
		for (T_MALL_SHOPPINGCAR cart : cart_list) {
			if("1".equals(cart.getShfxz())){
				BigDecimal hj = new BigDecimal(""+cart.getHj());
				sum = sum.add(hj);
			}
			num+=cart.getTjshl();
		}
		map.put("num", num);
		map.put("sum", sum.toString());
		return "sale_mini_cart_inner";
	}
	
	@ResponseBody
	@RequestMapping("/mini_cart_count.do")
	public String mini_cart_count( HttpSession session,
			@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie) {
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> cart_list = new ArrayList<T_MALL_SHOPPINGCAR>();
		
		if (user == null) {
			if (StringUtils.isNotBlank(list_cart_cookie)) {
				cart_list = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
			}
		} else {
			cart_list = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
		}
		int num =0;
		for (T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR : cart_list) {
			num+=t_MALL_SHOPPINGCAR.getTjshl();
		}
		return ""+num;
	}
	@ResponseBody
	@RequestMapping("/delete_cart.do")
	public boolean delete_cart(HttpSession session,T_MALL_SHOPPINGCAR cart,HttpServletResponse res,
			@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie){
		try{
			T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
			List<T_MALL_SHOPPINGCAR> cart_list = new ArrayList<T_MALL_SHOPPINGCAR>();
			if (user == null) {
				if (StringUtils.isNotBlank(list_cart_cookie)) {
					cart_list = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
					Iterator<T_MALL_SHOPPINGCAR> iter = cart_list.iterator();
					while(iter.hasNext()){
						if(iter.next().getSku_id()==cart.getSku_id()){
							iter.remove();
						}
					}
				}
				Cookie cookie = new Cookie("list_cart_cookie", MyJsonUtil.list_to_json(cart_list));
				res.addCookie(cookie);
				return true;
			} else {
				cart_list = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
				
				//操作session
				Iterator<T_MALL_SHOPPINGCAR> iter = cart_list.iterator();
				while(iter.hasNext()){
					T_MALL_SHOPPINGCAR next = iter.next();
					if(next.getId()==cart.getId()){
						cart=next;
						iter.remove();
					}
				}
				//操作db
				shoppingCartServiceImp.delete_cart(cart);
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	@RequestMapping("/goto_cart_list")
	public String goto_cart_list(ModelMap map, HttpSession session,
			@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie) {
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> cart_list = new ArrayList<T_MALL_SHOPPINGCAR>();

		if (user == null) {
			if (StringUtils.isNotBlank(list_cart_cookie)) {
				cart_list = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
			}
		} else {
			cart_list = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
		}
		//计算总价钱
		map.put("cart_list", cart_list);
		
		map.put("sum", get_sum(cart_list));
		return "sale_cart_list";
	}
	
	@RequestMapping("/update_cart")
	public String update_cart(ModelMap map, HttpServletResponse response,
			@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie_parm,
			T_MALL_SHOPPINGCAR cart, HttpSession session) {
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");

		List<T_MALL_SHOPPINGCAR> cart_list = new ArrayList<T_MALL_SHOPPINGCAR>();

		if (user == null) {
			// 操作cookie
			List<T_MALL_SHOPPINGCAR> list_cart_cookie = MyJsonUtil.json_to_list(list_cart_cookie_parm,
					T_MALL_SHOPPINGCAR.class);
			for (int i = 0; i < list_cart_cookie.size(); i++) {
				if (list_cart_cookie.get(i).getSku_id() == cart.getSku_id()) {
					
					if (cart.getTjshl() == -1) {
						//更新是否选中
						list_cart_cookie.get(i).setShfxz(cart.getShfxz());
					} else {
						//更新数量和合计
						list_cart_cookie.get(i).setTjshl(cart.getTjshl());
						BigDecimal bd1= new BigDecimal(cart.getTjshl());
						BigDecimal bd2= new BigDecimal(list_cart_cookie.get(i).getSku_jg());
						list_cart_cookie.get(i).setHj(bd1.multiply(bd2).doubleValue());
					}
				}
			}
			Cookie cookie = new Cookie("list_cart_cookie", MyJsonUtil.list_to_json(list_cart_cookie));
			cookie.setMaxAge(60 * 60 * 24 * 7);
			response.addCookie(cookie);
			cart_list = list_cart_cookie;
		} else {
			// 操作db
			List<T_MALL_SHOPPINGCAR> list_cart_session = (List<T_MALL_SHOPPINGCAR>) session
					.getAttribute("list_cart_session");

			for (int i = 0; i < list_cart_session.size(); i++) {
				
				if (list_cart_session.get(i).getId() == cart.getId()) {
					
					if (cart.getTjshl() == -1) {
						// 操作session
						list_cart_session.get(i).setShfxz(cart.getShfxz());
					} else {
						// 操作session
						list_cart_session.get(i).setTjshl(cart.getTjshl());
						
						BigDecimal bd1= new BigDecimal(cart.getTjshl());
						BigDecimal bd2= new BigDecimal(""+list_cart_session.get(i).getSku_jg());
						list_cart_session.get(i).setHj(bd1.multiply(bd2).doubleValue());
						cart.setHj(bd1.multiply(bd2).doubleValue());
					}
					// 操作db
					shoppingCartServiceImp.update_cart_2(cart);
					
				}
			}
			cart_list = list_cart_session;
		}
		
		//计算总价钱
		map.put("cart_list", cart_list);
		map.put("sum", get_sum(cart_list));
		return "sale_cart_list_inner";
	}
	//计算总价
	private String get_sum( List<T_MALL_SHOPPINGCAR>  cart_list){
		BigDecimal sum = new BigDecimal("0");
		for (T_MALL_SHOPPINGCAR c : cart_list) {
			if("1".equals(c.getShfxz())){
				BigDecimal hj = new BigDecimal(""+c.getHj());
				sum = sum.add(hj);
			}
		}
		return sum.toString();
	}
	
	@RequestMapping("/add_cart")
	public String add_cart(HttpServletResponse response, HttpSession session,
			@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie,
			T_MALL_SHOPPINGCAR cart,ModelMap map) {

		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");

		List<T_MALL_SHOPPINGCAR> cart_list = new ArrayList<T_MALL_SHOPPINGCAR>();

		if (user == null) {
			// 用户未登录，操作cookie
			if (StringUtils.isBlank(list_cart_cookie)) {
				// 向cookie添加购物车
				cart_list.add(cart);
			} else {
				cart_list = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
				// 判断购物车的商品是否重复
				boolean b = if_new_cart(cart_list, cart);
				if (b) {
					cart_list.add(cart);
				} else {
					for (int i = 0; i < cart_list.size(); i++) {
						if (cart_list.get(i).getSku_id() == cart.getSku_id()) {
							cart_list.get(i).setTjshl(cart_list.get(i).getTjshl() + cart.getTjshl());
							cart_list.get(i).setHj(cart_list.get(i).getHj() + cart.getHj());
						}
					}
				}
			}
			Cookie cookie = new Cookie("list_cart_cookie", MyJsonUtil.list_to_json(cart_list));
			
			cookie.setMaxAge(60 * 60 * 24 * 7);
			response.addCookie(cookie);
		} else {
			// 用户已登录，操作db
			cart_list = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
			cart.setYh_id(user.getId());
			if (cart_list == null || cart_list.size() == 0) {
				cart_list = new ArrayList<T_MALL_SHOPPINGCAR>();
				// 添加db
				shoppingCartServiceImp.add_cart(cart);
				cart_list.add(cart);
				session.setAttribute("list_cart_session", cart_list);
			} else {
				boolean b = if_new_cart(cart_list, cart);
				if (b) {
					shoppingCartServiceImp.add_cart(cart);
					cart_list.add(cart);// 直接更新session中的购物车数据
				} else {
					// 更新添加数量和购物车合计
					for (int i = 0; i < cart_list.size(); i++) {
						if (cart_list.get(i).getSku_id() == cart.getSku_id()) {
							cart_list.get(i).setTjshl(cart_list.get(i).getTjshl() + cart.getTjshl());
							cart_list.get(i).setHj(cart_list.get(i).getHj() + cart.getHj());
							shoppingCartServiceImp.update_cart(cart_list.get(i));
						}
					}
				}
			}
		}
		return "redirect:/cart_success.do";
	}
	/**
	 * 判断购物项是否存在
	 * @param cart_list
	 * @param cart
	 * @return
	 */
	private boolean if_new_cart(List<T_MALL_SHOPPINGCAR> cart_list, T_MALL_SHOPPINGCAR cart) {
		boolean b = true;
		for (int i = 0; i < cart_list.size(); i++) {
			if (cart_list.get(i).getSku_id() == cart.getSku_id()) {
				b = false;
			}
		}
		return b;
	}

	@RequestMapping("/cart_success")
	public String cart_success() {
		return "redirect:/goto_cart_list.do";
	}

}
