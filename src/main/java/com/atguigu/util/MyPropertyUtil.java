package com.atguigu.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyPropertyUtil {
	/**
	 * 获取properties文件的key应对的值
	 * @param fileName
	 * @param key
	 * @return
	 */
	public static String getProperty(String fileName,String key) {

		Properties properties = new Properties();

		InputStream resourceAsStream = MyPropertyUtil.class.getClassLoader()
				.getResourceAsStream(fileName);
		try {
			properties.load(resourceAsStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String value = properties.getProperty(key);
		return value;
	}
	
	public static void main(String[] args) {
		String value = MyPropertyUtil.getProperty("uploadPath.properties","windows_path");
		System.out.println(value);
	}

}
