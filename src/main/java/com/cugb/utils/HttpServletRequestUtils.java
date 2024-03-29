package com.cugb.utils;

import javax.servlet.http.HttpServletRequest;


public class HttpServletRequestUtils {

	public static int getInt(HttpServletRequest request,String key)
	{
		try {
			return Integer.decode(request.getParameter(key));
		}catch(Exception e)
		{
			return -1;
		}
	}
	
	public static double getDouble(HttpServletRequest request,String key)
	{
		try {
			return Double.valueOf(request.getParameter(key));
		} catch (Exception e) {
			// TODO: handle exception
			return -1;
		}
	}
	
	public static boolean getBoolean(HttpServletRequest request,String key)
	{
		try {
			return Boolean.valueOf(request.getParameter(key));
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public static long getLong(HttpServletRequest request,String key)
	{
		try {
			return Long.valueOf(request.getParameter(key));
					
		} catch (Exception e) {
			// TODO: handle exception
			return -1L;
		}
	}
	
	public static String getString(HttpServletRequest request,String key)
	{
		try {
			String result  = request.getParameter(key);
			return result==null?null:result.trim();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("参数出错");
		}
	}
}
