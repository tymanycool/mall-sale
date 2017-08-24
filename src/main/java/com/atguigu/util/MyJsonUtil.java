package com.atguigu.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.sf.json.JSONArray;

public class MyJsonUtil {

	public static void main(String[] args) throws UnsupportedEncodingException {
		ArrayList<T_MALL_SHOPPINGCAR> arrayList = new ArrayList<T_MALL_SHOPPINGCAR>();

		for (int i = 0; i < 3; i++) {
			T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR = new T_MALL_SHOPPINGCAR();
			t_MALL_SHOPPINGCAR.setSku_mch("商品" + i);
			t_MALL_SHOPPINGCAR.setSku_jg(i * 1000);
			t_MALL_SHOPPINGCAR.setTjshl(i);
			arrayList.add(t_MALL_SHOPPINGCAR);
		}

		Gson gson = new Gson();

		// 购物车集合对象
		String json = gson.toJson(arrayList);

		json_to_list(json, T_MALL_SHOPPINGCAR.class);

		TypeToken<ArrayList<T_MALL_SHOPPINGCAR>> type = new TypeToken<ArrayList<T_MALL_SHOPPINGCAR>>() {
		};

		List<T_MALL_SHOPPINGCAR> fromJson2 = (List<T_MALL_SHOPPINGCAR>) gson.fromJson(json, type.getType());

		// 一个购物车对象
		T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR = new T_MALL_SHOPPINGCAR();
		t_MALL_SHOPPINGCAR.setSku_mch("商品" + 10);
		t_MALL_SHOPPINGCAR.setSku_jg(10 * 1000);
		t_MALL_SHOPPINGCAR.setTjshl(10);
		String json2 = gson.toJson(t_MALL_SHOPPINGCAR);
		System.out.println(json2);
		T_MALL_SHOPPINGCAR fromJson = gson.fromJson(json2, T_MALL_SHOPPINGCAR.class);
		System.out.println(fromJson);

	}

	public static <T> List<T> json_to_list(String json, Class<T> t) {
		if (StringUtils.isNotBlank(json)) {
			try {
				json = URLDecoder.decode(json, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			JSONArray fromObject4 = JSONArray.fromObject(json);
	
			List<T> collection = (List<T>) JSONArray.toCollection(fromObject4, t);
			return collection;
		}
		return null;
	}

	public static <T> String list_to_json(T t) {

		Gson gson = new Gson();

		String json = gson.toJson(t);

		String encode = "";
		try {
			encode = URLEncoder.encode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return encode;

	}
	
	public static<T> String get_json(T t) {
		String json = new Gson().toJson(t);
		try {
			json = URLEncoder.encode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public static <T> T get_obj(String json,Class<T> type) {
		try {
			json = URLDecoder.decode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		T fromJson = new Gson().fromJson(json, type);
		return fromJson;
	}


}
