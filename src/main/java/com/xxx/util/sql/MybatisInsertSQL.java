package com.xxx.util.sql;

import java.io.InputStream;

public class MybatisInsertSQL {
	private final String configLocation="com/xxx/util/sql/MybatisInsertSQL.ini";
	
	public static void main(String[] args) {
		new MybatisInsertSQL().generate();
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
			
			result += "insert into table_name\r\n";
			result += "<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\r\n";
			for (String string : words) {
				if(string.equals("")||string==null)
					continue;
				result += "    <if test=\""+string+"!=null\">"+string+",</if>\r\n";
			}
			result += "</trim>\r\n";
			result += "values\r\n";
			result += "<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\r\n";
			for (String string : words) {
				if(string.equals("")||string==null)
					continue;
				result += "    <if test=\""+string+"!=null\">#{"+string+"},</if>\r\n";
			}
			result += "</trim>\r\n";
			System.out.println(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
}
