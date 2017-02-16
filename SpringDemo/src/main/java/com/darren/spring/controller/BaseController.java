package com.darren.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.darren.spring.domain.Pagination;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class BaseController {
	public Pagination getPagination(HttpServletRequest request) {
		Pagination page = new Pagination();
		String rows = request.getParameter("rows");
		String pageNum1 = request.getParameter("page");
		String pageNum2 = request.getParameter("pageNo");
		if (rows == null || StringUtils.isBlank(rows) || rows.equals("NaN")) {
			rows = "30";
		}
		if (pageNum1 == null || StringUtils.isBlank(pageNum1)) {
			pageNum1 = "1";
		}
		int pageNo = Integer.parseInt(pageNum1);
		if (pageNum2 != null && StringUtils.isNotBlank(pageNum2)) {
			pageNo = Integer.parseInt(pageNum2);
		}
		request.setAttribute("pageNo", pageNo);
		int pageSize = Integer.parseInt(rows);

		// 设置起始行
		page.setStartRow(pageSize * (pageNo - 1));
		// 设置页面大小
		page.setLimitRow(pageSize);

		return page;
	}
	
	public void ajaxResponse(JSONObject datas,HttpServletResponse response)
    {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter  out=null;
        try
        {
        	out=response.getWriter();
            if (null != datas || !datas.keySet().isEmpty())
            {
                // 打印返回结果
                // logger.info(CommonConstants.Log.PRINT_RESULT + "datas=" +
                // datas);
                response.getWriter().print(datas);
            }
            else
            {
                response.getWriter().print("数据为空");
            }
        }
        catch (IOException e)
        {
        	System.out.println("输出异常。。");
        }
    }
	
	public void ajaxResponse(JSONArray datas,HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		try {
			if (null != datas) {
				// 打印返回结果
				// logger.info(CommonConstants.Log.PRINT_RESULT + "datas=" +
				// datas);
				response.getWriter().print(datas);
			} else {
				// 打印返回结果
				// logger.info(CommonConstants.Log.PRINT_RESULT +
				// "[datas=null]");
				response.getWriter().print("数据为空");
			}
		} catch (IOException e) {
			// 打印结果错误
			// logger.error(CommonConstants.Log.EXCEPTION + "Response输出异常",
			// e);
			System.out.println("Response输出异常");
		}
	}
	
	public void ajaxResponseJson(String json,HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			System.out.println("Response输出异常");
		}
	}
}
