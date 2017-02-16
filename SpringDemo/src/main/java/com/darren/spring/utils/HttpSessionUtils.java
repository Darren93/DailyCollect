package com.darren.spring.utils;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.opensymphony.xwork2.ActionContext;

public class HttpSessionUtils {

	/**
	 * 安全获取session作用域的String类型参数。若是参数值是null，则返回空字符串[""]。
	 */
	public static final String getString(String paramName){
		//if(StringUtils.equals(paramName, "customerKey"))
		//	return "1-170852153";
//		
		String value = StringUtils.EMPTY;
		Object _value=((HttpSession)ActionContext.getContext().getSession()).getAttribute(paramName);
		try{
			return _value.toString();
		}catch(Exception e){
			return value;
		}
	}
	
	/**
	 * 安全获取session作用域的<T> T类型参数。若是参数值是null，则返回null。
	 */
	@SuppressWarnings("unchecked")
	public static final <T> T getObject(String paramName, Class<T> clazz){
		Object value =((HttpSession)ActionContext.getContext().getSession()).getAttribute(paramName);
		if(value != null)
			return (T) value;
		
		return null;
	}
	
	/**
	 * 安全获取request作用域的int类型参数。默认返回-1。
	 */
	public static int getInt(String paramName){
		Object value = ((HttpSession)ActionContext.getContext().getSession()).getAttribute(paramName);
		if(value != null){
			return org.apache.commons.lang3.math.NumberUtils.toInt(value.toString(), NumberUtils.INTEGER_MINUS_ONE);
		}
		
		return NumberUtils.INTEGER_MINUS_ONE;
	}
}
