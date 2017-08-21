package com.atguigu.bean;

import java.io.Serializable;
import java.util.List;

public class OBJECT_T_MALL_ORDER extends T_MALL_ORDER implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<T_MALL_ORDER_INFO> list_order_info;

	public List<T_MALL_ORDER_INFO> getList_order_info() {
		return list_order_info;
	}

	public void setList_order_info(List<T_MALL_ORDER_INFO> list_order_info) {
		this.list_order_info = list_order_info;
	}

}
