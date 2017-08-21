package com.atguigu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDateUtil {

	public static String getMyDate() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");

		String d = sdf.format(new Date());

		return d;
	}
	
	
	public static Date getFlowDate(int i) {

		Calendar c = Calendar.getInstance();

		c.add(Calendar.DATE, i);

		Date time = c.getTime();

		return time;
	}

}
