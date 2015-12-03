package org.yy.monitor.core.entity;

/**
 * 测试结果
 * 
 * @author zhouliang
 *
 */
public class TestResult {

	/**
	 * 成功或失败
	 */
	private boolean status;

	/**
	 * 响应
	 */
	private String response;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "TestResult [status=" + status + ", response=" + response
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
