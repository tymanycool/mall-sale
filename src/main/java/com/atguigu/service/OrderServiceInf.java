package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;

public interface OrderServiceInf {

	 List<Integer> save_order(T_MALL_USER_ACCOUNT user, List<OBJECT_T_MALL_ORDER> list_order, T_MALL_ADDRESS address);

	void order_pay(List<OBJECT_T_MALL_ORDER> list_order);

}
