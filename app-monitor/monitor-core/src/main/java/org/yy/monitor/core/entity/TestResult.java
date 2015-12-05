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
	private boolean status = false;

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
		if(response != null && response.length() >= 10000){
			this.response = response.substring(0, 9999);
		}else{
			this.response = response;
		}
	}

	@Override
	public String toString() {
		return "TestResult [status=" + status + ", response=" + response
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
