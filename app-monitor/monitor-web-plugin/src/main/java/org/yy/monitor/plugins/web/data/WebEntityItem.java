package org.yy.monitor.plugins.web.data;

import org.yy.monitor.core.entity.EntityItem;

/**
 * web监控实体项
 * 
 * @author zhouliang
 *
 */
public class WebEntityItem extends EntityItem {

	/**
	 * 监控请求地址
	 */
	private String url;

	/**
	 * 请求参数
	 */
	private String params;

	/**
	 * 正常响应字符串
	 */
	private String resp;

	/**
	 * 字符集
	 */
	private String charset;

	/**
	 * 请求方法
	 */
	private String method;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getResp() {
		return resp;
	}

	public void setResp(String resp) {
		this.resp = resp;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}
