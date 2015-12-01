package org.yy.monitor.plugins.web.data;

import java.util.List;

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

	/**
	 * 节点
	 */
	private List<String> nodes;

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

	public List<String> getNodes() {
		return nodes;
	}

	public void setNodes(List<String> nodes) {
		this.nodes = nodes;
	}

}
