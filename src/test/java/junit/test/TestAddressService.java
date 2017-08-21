package junit.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.service.AddressServiceInf;

public class TestAddressService {
	@Test
	public void test(){
		ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
		AddressServiceInf bean = ioc.getBean(AddressServiceInf.class);
		T_MALL_USER_ACCOUNT user = new T_MALL_USER_ACCOUNT();
		user.setYh_mch("tianyao");
		user.setId(1);
		List<T_MALL_ADDRESS> get_addresses_by_user_id = bean.get_addresses_by_user_id(user );
		System.out.println(get_addresses_by_user_id);
		System.out.println(bean);
	}
}
