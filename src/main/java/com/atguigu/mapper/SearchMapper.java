package com.atguigu.mapper;

import java.util.HashMap;
import java.util.List;

import com.atguigu.bean.MODEL_DETAIL_T_MALL_SKU;
import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU;

public interface SearchMapper {

	List<OBJECT_T_MALL_SKU> select_sku_by_class_2(Integer class_2_id);
	
	List<OBJECT_T_MALL_SKU> select_sku_by_attr(HashMap<String,Object> map);

	MODEL_DETAIL_T_MALL_SKU select_sku_detail_by_id(int sku_id);

	List<T_MALL_SKU> select_sku_by_spu(int spu_id);

}
