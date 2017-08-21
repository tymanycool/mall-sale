package junit.test;

import java.util.HashMap;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.handler.WSHandlerConstants;

import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.service.UserServiceInf;
import com.atguigu.util.MyPasswordCallBack;

public class TestWSS4J {
	public static void main(String[] args) {
		JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();

		jaxWsProxyFactoryBean.setAddress("http://localhost:28080/mall-user-service/user?wsdl");

		jaxWsProxyFactoryBean.setServiceClass(UserServiceInf.class);

		HashMap<String, Object> hashMap = new HashMap<String, Object>();

		hashMap.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
		hashMap.put(WSHandlerConstants.PASSWORD_TYPE, "PasswordText");
		hashMap.put(WSHandlerConstants.USER, "user");
		hashMap.put(WSHandlerConstants.PW_CALLBACK_CLASS, MyPasswordCallBack.class.getName());

		WSS4JOutInterceptor wss4jOutInterceptor = new WSS4JOutInterceptor(hashMap);

		jaxWsProxyFactoryBean.getOutInterceptors().add(wss4jOutInterceptor);

		UserServiceInf create = (UserServiceInf) jaxWsProxyFactoryBean.create();

		T_MALL_USER_ACCOUNT user = new T_MALL_USER_ACCOUNT();
		user.setYh_mch("tianyao");
		user.setYh_mm("123");
		T_MALL_USER_ACCOUNT query_user = create.get_user_account(user );

		System.out.println(query_user);
	}
}
