package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;

public interface ShoppingCartServiceInf {

	public void add_cart(T_MALL_SHOPPINGCAR cart);

	public void update_cart(T_MALL_SHOPPINGCAR cart);

	public List<T_MALL_SHOPPINGCAR> get_list_cart_by_user_id(int yh_id);
	
	public void update_cart_2(T_MALL_SHOPPINGCAR cart);
	
	public void delete_cart(T_MALL_SHOPPINGCAR cart);

}
