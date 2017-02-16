/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darren.spring.utils.info.codes;

import com.darren.spring.utils.info.ResponseCode;

/**
 *对外接口服务返回数据中的通用的返回码
 * @author tangjun
 */
public enum DataCode implements ResponseCode {

    /** 信息传递不成功，数据传输不完整 **/
    DATASTREAM_ERROR((short) 1, "SOA001", "信息传递不成功，数据传输不完整"),
    /** 网络异常 **/
    NETWORK_EXCEPTION((short) 1, "SOA002", "网络异常"),
    /** 文件中任意一行取值不可以为空 **/
    RECORD_NOTNULL((short) 1, "SOA003", "文件中任意一行取值不可以为空"),  
    /** 某一表记录数与相应数据行数不匹配问题 **/
    RECORD_MATCH_ERROR((short) 1, "SOA004", "某一表记录数与相应数据行数不匹配问题"),
    /**数据重复**/
    DATA_DUPLICATOIN((short) 1, "SOA005", "数据重复"),
    /** 重复上传55码,58码 **/
    CC_ECHO_ERROR((short) 1, "SOA006", "重复上传55码,58码"),
    /** 塑料袋纪录处理异常 **/
    CC_POCKET_ERROR((short) 1, "SOA007", "塑料袋纪录处理异常"),
    /** 上传彩码状态异常 **/
    CC_STATUS_ERROR((short) 1, "SOA008", "上传彩码状态异常"),
    /** 修改库存异常 **/
    STOCK_ERROR((short) 1, "SOA009", "修改库存异常"),
    /** 数据不存在**/
    DATA_NOEXSIST((short) 1, "SOA010", "数据不存在"),
    /** 找不到可操数据**/
    DATA_NOFIND((short) 1, "SOA010", "找不到可操数据"),
    /** 收银编号已被使用 **/
    DATA_USEED((short) 1, "SOA110", "收银编号已被使用")
    ;
    
    private Short status;
    private String code, desc;

    DataCode(Short status, String code, String desc) {
        this.status = status;
        this.code = code;
        this.desc = desc;
    }

    public String value() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }
    
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Short getStatus() {
		return status;
	}

	public String getCode() {
		return code;
	}

    
    
}
