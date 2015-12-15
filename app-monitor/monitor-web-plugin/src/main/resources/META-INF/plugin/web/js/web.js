/**
 * web插件js
 */
$(function(){
	
	//异步请求结果存放div
	var ajax_result = "ajax_result";
	var frm_add_entity = 'frm_add_entity';
	var frm_edit_entity = 'frm_edit_entity';
	
	//测试web监控项
	$('.test-entity-item').click(function(){
		var itemId =  $(this).attr('itemid') ;
		var url =  $('#web_test_url').val();
		layer.load(2);		//显示loading
		$.ajax({
			  type: 'POST',dataType: "json",timeout : 30000,
			  url: "/plugins/web/test_entity_item.json",
			  data: "itemId=" + itemId + "&url=" + url,
			  success: function(data){
				  data.reqUrl = url;
				  layer.open({ type: 0, shade: 0.3,  area: '700px', title:'测试结果', content: juicer(test_entity_item_tpl,data),  zIndex: layer.zIndex,
					  yes: function(index){
						  layer.closeAll();
						  window.location.reload();
					  }
				  });         
			  },
			  complete:function(xhr, ts){ layer.closeAll('loading');},
			  error:function(xhr,err_info,ex){ layer.msg('测试时，系统发生异常...',{time: 1000}); }
		});
	});
	
	
	
	//显示web监控项测试结果列表
	$('.list-test-entity-item').click(function(){
		var itemId =  $(this).attr('itemid') ;
		layer.load(2);
		$.ajax({
			  type: 'POST',dataType: "json",timeout : 30000,
			  url: "/plugins/web/list_test_entity.json",
			  data: "itemId=" + itemId,
			  success: function(data){
				  layer.open({
					    type: 0, shade: 0.3, area: ['850px', '550px'],btn:[],zIndex: layer.zIndex-1,
					    title:"测试结果列表<font color='red'>(放置在期望结果，实际结果，可看具体数据)</font>",
					    content: juicer(list_test_entity_item_tpl,data),
					    success: function(layero){
					        $('.detail-test-entity-item').click(function(){
								  layer.tips('请求URL:' + $(this).attr("url") + "<br>" + "请求参数:" + $(this).attr('params'), $(this), {
									    tips: [4, '#3595CC'], time: 3000, zIndex:layer.zIndex
								  });
							})
					    }
					});     
			  },
			  complete:function(xhr, ts){ layer.closeAll('loading'); },
			  error:function(xhr,err_info,ex){ layer.msg('系统发生异常...',{time: 1000}); }
		});
	});
	
	
	
	//选择实体
	$('.select-entity').click(function(){
		layer.load(2);
		window.location.href = $(this).attr("href");
	});
	
	
	
	//删除监控实体
	$('#del-entity').click(function(){
		layer.msg('你确定要删除当前应用吗？', {
		    time: 0 //不自动关闭
		    ,btn: ['确定', '取消']
		    ,yes: function(index){
		    	layer.load(2);
		    	$.ajax({
					  type: 'POST',dataType: "json",timeout : 30000,
					  url: "/plugins/web/del_entity.json",
					  data: "cfgID=" + $('#currentEntityCfgID').val(),
					  success: function(data){
						  if( "success" == data.flag){
							  window.location.reload();
						  }else{
							  layer.closeAll('loading');
							  layer.msg('删除失败,请确认应用下的监控项是否被删除！', {icon: 5,time: 2000 });
						  }
						  
					  },
					  complete:function(xhr, ts){ layer.closeAll('loading');},
				});
		    }
		});
	});
	
	
	
	//增加实体
	$('#add-entity').click(function(){
		var monitor_type = { "monitor_type": $('#currentPluginCode').val() };
		layer.open({ type: 0, shade: 0.3, area: '700px', title:"增加WEB应用", btn:['提交'],
		    yes: function(){
		    	//Step 1, 设置表单提交选项
				var frmOptions = { target:  ajax_result, dataType: 'json', timeout : 3000,	 
				    success: function(data){
				    	if("success" == data.flag){
				    		layer.alert('成功增加监控实体！', {icon: 6, yes:function(){window.location.reload()}});
				    	}
				    } 	
				};
				//2, 绑定表单提交,异步提交
				$('#' + frm_add_entity).ajaxSubmit(frmOptions);
		    },
		    content: juicer(add_entity_tpl,monitor_type),
		    zIndex: layer.zIndex-1
		});   
	});
	
	
	
	//更新实体
	$('#edit-entity').click(function(){
		var cfgID = $('#currentEntityCfgID').val();
		layer.load(2);
    	$.ajax({
			  type: 'POST',dataType: "json",timeout : 30000,
			  url: "/plugins/web/edit_entity.json",
			  data: "cfgID=" + cfgID,
			  success: function(data){
				  var nodes = data.data.nodes.join(';');
				  data.data.nodes = nodes;
				  layer.open({ type: 0, shade: 0.3, area: '700px', title:"编辑WEB应用", btn:['提交'],
					    yes: function(){
					    	//Step 1, 设置表单提交选项
							var frmOptions = { target:  ajax_result, dataType: 'json', timeout : 3000,	 
							    success: function(data){
							    	if("success" == data.flag){
							    		layer.alert('成功编辑监控实体！', {icon: 6, yes:function(){window.location.reload()}});
							    	}
							    } 	
							};
							//2, 绑定表单提交,异步提交
							$('#' + frm_edit_entity).ajaxSubmit(frmOptions);
					    },
					    content: juicer(edit_entity_tpl,data),
					    zIndex: layer.zIndex-1
				  });   
			  },
			  complete:function(xhr, ts){ layer.closeAll('loading');},
		});
	});
	
	
	
	//增加监控项
	$('#add_entity_item').click(function(){
		var entityCfgID = { "entityCfgID": $('#currentEntityCfgID').val() };
		layer.open({ type: 0, shade: 0.3, area: '700px', title:"增加WEB监控项", btn:['提交'],
		    yes: function(){
		    	//Step 1, 设置表单提交选项
				var frmOptions = { target:  ajax_result, dataType: 'json', timeout : 3000,	 
				    success: function(data){
				    	if("success" == data.flag){
				    		layer.alert('成功增加web监控项！', {icon: 6, yes:function(){window.location.reload()}});
				    	}
				    } 	
				};
				//2, 绑定表单提交,异步提交
				$('#frm_add_entity_item').ajaxSubmit(frmOptions);
		    },
		    content: juicer(add_entityitem_tpl,entityCfgID),
		    zIndex: layer.zIndex-1
		});  
	})
	
	
	//删除监控项
	$('.del-entity-item').click(function(){
		var itemId =  $(this).attr('itemid') ;
		layer.msg('你确定要删除当前应用监控项吗？', {
		    time: 0 //不自动关闭
		    ,btn: ['确定', '取消']
		    ,yes: function(index){
		    	layer.load(2);
		    	$.ajax({
					  type: 'POST',dataType: "json",timeout : 30000,
					  url: "/plugins/web/del_entity_item.json",
					  data: "itemId=" + itemId,
					  success: function(data){
						  if( "success" == data.flag){
							  window.location.reload();
						  }else{
							  layer.closeAll('loading');
							  layer.msg('删除失败！', {icon: 5,time: 2000 });
						  }
						  
					  },
					  complete:function(xhr, ts){ layer.closeAll('loading');},
				});
		    }
		});
	})
	
	
	//配置监控项
	$('.cfg-entity-item').click(function(){
		var itemId =  $(this).attr('itemid') ;
		layer.load(2);
    	$.ajax({
			  type: 'POST',dataType: "json",timeout : 30000,
			  url: "/plugins/web/edit_entityitem.json",
			  data: "itemId=" + itemId,
			  success: function(data){
				  layer.open({ type: 0, shade: 0.3, area: '700px', title:"编辑WEB应用监控项", btn:['提交'],
					    yes: function(){
					    	//Step 1, 设置表单提交选项
							var frmOptions = { target:  ajax_result, dataType: 'json', timeout : 3000,	 
							    success: function(data){
							    	if("success" == data.flag){
							    		layer.alert('成功编辑web应用监控项！', {icon: 6, yes:function(){window.location.reload()}});
							    	}
							    } 	
							};
							//2, 绑定表单提交,异步提交
							$('#frm_edit_entity_item').ajaxSubmit(frmOptions);
					    },
					    content: juicer(edit_entityitem_tpl,data),
					    zIndex: layer.zIndex-1
				  });   
			  },
			  complete:function(xhr, ts){ layer.closeAll('loading');},
		});
	})
	
	
	//测试结果页面模板
	var test_entity_item_tpl = '<form class="pure-form pure-form-aligned">\
						<fieldset>\
					    <div class="pure-control-group">\
					        <label for="url">请求URL:</label>\
					        <input id="url" type="text" class="pure-u-1-2" value="${reqUrl}${data.entityItem.url}" readonly>\
					    </div>\
					    <div class="pure-control-group">\
					        <label for="params">请求参数:</label>\
					        <input id="params" type="text"  class="pure-u-1-2" value="${data.entityItem.params}" readonly>\
					    </div>\
					    <div class="pure-control-group">\
					        <label for="method">请求方法:</label>\
					        <input id="method" type="text"  class="pure-u-1-2"  value="${data.entityItem.method}" readonly>\
					    </div>\
					    <div class="pure-control-group">\
					        <label for="expectResp">期望结果:</label>\
					        <input id="expectResp" type="text" class="pure-u-1-2"  value="${data.entityItem.resp}" readonly>\
					    </div>\
						<div class="pure-control-group">\
					        <label for="actualResp">实际结果:</label>\
							<textarea rows="3" cols="60">${data.result.response}</textarea>\
					    </div>\
						<div class="pure-control-group">\
					        <label for="testResult">测试结果:</label>\
		 					<font color="red" style="font-size:18px" >{@if data.result.status}正确{@else}错误{@/if}</font>\
					    </div>\
					</fieldset>\
				</form>';
	
	
	//测试结果列表页面模板
	var list_test_entity_item_tpl = '<div class="pure-g">\
							<div class="pure-u-5-5">\
								<table class="pure-table" style="width: 100%">\
									<thead>\
										<tr>\
											<th width="10">#</th>\
											<th width="150">测试时间</th>\
											<th width="180">期望响应</th>\
											<th width="180">实际响应</th>\
											<th>测试结果</th>\
											<th>操作</th>\
										</tr>\
									</thead>\
									<tbody>\
										{@each data as item,index}\
										<tr  {@if index%2 == 1} class="pure-table-odd"  {@/if}>\
											<td>${item.testID}</td>\
											<td>${item.testTime}</td>\
											<td style="padding:2px;" title="${item.expectResponse}"><input type="text"  style="border:0;width:100%;{@if index%2 == 1} background-color:#f2f2f2 {@/if}" value="${item.expectResponse}" ></td>\
											<td style="padding:2px;" title="${item.actulResponse}"><input type="text" style="border:0;width:100%;{@if index%2 == 1} background-color:#f2f2f2 {@/if}"  value="${item.actulResponse}"></td>\
											<td><font {@if item.result != true}  color="red"  {@/if} >${item.result}</font></td>\
											<td><a class="detail-test-entity-item" href="javascript:void(0)" url="${item.url}" params="${item.params}">参数明细</a></td>\
										</tr>\
										{@/each}\
									</tbody>\
								</table>\
							</div>\
						</div>';
	
	
	//增加监控实体模板
	var add_entity_tpl = '<form class="pure-form pure-form-aligned" id="frm_add_entity" action="/plugins/web/add_entity.json"  method="post">\
						    <fieldset>\
							    <div class="pure-control-group">\
							        <label for="name">应用名称:</label>\
							        <input name="name" type="text" class="pure-u-2-5">\
									<input name="monitorType" type="hidden" value="${monitor_type}">\
							    </div>\
							    <div class="pure-control-group">\
							        <label for="url">访问URL:</label>\
							        <input name="url" type="text" placeholder="http://www.baidu.com"  class="pure-u-2-5">\
							    </div>\
							    <div class="pure-control-group">\
							        <label for="icon">应用图标:</label>\
							        <input name="icon" type="text" placeholder="http://www.baidu.com/baidu.png"  class="pure-u-2-5">\
							    </div>\
							    <div class="pure-control-group">\
							        <label for="desc">应用描述:</label>\
									<textarea name="desc" rows="3" cols="60"></textarea>\
							    </div>\
								<div class="pure-control-group">\
							        <label for="entity_nodes">应用节点:</label>\
							        <input name="entity_nodes" type="text" placeholder="http://192.168.1.1;http://192.168.1.2"  class="pure-u-3-5">\
							    </div>\
							</fieldset>\
						</form>';
	
	//编辑监控实体模板
	var edit_entity_tpl = '<form class="pure-form pure-form-aligned" id="frm_edit_entity" action="/plugins/web/edit_entity_save.json"  method="post">\
						    <fieldset>\
							    <div class="pure-control-group">\
							        <label for="name">应用名称:</label>\
							        <input name="name" type="text" class="pure-u-2-5" value="${data.name}">\
									<input name="cfgID" type="hidden" class="pure-u-1-2" value="${data.cfgID}" >\
									<input name="monitorType" type="hidden" value="${data.monitorType}">\
							    </div>\
							    <div class="pure-control-group">\
							        <label for="url">访问URL:</label>\
							        <input name="url" type="text" value="${data.url}"  class="pure-u-2-5" >\
							    </div>\
							    <div class="pure-control-group">\
							        <label for="icon">应用图标:</label>\
							        <input name="icon" type="text" value="${data.icon}"  class="pure-u-2-5">\
							    </div>\
							    <div class="pure-control-group">\
							        <label for="desc">应用描述:</label>\
									<textarea name="desc" rows="3" cols="60">${data.desc}</textarea>\
							    </div>\
								<div class="pure-control-group">\
							        <label for="entity_nodes">应用节点:</label>\
							        <input name="entity_nodes" type="text" value="${data.nodes}"  class="pure-u-3-5">\
							    </div>\
							</fieldset>\
						</form>';
	
	
	//增加监控项模板
	var add_entityitem_tpl = '<form class="pure-form pure-form-aligned" id="frm_add_entity_item" action="/plugins/web/add_entity_item.json"  method="post">\
						    <fieldset>\
							    <div class="pure-control-group">\
							        <label for="name">业务名称:</label>\
							        <input name="name" type="text" class="pure-u-2-5">\
									<input name="entityCfgID" type="hidden" value="${entityCfgID}">\
							    </div>\
							    <div class="pure-control-group">\
							        <label for="url">请求URL:</label>\
							        <input name="url" type="text" placeholder="/scan"  class="pure-u-2-5">\
							    </div>\
							    <div class="pure-control-group">\
							        <label for="params">请求参数:</label>\
							        <input name="params" type="text" placeholder="param1=v1&param2=v2"  class="pure-u-2-5">\
							    </div>\
							    <div class="pure-control-group">\
							        <label for="charset">字符集:</label>\
									<input name="charset" type="text" placeholder="UTF-8/GB2312等"  class="pure-u-2-5">\
							    </div>\
								<div class="pure-control-group">\
							        <label for="method">方法:</label>\
							        <input name="method" type="text" placeholder="POST或GET"  class="pure-u-3-5">\
							    </div>\
								<div class="pure-control-group">\
							        <label for="resp">期望结果:</label>\
							        <input name="resp" type="text"  class="pure-u-3-5">\
							    </div>\
								<div class="pure-control-group">\
							        <label for="schedulerCron">调度规则:</label>\
							        <input name="schedulerCron" type="text" placeholder="* * * * *(分钟 小时 日期 月份 星期)"  class="pure-u-3-5">\
							    </div>\
								<div class="pure-control-group">\
							        <label for="notifyMobile">通知电话:</label>\
							        <input name="notifyMobile" type="text" placeholder="13811111111;13911111111" class="pure-u-3-5">\
							    </div>\
								<div class="pure-control-group">\
							        <label for="notifyEmail">通知邮箱:</label>\
							        <input name="notifyEmail" type="text" placeholder="aa@163.com;bb@163.com" class="pure-u-3-5">\
							    </div>\
							</fieldset>\
						</form>';
	
	//编辑监控项模板
	var edit_entityitem_tpl = '<form class="pure-form pure-form-aligned" id="frm_edit_entity_item" action="/plugins/web/edit_entityitem_save.json"  method="post">\
						    <fieldset>\
							    <div class="pure-control-group">\
							        <label for="name">业务名称:</label>\
							        <input name="name" type="text" class="pure-u-2-5" value="${data.name}">\
									<input name="itemID" type="hidden" value="${data.itemID}">\
									<input name="reqTimes" type="hidden" value="${data.reqTimes}">\
									<input name="successTimes" type="hidden" value="${data.successTimes}">\
									<input name="failureTimes" type="hidden" value="${data.failureTimes}">\
									<input name="entityCfgID" type="hidden" value="${data.entityCfgID}">\
							    </div>\
							    <div class="pure-control-group">\
							        <label for="url">请求URL:</label>\
							        <input name="url" type="text"   class="pure-u-2-5" value="${data.url}">\
							    </div>\
							    <div class="pure-control-group">\
							        <label for="params">请求参数:</label>\
							        <input name="params" type="text" value="${data.params}"  class="pure-u-2-5">\
							    </div>\
							    <div class="pure-control-group">\
							        <label for="charset">字符集:</label>\
									<input name="charset" type="text"  value="${data.charset}"  class="pure-u-2-5">\
							    </div>\
								<div class="pure-control-group">\
							        <label for="method">方法:</label>\
							        <input name="method" type="text" value="${data.method}"  class="pure-u-3-5">\
							    </div>\
								<div class="pure-control-group">\
							        <label for="resp">期望结果:</label>\
							        <input name="resp" type="text" value="${data.resp}"  class="pure-u-3-5">\
							    </div>\
								<div class="pure-control-group">\
							        <label for="schedulerCron">调度规则:</label>\
							        <input name="schedulerCron" type="text" placeholder="* * * * *(分钟 小时 日期 月份 星期)" value="${data.schedulerCron}"  class="pure-u-3-5">\
							    </div>\
								<div class="pure-control-group">\
							        <label for="notifyMobile">通知电话:</label>\
							        <input name="notifyMobile" type="text" placeholder="13811111111;13911111111" value="${data.notifyMobile}"  class="pure-u-3-5">\
							    </div>\
								<div class="pure-control-group">\
							        <label for="notifyEmail">通知邮箱:</label>\
							        <input name="notifyEmail" type="text" placeholder="aa@163.com;bb@163.com" value="${data.notifyEmail}"  class="pure-u-3-5">\
							    </div>\
							</fieldset>\
						</form>';
	
	
});