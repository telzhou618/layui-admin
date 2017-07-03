package org.alex.admin.core.log;

import java.util.Date;

/**
 * 日志对象
 * Created by Gaojun.Zhou 2017年6月20日
 */
public class LogBean {

	/**
	 * 日志标题
	 */
	private String logTitle;
	/**
	 * 日志内容
	 */
	private String logContent;
	/**
	 * 客户端IP
	 */
	private String clientIp;
	/**
	 * 日志时间
	 */
	private Date logTime;
	/**
	 * 请求方法
	 */
	private String requestMethod;
	/**
	 * 请求参数
	 */
	private String requestParams;
	
	/**
	 * 其他数据
	 */
	private String other;

	public String getLogTitle() {
		return logTitle;
	}

	public void setLogTitle(String logTitle) {
		this.logTitle = logTitle;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	
	
	public String getRequestParams() {
		return requestParams;
	}

	public void setRequestParams(String requestParams) {
		this.requestParams = requestParams;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	@Override
	public String toString() {
		return "LogBean [logTitle=" + logTitle + ", logContent=" + logContent + ", clientIp=" + clientIp + ", logTime="
				+ logTime + ", requestMethod=" + requestMethod + ", requestParams=" + requestParams + ", other=" + other
				+ "]";
	}
}
