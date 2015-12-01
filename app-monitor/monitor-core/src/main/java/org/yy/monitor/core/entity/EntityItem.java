package org.yy.monitor.core.entity;

import java.util.Date;

/**
 * 监控实体项
 * 
 * @author zhouliang
 *
 */
public class EntityItem {

	/**
	 * 监控项ID
	 */
	private Integer itemID;

	/**
	 * 监控实体对象ID
	 */
	private Integer entityCfgID;

	/**
	 * 监控项名称
	 */
	private String name;

	/**
	 * 监控项配置,具体格式和解析由插件实现。\n{\n request:’’,\n response:’’\n}
	 */
	private String itemCfg;

	/**
	 * 请求次数
	 */
	private Integer reqTimes;

	/**
	 * 成功次数
	 */
	private Integer successTimes;

	/**
	 * 失败次数
	 */
	private Integer failureTimes;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 状态，1为有效，0为无效
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

	public Integer getItemID() {
		return itemID;
	}

	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}

	public Integer getEntityCfgID() {
		return entityCfgID;
	}

	public void setEntityCfgID(Integer entityCfgID) {
		this.entityCfgID = entityCfgID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItemCfg() {
		return itemCfg;
	}

	public void setItemCfg(String itemCfg) {
		this.itemCfg = itemCfg;
	}

	public Integer getReqTimes() {
		return reqTimes;
	}

	public void setReqTimes(Integer reqTimes) {
		this.reqTimes = reqTimes;
	}

	public Integer getSuccessTimes() {
		return successTimes;
	}

	public void setSuccessTimes(Integer successTimes) {
		this.successTimes = successTimes;
	}

	public Integer getFailureTimes() {
		return failureTimes;
	}

	public void setFailureTimes(Integer failureTimes) {
		this.failureTimes = failureTimes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

}
