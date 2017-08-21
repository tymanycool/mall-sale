package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.MODEL_DETAIL_T_MALL_SKU;
import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;

public interface SearchServiceInf {

	List<OBJECT_T_MALL_SKU> get_sku_by_class_2(String class_2_id);

	List<OBJECT_T_MALL_SKU> get_sku_by_attr(String order, int class_2_id, List<T_MALL_SKU_ATTR_VALUE> list_av);

	MODEL_DETAIL_T_MALL_SKU get_sku_detail_by_id(int sku_id);

	List<T_MALL_SKU> get_sku_by_spu(int spu_id);

}
