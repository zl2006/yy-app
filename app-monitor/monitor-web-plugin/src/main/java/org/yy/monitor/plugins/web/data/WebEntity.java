package org.yy.monitor.plugins.web.data;

import org.yy.monitor.core.entity.Entity;

/**
 * web监控实体
 * 
 * @author zhouliang
 *
 */
public class WebEntity extends Entity {

	/**
	 * 应用图标
	 */
	private String icon;

	/**
	 * 应用地址
	 */
	private String url;


	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
