package com.atguigu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.MODEL_DETAIL_T_MALL_SKU;
import com.atguigu.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.service.AttrServiceInf;
import com.atguigu.service.SearchServiceInf;

@Controller
public class SearchController {
	
	@Autowired
	SearchServiceInf searchServiceInf;
	
	@Autowired
	AttrServiceInf attrServiceInf;
	
	@RequestMapping("/goto_sku_detail")
	public String goto_sku_detail(int sku_id,Integer spu_id, ModelMap map) {
		
		List<T_MALL_SKU> list_sku = searchServiceInf.get_sku_by_spu(spu_id);
		
		
		//System.out.println(list_sku);

		MODEL_DETAIL_T_MALL_SKU obj_sku = searchServiceInf.get_sku_detail_by_id(sku_id);
		map.put("obj_sku", obj_sku);
		map.put("list_sku", list_sku);
		return "sale_seach_sku_detail";
	}
	
	@RequestMapping("/get_sku_by_class_2")
	public String get_sku_by_class_2(String class_2_id,String class_2_name,ModelMap map){
		List<OBJECT_T_MALL_SKU> list_sku = searchServiceInf.get_sku_by_class_2(class_2_id);

		List<OBJECT_T_MALL_ATTR> list_attr = attrServiceInf.get_attr_by_class_2_id(class_2_id);

		map.put("list_attr", list_attr);
		map.put("list_sku", list_sku);
		map.put("class_2_id", class_2_id);
		map.put("class_2_name", class_2_name);
		return "sale_seach";
		
	}
	
	@RequestMapping("/get_sku_by_attr")
	public String get_sku_by_attr(String order, int class_2_id, MODEL_T_MALL_SKU_ATTR_VALUE list_av, ModelMap map) {

		List<OBJECT_T_MALL_SKU> list_sku = searchServiceInf.get_sku_by_attr(order, class_2_id, list_av.getList_av());
		map.put("list_sku", list_sku);
		return "sale_seach_list_inner";
	}
	
	
}
