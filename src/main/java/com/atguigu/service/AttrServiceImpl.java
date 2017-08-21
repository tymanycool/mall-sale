package com.atguigu.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_ATTR;
import com.atguigu.bean.T_MALL_VALUE;
import com.atguigu.mapper.AttrMapper;


@Service
public class AttrServiceImpl implements AttrServiceInf{
	/**
	* Logger for this class
	*/
	private static final Logger logger = Logger.getLogger(AttrServiceImpl.class);
	
	@Autowired
	AttrMapper attrMapper;

	public List<OBJECT_T_MALL_ATTR> get_attr_by_class_2_id(String class_2_id) {
		return attrMapper.select_attr_by_class_2_id(class_2_id);
	}

	
	
	
}
