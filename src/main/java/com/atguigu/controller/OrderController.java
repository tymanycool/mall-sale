package com.atguigu.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.atguigu.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_ORDER_INFO;
import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.service.AddressServiceInf;
import com.atguigu.service.OrderServiceInf;
import com.atguigu.service.ShoppingCartServiceInf;
import com.atguigu.service.UserServiceInf;

@Controller
public class OrderController {
	@Autowired
	AddressServiceInf addressServiceInf;
	
	@Autowired
	OrderServiceInf orderServiceImpl;
	
	@Autowired
	UserServiceInf userServiceInf;
	
	@Autowired
	OrderServiceInf orderServiceInf;
	
	@Autowired
	ShoppingCartServiceInf shoppingCartServiceImp;

	@RequestMapping("/sale_goto_pay")
	public String sale_goto_pay() {
		
		return "sale_goto_pay";
	}
	@RequestMapping("/pay_success")
	public String pay_success() {
		
		return "sale_pay_success";
	}
	
	@RequestMapping("/order_pay")
	public String order_pay(HttpSession session) {
		List<OBJECT_T_MALL_ORDER> list_order = (List<OBJECT_T_MALL_ORDER>) session.getAttribute("list_order");
		orderServiceImpl.order_pay(list_order);
		return "redirect:/pay_success.do";
	}
	
	@RequestMapping("/login_buy")
	public String login_buy(@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie,
			T_MALL_USER_ACCOUNT user,HttpSession session,ModelMap map,HttpServletResponse res) {
		
		T_MALL_USER_ACCOUNT db_user=null;
		try {
			db_user = userServiceInf.get_user_account(user);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(db_user!=null){
			session.setAttribute("user", db_user);
			merge_cart(list_cart_cookie, session, db_user.getId(), res);
			return "redirect:/sale_order.do";
		}
		return "goto_login_buy";
	}
	@RequestMapping("/goto_login_buy")
	public String goto_login_buy() {
		return "sale_login_buy";
	}
	//提交订单时调用
	@RequestMapping("/save_order")
	public String save_order(int address_id,HttpSession session, ModelMap map) {
		List<OBJECT_T_MALL_ORDER> list_order = (List<OBJECT_T_MALL_ORDER>) session.getAttribute("list_order");
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		T_MALL_ADDRESS address = addressServiceInf.get_addresses_by_id(address_id);
		List<Integer> list_cart_id = orderServiceImpl.save_order(user,list_order, address);
		
		List<T_MALL_SHOPPINGCAR> list_cart_session = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");

		Iterator<T_MALL_SHOPPINGCAR> iterator = list_cart_session.iterator();

		while (iterator.hasNext()) {
			T_MALL_SHOPPINGCAR next = iterator.next();
			if (list_cart_id.contains(next.getId())) {
				iterator.remove();
			}
		}
		// 更新sessin中的购物车数据
		return "redirect:/sale_goto_pay.do";
	}
	
	@RequestMapping("/sale_order")
	public String sale_order(HttpSession session, ModelMap map){
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT)session.getAttribute("user");
		List<OBJECT_T_MALL_ORDER> list_order = new ArrayList<OBJECT_T_MALL_ORDER>();
		if (user == null) {
			return "redirect:/goto_login_buy.do";
		} else {
			// 拆单业务
			@SuppressWarnings("unchecked")
			List<T_MALL_SHOPPINGCAR> cart_list = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");

			// 循环购物车对象，取出仓库信息去重
			HashSet<String> list_ckdz = new HashSet<String>();

			for (int i = 0; i < cart_list.size(); i++) {
				list_ckdz.add(cart_list.get(i).getKcdz());
			}

			// 循环库存地址，
			Iterator<String> iterator_kcdz = list_ckdz.iterator();
			while (iterator_kcdz.hasNext()) {
				BigDecimal zje = new BigDecimal("0");
				String next = iterator_kcdz.next();

				OBJECT_T_MALL_ORDER t_MALL_ORDER = new OBJECT_T_MALL_ORDER();
				t_MALL_ORDER.setYh_id(user.getId());
				t_MALL_ORDER.setJdh(1);
				// 循环购物车，封装订单对象
				List<T_MALL_ORDER_INFO> list_order_info = new ArrayList<T_MALL_ORDER_INFO>();
				for (int i = 0; i < cart_list.size(); i++) {
					if (next.equals(cart_list.get(i).getKcdz())) {
						if (cart_list.get(i).getShfxz().equals("1")) {
							T_MALL_ORDER_INFO t_MALL_ORDER_INFO = new T_MALL_ORDER_INFO();
							t_MALL_ORDER_INFO.setGwch_id(cart_list.get(i).getId());
							t_MALL_ORDER_INFO.setShp_tp(cart_list.get(i).getShp_tp());
							t_MALL_ORDER_INFO.setSku_id(cart_list.get(i).getSku_id());
							t_MALL_ORDER_INFO.setSku_jg(cart_list.get(i).getSku_jg());
							t_MALL_ORDER_INFO.setSku_kcdz(cart_list.get(i).getKcdz());
							t_MALL_ORDER_INFO.setSku_mch(cart_list.get(i).getSku_mch());
							t_MALL_ORDER_INFO.setSku_shl(cart_list.get(i).getTjshl());
							zje = zje.add(new BigDecimal(cart_list.get(i).getHj() + ""));
							list_order_info.add(t_MALL_ORDER_INFO);
						}
					}
				}
				t_MALL_ORDER.setZje(zje);
				t_MALL_ORDER.setList_order_info(list_order_info);

				list_order.add(t_MALL_ORDER);
			}

			List<T_MALL_ADDRESS> list_address = addressServiceInf.get_addresses_by_user_id(user);

			map.put("list_address", list_address);
			map.put("list_order", list_order);
			session.setAttribute("list_order", list_order);
			return "sale_order";
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
