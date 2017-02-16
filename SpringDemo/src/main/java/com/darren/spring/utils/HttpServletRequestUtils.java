package com.darren.spring.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.xwork.StringUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.opensymphony.xwork2.ActionContext;

public class HttpServletRequestUtils {
	public static void continued(HttpServletRequest request)
	  {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Enumeration iterate = request.getParameterNames();
	    String param = null;
	    while (iterate.hasMoreElements()) {
	      param = (String)iterate.nextElement();
	      request.setAttribute(param, request.getParameter(param));
	    }
	  }

	  public static String getInput(HttpServletRequest request) {
	    try {
	     request.setCharacterEncoding("utf-8");
	      BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
	      String line = null;
	      StringBuilder sb = new StringBuilder();
	      while ((line = br.readLine()) != null) {
	        sb.append(line);
	      }
	      return sb.toString();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }

	    return null;
	  }

	  public static Map<String, Object> transfer(HttpServletRequest request)
	  {
		  Map map= new HashMap();
		  try {
			request.setCharacterEncoding("utf-8");
			Enumeration iterate = request.getParameterNames();
			String param = null;
			while (iterate.hasMoreElements()) {
				param = (String)iterate.nextElement();
				map.put(param, request.getParameter(param));
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return map;
	  }

	  public static String getString(String name,HttpServletRequest request)
	  {
		  try {
			request.setCharacterEncoding("utf-8");
			String value = request.getParameter(name);
			if (StringUtils.isNotEmpty(value)) {
			  return value;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return "";
	  }

	  public static int getInt(String name,HttpServletRequest request)
	  {
		  try {
			request.setCharacterEncoding("utf-8");
			String value = request.getParameter(name);
			if ((StringUtils.isNotEmpty(value)) && (NumberUtils.isNumber(value))) {
			  return Integer.valueOf(value).intValue();
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return NumberUtils.INTEGER_MINUS_ONE.intValue();
	  }

	  public static Long getLong(String name,HttpServletRequest request)
	  {
		  try {
			request.setCharacterEncoding("utf-8");
			String value = request.getParameter(name);
			if ((StringUtils.isNotEmpty(value)) && (NumberUtils.isNumber(value))) {
			  return Long.valueOf(value);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return NumberUtils.LONG_ZERO;
	  }

	  public static boolean getBoolean(String name,HttpServletRequest request)
	  {
		  try {
			request.setCharacterEncoding("utf-8");
			String value = request.getParameter(name);
			return BooleanUtils.toBoolean(value);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return BooleanUtils.toBoolean("0");
		}
	  }

	  public static String getHeadString(String name,HttpServletRequest request)
	  {
		  try {
			request.setCharacterEncoding("utf-8");
			String value = request.getHeader(name);
			if (StringUtils.isNotEmpty(value)) {
			  return value;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return "";
	  }

	  public static final <T> T populate(Class<T> clazz,HttpServletRequest request)
	  {
	    Object t = null;
	    try {
	      t = clazz.newInstance();

	      Map map = transfer(request);

	      BeanUtils.populate(t, map);
	    } catch (InstantiationException e) {
	      e.printStackTrace();
	    } catch (IllegalAccessException e) {
	      e.printStackTrace();
	    } catch (InvocationTargetException e) {
	      e.printStackTrace();
	    }

	    return (T) t;
	  }

	  public static void main(String[] args) {
	    String s = null;
	  }
}
