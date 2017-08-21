package com.atguigu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_FLOW;
import com.atguigu.bean.T_MALL_ORDER_INFO;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.mapper.OrderMapper;
import com.atguigu.util.MyDateUtil;

@Service
public class OrderServiceImpl implements OrderServiceInf {
	
	@Autowired
	OrderMapper orderMapper;

	@Override
	public List<Integer> save_order(T_MALL_USER_ACCOUNT user, List<OBJECT_T_MALL_ORDER> list_order, T_MALL_ADDRESS address) {
		//System.out.println("保存订单");
		// 循环保存订单
		List<Integer> list_cart_id = new ArrayList<Integer>();
		for (int i = 0; i < list_order.size(); i++) {
			OBJECT_T_MALL_ORDER order = list_order.get(i);
			// 保存一个订单
			// shhr
			// yjsdshj
			// dzh_id
			// dzh_mch
			order.setShhr(address.getShjr());
			order.setDzh_id(address.getId());
			order.setDzh_mch(address.getYh_dz());
			orderMapper.insert_order(order);

			// 批量保存订单信息
			// dd_id
			// 批量插入订单信息表
			for (T_MALL_ORDER_INFO order_info: order.getList_order_info()) {
				order_info.setDd_id(order.getId());
				list_cart_id.add(order_info.getGwch_id());
			}
			orderMapper.insert_order_infos(order.getList_order_info());

			// 生成物流信息
			// psfsh
			// yh_id
			// dd_id
			// mdd
			T_MALL_FLOW t_MALL_FLOW = new T_MALL_FLOW();
			t_MALL_FLOW.setPsfsh("硅谷快递");
			t_MALL_FLOW.setYh_id(user.getId());
			t_MALL_FLOW.setDd_id(list_order.get(i).getId());
			t_MALL_FLOW.setMqdd("尚未出库");
			t_MALL_FLOW.setMdd(address.getYh_dz());
			orderMapper.insert_flow(t_MALL_FLOW);

			// 删除购物车数据
			orderMapper.delete_cart(list_cart_id);
			
		}
		return list_cart_id;

	}

	@Override
	public void order_pay(List<OBJECT_T_MALL_ORDER> list_order) {

		for (int i = 0; i < list_order.size(); i++) {
			// 修改订单
			list_order.get(i).setJdh(2);
			list_order.get(i).setYjsdshj(MyDateUtil.getFlowDate(3));// 计算送达时间
			orderMapper.update_order(list_order.get(i));

			// 修改物流
			// psshj
			// psmsh
			// ywy
			// lxfsh
			T_MALL_FLOW t_MALL_FLOW = new T_MALL_FLOW();
			t_MALL_FLOW.setPsshj(MyDateUtil.getFlowDate(1));// 计算配送时间
			t_MALL_FLOW.setPsmsh("商品已出库，准备发往下一个站点");
			t_MALL_FLOW.setYwy("老佟");
			t_MALL_FLOW.setLxfsh("123123123123");
			t_MALL_FLOW.setDd_id(list_order.get(i).getId());
			orderMapper.update_flow(t_MALL_FLOW);

			// 修改库存
			for (int j = 0; j < list_order.get(i).getList_order_info().size(); j++) {
				// 判断库存表中的需要购买的商品数量是否大于购买数量
				boolean b = if_can_buy(list_order.get(i).getList_order_info().get(j));
				if (b) {
					orderMapper.update_sku_kc(list_order.get(i).getList_order_info().get(j));
				} else {
					// 超卖，抛出自定义异常
				}
			}
		}

	}
	
	private boolean if_can_buy(T_MALL_ORDER_INFO t_MALL_ORDER_INFO) {
		boolean b = false;
		// select * from t_mall_sku where id = 1 for update 行锁查询语句查询库存数量
		int i = orderMapper.select_sku_kc(t_MALL_ORDER_INFO);
		if (i > 0) {
			b = true;
		}
		return b;
	}

}
