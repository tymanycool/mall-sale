package com.xxx.util.sql;





import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 此类用于提供：
 * 将InputStream转换成String或byte[]
 * @author tianyao
 *
 */
public class StreamUtils {
	/**
	 * 将读取字节流转换成string
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public static String streamToString(InputStream is) throws Exception{
		BufferedReader reader=new BufferedReader(new InputStreamReader(is));
		StringBuilder builder = new StringBuilder();
		String line;
		while((line=reader.readLine())!=null){
			builder.append(line+"\r\n");
		}
		return builder.toString();
	}
	/**
	 * 将读取字节流转换成byte[]
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public static byte[] streamToByteArray(InputStream is) throws Exception{
		ByteArrayOutputStream  baos =new ByteArrayOutputStream();
		byte[] b=new byte[1024];
		int len;
		BufferedInputStream bis=new BufferedInputStream(is);
		while((len=bis.read(b))!=-1){
			baos.write(b, 0, len);
		}
		return baos.toByteArray();
	}

}
