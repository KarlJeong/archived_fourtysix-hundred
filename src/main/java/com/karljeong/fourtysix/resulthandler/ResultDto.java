package com.karljeong.fourtysix.resulthandler;

public class ResultDto {

	public enum ResultCodeEnum {
		SUCCESS, SUCCESS_REDIRECT, FAIL, FAIL_REDIRECT;
	}

	private ResultCodeEnum resultCd;
	private String resultMsg;
	private Object data;
	private String linkUrl;

	public ResultDto() {
		resultCd = ResultCodeEnum.SUCCESS;
		resultMsg = "SUCCESS";
	}

	public ResultCodeEnum getResultCd() {
		return resultCd;
	}

	public void setResultCd(ResultCodeEnum resultCd) {
		this.resultCd = resultCd;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

}
