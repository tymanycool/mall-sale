package com.atguigu.util;


import java.util.HashMap;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.factory.FactoryBean;

public class MyWsInfFactoryBean<T> implements FactoryBean<T>{
	private Class<T> type;
	
	private String  url;
	
	
	
	public Class<T> getType() {
		return type;
	}

	public void setType(Class<T> type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public T getObject() throws Exception {
		JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
		jaxWsProxyFactoryBean.setAddress(url);
		jaxWsProxyFactoryBean.setServiceClass(type);
		if(type.getClass().getSimpleName().equals("IndexServiceInf"));{
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
			hashMap.put(WSHandlerConstants.PASSWORD_TYPE, "PasswordText");
			hashMap.put(WSHandlerConstants.USER, "user");
			hashMap.put(WSHandlerConstants.MUST_UNDERSTAND, "0"); //???
			hashMap.put(WSHandlerConstants.PW_CALLBACK_CLASS, MyPasswordCallBack.class.getName());
			WSS4JOutInterceptor wss4jOutInterceptor = new WSS4JOutInterceptor(hashMap);
			jaxWsProxyFactoryBean.getOutInterceptors().add(wss4jOutInterceptor);
		}
		
		T t = (T)jaxWsProxyFactoryBean.create();
		return t;
	}

	@Override
	public Class<?> getObjectType() {
		return type;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
