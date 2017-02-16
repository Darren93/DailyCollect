package com.darren.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.darren.spring.utils.HttpServletRequestUtils;
import com.darren.spring.utils.info.codes.CommonCode;
import com.darren.spring.utils.response.JsonResultProvider;
import com.darren.spring.utils.response.result.JsonResult;
import com.darren.spring.vo.PersonVO;

@Controller
public class TestController extends BaseController {
	@RequestMapping("/test")
	public String helloWorld(){
		return "test";
	}
	@RequestMapping("/testAjax")
	public void testAjax(HttpServletRequest request,HttpServletResponse response){
		PersonVO pVo=HttpServletRequestUtils.populate(PersonVO.class,request);
		JsonResult jsonResult=JsonResultProvider.buildCodeResult(CommonCode.SUCCESS,pVo);
		System.out.println(pVo.getAddress());
		ajaxResponseJson(jsonResult.toJson(), response);
	}
}
