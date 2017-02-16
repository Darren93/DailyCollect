package com.darren.spring.utils.response.result;

import javax.xml.bind.annotation.XmlRootElement;

import com.darren.spring.utils.info.ResultMessage;
import com.darren.spring.utils.response.data.RawData;

import net.sf.json.JSONObject;



@XmlRootElement
public class JsonResult implements RawResult{

	private static final long serialVersionUID = 1L;

	/** 响应状态 */
	private ResultMessage resultMessage;

	/** 响应数据 */
	private RawData resultData;
	
	public JsonResult(ResultMessage resultMessage) {
		super();
		this.resultMessage = resultMessage;
	}
	
	public JsonResult(ResultMessage resultMessage, RawData resultData) {
		super();
		this.resultMessage = resultMessage;
		this.resultData = resultData;
	}

	public ResultMessage getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(ResultMessage resultMessage) {
		this.resultMessage = resultMessage;
	}

	public RawData getResultData() {
		return resultData;
	}

	public void setResultData(RawData resultData) {
		this.resultData = resultData;
	}

	public String toJson(){
		return JSONObject.fromObject(this).toString();
	}
}
