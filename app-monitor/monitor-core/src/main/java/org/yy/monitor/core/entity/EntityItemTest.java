package org.yy.monitor.core.entity;

import java.util.Date;

import org.yy.monitor.core.util.CustomDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 监控项测试结果
 * 
 * @author zhouliang
 *
 */
public class EntityItemTest {

	/**
	 * 结果标识
	 */
	private Integer testID;

	/**
	 * 监控实体项
	 */
	private Integer itemID;

	/**
	 * 测试时间
	 */
	private Date testTime;

	/**
	 * 测试结果
	 */
	private Boolean result;

	/**
	 * 期望响应
	 */
	private String expectResponse;

	/**
	 * 实际响应
	 */
	private String actulResponse;

	/**
	 * 测试URL
	 */
	private String url;

	/**
	 * 请求参数
	 */
	private String params;

	public Integer getTestID() {
		return testID;
	}

	public void setTestID(Integer testID) {
		this.testID = testID;
	}

	public Integer getItemID() {
		return itemID;
	}

	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getTestTime() {
		return testTime;
	}

	public void setTestTime(Date testTime) {
		this.testTime = testTime;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String getExpectResponse() {
		return expectResponse;
	}

	public void setExpectResponse(String expectResponse) {
		this.expectResponse = expectResponse;
	}

	public String getActulResponse() {
		return actulResponse;
	}

	public void setActulResponse(String actulResponse) {
		this.actulResponse = actulResponse;
	}

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

	@Override
	public String toString() {
		return "EntityItemTest [testID=" + testID + ", itemID=" + itemID
				+ ", testTime=" + testTime + ", result=" + result
				+ ", expectResponse=" + expectResponse + ", actulResponse="
				+ actulResponse + ", url=" + url + ", params=" + params + "]";
	}

}
