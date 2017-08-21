package com.atguigu.util;



import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

public class MyPasswordCallBack implements CallbackHandler {

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		WSPasswordCallback wscb = (WSPasswordCallback) callbacks[0];
		//wscb.get
		wscb.setIdentifier("cxf");
		//MD5加密
		String pwd = MD5Util.md5("wss4j" +MyDateUtil.getMyDate());
		wscb.setPassword(pwd);
		//wscb.setPassword("wss4j");
	}

}
