package com.atguigu.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.atguigu.bean.OBJECT_T_MALL_SKU;

import redis.clients.jedis.Jedis;

public class MyRedisUtil {

	public static int setMyObjSku(String key, List<OBJECT_T_MALL_SKU> list_sku) {
		Jedis jedis = JedisPoolUtils.getJedis();

		// 循环sku集合，转成json放入redis的zset
		for (int i = 0; i < list_sku.size(); i++) {
			String get_json = MyJsonUtil.get_json(list_sku.get(i));
			jedis.zadd(key, i, get_json);
		}

		return list_sku.size();

	}

	public static List<OBJECT_T_MALL_SKU> getMyObjSku(int class_2_id) {

		List<OBJECT_T_MALL_SKU> list_sku = new ArrayList<OBJECT_T_MALL_SKU>();

		Jedis jedis = JedisPoolUtils.getJedis();

		String key = "class_2_" + class_2_id;

		Set<String> zrange = jedis.zrange(key, 0, -1);

		Iterator<String> iterator = zrange.iterator();

		while (iterator.hasNext()) {
			String next = iterator.next();

			OBJECT_T_MALL_SKU sku = MyJsonUtil.get_obj(next,OBJECT_T_MALL_SKU.class);

			list_sku.add(sku);
		}
		return list_sku;
	}

}
