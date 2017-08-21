package com.atguigu.util.mybatis;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class MyBatisUtils{
	
	//MyBatis配置文件的位置
	private static final String configLocation = "com/atguigu/util/mybatis/mybatis-config.xml";
	
	
	public <T> MyBatisUtils(RunTest<T> runTest) {
		Class<?> cla = runTest.getClass();
		// 通过子类获取父类 TestMyBatisUtil<User> getGenericSuperclass
		// 获取的父类其实是一个 有参数的类型ParameterizedType
		Type[] genericInterfaces = cla.getGenericInterfaces();
		ParameterizedType pt[] = new ParameterizedType[genericInterfaces.length];
		for (int i = 0;i<pt.length;i++) {
			pt[i]=(ParameterizedType) genericInterfaces[i];
			//System.out.println(pt[i]);
		}
		//System.out.println("父类的真实类型是：" + pt[0]);
		// 通过 获取的父类 获取 参数 <T>
		Type[] types = pt[0].getActualTypeArguments();
		
		// 只有一个参数 索引为0就可以读取到 T
		Class<?> type = (Class<?>) types[0];
		//System.out.println("泛型类型：" + type);
		
		InputStream rs = MyBatisUtils.class.getClassLoader().getResourceAsStream(configLocation);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(rs);
		SqlSession session = sessionFactory.openSession();

		try {
			@SuppressWarnings("unchecked")
			T t = (T) session.getMapper(type);
			runTest.test(t);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
