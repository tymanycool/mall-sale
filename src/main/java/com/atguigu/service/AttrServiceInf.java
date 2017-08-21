package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_SKU;

public interface AttrServiceInf {
	
	public List<OBJECT_T_MALL_ATTR> get_attr_by_class_2_id(String class_2_id);

	
}
