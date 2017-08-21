package com.atguigu.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.mapper.ShoppingCartMapper;

@Service
public class ShoppingCartServiceImp implements ShoppingCartServiceInf {

	@Autowired
	ShoppingCartMapper shoppingCartMapper;

	public void add_cart(T_MALL_SHOPPINGCAR cart) {
		shoppingCartMapper.insert_cart(cart);

	}

	public void update_cart(T_MALL_SHOPPINGCAR cart) {
		shoppingCartMapper.update_cart(cart);

	}

	@Override
	public List<T_MALL_SHOPPINGCAR> get_list_cart_by_user_id(int yh_id) {
		return shoppingCartMapper.select_list_cart_by_user_id(yh_id);
	}

	public void update_cart_2(T_MALL_SHOPPINGCAR cart) {
		
		
		
		shoppingCartMapper.update_cart_2(cart);
	}

	public void delete_cart(T_MALL_SHOPPINGCAR cart) {
		shoppingCartMapper.delete_cart(cart);
	}

}
