package com.atguigu.bean;

import java.io.Serializable;
import java.util.List;

public class OBJECT_T_MALL_ATTR extends T_MALL_ATTR implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<T_MALL_VALUE> list_value;

	public List<T_MALL_VALUE> getList_value() {
		return list_value;
	}

	public void setList_value(List<T_MALL_VALUE> list_value) {
		this.list_value = list_value;
	}

}
