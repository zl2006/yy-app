package org.yy.monitor.core.entity;

import java.util.Date;

/**
 * 监控对象
 * 
 * @author zhouliang
 *
 */
public class Entity {
	/**
	 * 配置ID
	 */
	private Integer cfgID;

	/**
	 * 实体名称
	 */
	private String name;

	/**
	 * 描述
	 */
	private String desc;

	/**
	 * 配置类型，对应监控插件的编码
	 */
	private String monitorType;

	/**
	 * 监控配置,具体格式和解析由插件实现。\n例如：\n{\n	‘dbtype’: ‘mysql’,\n	‘url’	:	‘jdbc:…’,\n	‘user’:	‘monitor’,\n	‘pwd’	: ‘monitor’\n}
	 */
	private String monitorEntityCfg;

	/**
	 * 监控节点,具体格式和解析由插件实现。\n[{ip},{ip}]
	 */
	private String monitorEntityNodes;

	/**
	 * 创建者
	 */
	private Integer managerID;

	/**
	 * 状态
	 */
	private Integer status;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	public Integer getCfgID() {
		return cfgID;
	}

	public void setCfgID(Integer cfgID) {
		this.cfgID = cfgID;
	}

	public String getMonitorType() {
		return monitorType;
	}

	public void setMonitorType(String monitorType) {
		this.monitorType = monitorType;
	}

	public String getMonitorEntityCfg() {
		return monitorEntityCfg;
	}

	public void setMonitorEntityCfg(String monitorEntityCfg) {
		this.monitorEntityCfg = monitorEntityCfg;
	}

	public String getMonitorEntityNodes() {
		return monitorEntityNodes;
	}

	public void setMonitorEntityNodes(String monitorEntityNodes) {
		this.monitorEntityNodes = monitorEntityNodes;
	}

	public Integer getManagerID() {
		return managerID;
	}

	public void setManagerID(Integer managerID) {
		this.managerID = managerID;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
