package com.atguigu.service;

import javax.jws.WebService;

import com.atguigu.bean.T_MALL_USER_ACCOUNT;


@WebService
public interface UserServiceInf {
	
	T_MALL_USER_ACCOUNT get_user_account(T_MALL_USER_ACCOUNT user) ;
	
	boolean regist(T_MALL_USER_ACCOUNT user) ;
}
