package com.xxx.util.sql;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MybatisUpdateSQL {
	private final String configLocation="com/xxx/util/sql/MybatisUpdateSQL.ini";
	
	public static void main(String[] args) {
		new MybatisUpdateSQL().generate();
	}
	
	
	public String generate(){
		String result = "";
		try {
			InputStream is = this.getClass().getClassLoader().getResourceAsStream(configLocation);
			String content = StreamUtils.streamToString(is);
			String[] all = content.split("\r\n");
			String real_string = "";
			for (String string : all) {
				if(!string.startsWith("#")){
					real_string += string;
				}
			}
			String[] words = real_string.split("[,\\s]");
			
			result += "update table_name\r\n";
			result += "<set>\r\n";
			for (String string : words) {
				if(string.equals("")||string==null)
					continue;
				result += "    <if test=\""+string+"!=null\">"+string+"=#{"+string+"},</if>\r\n";
			}
			result += "</set>\r\n";
			result += "<where>\r\n";
			result += "    <if test=\""+"id"+"!=null\">"+"id"+"=#{"+"id"+"},</if>\r\n";
			result += "</where>\r\n";
			System.out.println(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
}
