package com.telrob;

/**
 * 
 * @author 张瑞志
 *
 * 创建时间:2017年8月8日 下午12:41:04
 *
 */
public class QueryType {
	private String validateMessagesShowId;
	
	
	private boolean status;
	
	private int httpstatus;
	private Data data;
	public String getValidateMessagesShowId() {
		return validateMessagesShowId;
	}
	public void setValidateMessagesShowId(String validateMessagesShowId) {
		this.validateMessagesShowId = validateMessagesShowId;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getHttpstatus() {
		return httpstatus;
	}
	public void setHttpstatus(int httpstatus) {
		this.httpstatus = httpstatus;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "QueryType [validateMessagesShowId=" + validateMessagesShowId
				+ ", status=" + status + ", httpstatus=" + httpstatus
				+ ", data=" + data + "]";
	}
	
	
}
