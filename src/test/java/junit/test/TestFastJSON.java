package junit.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.bean.T_MALL_SHOPPINGCAR;

class Person{
	private int age;
	private String name;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}
	
	
}


public class TestFastJSON {
	
	@Test
	public void test2()throws Exception{
		JSONObject parseObject = JSON.parseObject(null);
		System.out.println(parseObject);//null
		parseObject = JSON.parseObject("");
		System.out.println(parseObject);//null
	}
	
	@Test
	public void test1() {
		Person person = new Person();
		person.setAge(23);
		person.setName("tianyao");
		ArrayList<Person> arrayList = new ArrayList<Person>();
		arrayList.add(person);
		person = new Person();
		person.setAge(24);
		person.setName("田耀");
		arrayList.add(person);
		//集合转化成json
		String jsonString = JSON.toJSONString(arrayList);
		System.out.println(jsonString);
		//json转化成集合
		List<Person> parseArray = JSON.parseArray(jsonString,Person.class);
		System.out.println(parseArray);
		
		
	}
	@Test
	public void test() {
		Person person = new Person();
		person.setAge(23);
		person.setName("tianyao");
		//java对象转化json
		String jsonString = JSON.toJSONString(person);
		System.out.println(jsonString);
		//json转换层java对象
		Person parseObject = JSON.parseObject(jsonString, Person.class);
		System.out.println(parseObject);
	}
	
	
}
