package org.yy.monitor.plugins.web;

import org.springframework.stereotype.Component;
import org.yy.monitor.core.AbsMonitorView;
import org.yy.monitor.plugins.web.data.WebEntity;
import org.yy.monitor.plugins.web.data.WebEntityItem;


/**
 * web监控插件视图
 * 
 * @author zhouliang
 *
 */
@Component("webmonitorview")
public class WebMonitorView extends AbsMonitorView<WebEntity,WebEntityItem> {
	
	//private static Logger logger = LoggerFactory.getLogger(WebMonitorView.class);
	
	public WebMonitorView(){
		super(WebEntity.class,WebEntityItem.class );
	}
	

	/*@SuppressWarnings("unchecked")
	@Override
	protected void processData(Map<String, Object> data, Plugin plugin, List<Entity> entitys, List<EntityItem> entityItems) {
		
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
			logger.error("process data failure!", ex);
		}
		
		
		data.put("entitys", webEntitys);
		data.put("entityItems", webEntityItems);
	}*/
	

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
