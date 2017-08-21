package com.atguigu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.mapper.IndexMapper;

@Service
public class IndexServiceImpl implements IndexServiceInf{
	@Autowired
	IndexMapper indexMapper;

	public int test() {
		return indexMapper.test();
	}


}
