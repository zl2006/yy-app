package org.yy.monitor.plugins.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.yy.monitor.core.AbsPluginView;
import org.yy.monitor.core.entity.Entity;
import org.yy.monitor.core.entity.EntityItem;
import org.yy.monitor.core.entity.Plugin;
import org.yy.monitor.core.util.BeanUtilEx;
import org.yy.monitor.core.util.JsonUtil;
import org.yy.monitor.plugins.web.data.WebEntity;
import org.yy.monitor.plugins.web.data.WebEntityItem;


/**
 * web监控插件视图
 * 
 * @author zhouliang
 *
 */
@Component("webmonitorview")
public class WebMonitorView extends AbsPluginView {
	
	private static Logger logger = LoggerFactory.getLogger(WebMonitorView.class);
	

	@SuppressWarnings("unchecked")
	@Override
	protected Object processData(Plugin plugin, List<Entity> entitys, List<EntityItem> entityItems) {
		
		List<WebEntity> webEntitys = new ArrayList<WebEntity>();
		List<WebEntityItem> webEntityItems = new ArrayList<WebEntityItem>();
		
		try{
			for(Entity item :entitys){
				//通用属性
				WebEntity webEntity = new WebEntity();
				BeanUtilEx.copyProperties(webEntity,item);
				
				//特殊属性
				if( StringUtils.isNotEmpty(item.getMonitorEntityCfg())){
					Map<String,String> temp = JsonUtil.parseObject(item.getMonitorEntityCfg(), Map.class);
					BeanUtilEx.populate(webEntity, temp);
				}
				
				//节点属性
				if( StringUtils.isNotEmpty(item.getMonitorEntityNodes())){
					webEntity.setNodes( JsonUtil.parseList(item.getMonitorEntityNodes(), String.class) );
				}
				
				webEntitys.add(webEntity);
			}
			
			
			for(EntityItem item :entityItems){
				//通用属性
				WebEntityItem webEntityItem = new WebEntityItem();
				BeanUtilEx.copyProperties(webEntityItem,item);
				
				//特殊属性
				if( StringUtils.isNotEmpty(item.getItemCfg())){
					Map<String,String> temp = JsonUtil.parseObject(item.getItemCfg(), Map.class);
					BeanUtilEx.populate(webEntityItem, temp);
				}
				
	
				webEntityItems.add(webEntityItem);
			}
			
			
			
			
		}catch(Exception ex){
			//ex.printStackTrace();
			logger.error("process data failure!", ex);
		}
		
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("entitys", webEntitys);
		result.put("entityItems", webEntityItems);
		return result;
	}
	

	/*
	public static void main(String[] args) {
		Properties p = new Properties();
		p.setProperty("resource.loader", "class");
		p.setProperty("class.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		Velocity.init(p);
		
		
		VelocityContext ss = new VelocityContext();
		List<String> s = new ArrayList<String>();
		s.add("aa");
		s.add("bb");
		ss.put("entitys", s);
		
		
		System.out.println(VelocityUtil.mergeTemplate(
				"/org/yy/monitor/plugins/web/vm/index.vm", ss));
	}*/


}
