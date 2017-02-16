package com.darren.spring.utils.response;

import com.darren.spring.utils.info.ResponseCode;
import com.darren.spring.utils.info.ResultMessage;
import com.darren.spring.utils.info.codes.CommonCode;
import com.darren.spring.utils.response.data.RawData;
import com.darren.spring.utils.response.data.ResultData;
import com.darren.spring.utils.response.result.JsonResult;

public abstract class JsonResultProvider {

	/** 操作成功，无数据返回。 */
	public static JsonResult buildSuccessResult() {

		return new JsonResult(new ResultMessage(CommonCode.SUCCESS));
	}
	
	/** 操作成功，并能返回正常数据。 */
	public static JsonResult buildSuccessResult(RawData resultData) {

		return new JsonResult(new ResultMessage(CommonCode.SUCCESS), resultData);
	}
	
	/** 操作正常，无数据返回 */
	public static JsonResult buildNormalResult() {

		return new JsonResult(new ResultMessage(CommonCode.NORMAL));
	}
	
	/** 操作正常，并返回数据。 */
	public static JsonResult buildNormalResult(RawData resultData) {

		return new JsonResult(new ResultMessage(CommonCode.NORMAL), resultData);
	}

	/** 操作失败，并返回数据 */
	public static JsonResult buildFailureResult() {

		return new JsonResult(new ResultMessage(CommonCode.ERROR));
	}
	
	/** 操作失败，并返回数据 */
	public static JsonResult buildFailureResult(RawData resultData) {

		return new JsonResult(new ResultMessage(CommonCode.ERROR), resultData);
	}

	/** 创建并返回JsonResult */
	public static JsonResult buildJsonResult(ResultMessage message, RawData resultData) {

		return new JsonResult(message, resultData);
	}
	
	/** 创建并返回JsonResult */
	public static JsonResult buildCodeResult(ResponseCode code){
		return new JsonResult(new ResultMessage(code));
	}

	/** 创建并返回JsonResult */
	public static JsonResult buildCodeResult(ResponseCode code, RawData resultData) {

		return new JsonResult(new ResultMessage(code), resultData);
	}

	/** 创建并返回JsonResult */
	public static JsonResult buildCodeResult(ResponseCode code, Object o) {
		ResultData resultData = new ResultData();
		resultData.putItem("result", o);
		return new JsonResult(new ResultMessage(code), resultData);
	}
}
