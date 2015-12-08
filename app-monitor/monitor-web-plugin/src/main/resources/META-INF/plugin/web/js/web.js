/**
 * web插件js
 */
$(function(){
	
	//异步请求结果存放div
	var ajax_result = "ajax_result";
	var frm_add_entity = 'frm_add_entity';
	
	
	
	//测试web监控项
	$('.test-entity-item').click(function(){
		
		var itemId =  $(this).attr('itemid') ;
		var url =  $('#web_url').val();
		layer.load(2);		//显示loading
		
		$.ajax({
			  type: 'POST',
			  url: "/plugins/web/test_entity_item.json",
			  data: "itemId=" + itemId + "&url=" + url,
			  dataType: "json",
			  timeout : 30000,
			  success: function(data){
				  layer.open({ type: 0, shade: 0.3,  area: '700px', title:'测试结果', content: juicer(test_entity_item_tpl,data),  zIndex: layer.zIndex });         
			  },
			  complete:function(xhr, ts){
				  layer.closeAll('loading');
			  },
			  error:function(xhr,err_info,ex){
				  layer.msg('测试时，系统发生异常...',{time: 1000});
			  }
			});
		
	});
	
	
	
	//显示web监控项测试结果列表
	$('.list-test-entity-item').click(function(){
		var itemId =  $(this).attr('itemid') ;
		layer.load(2);
		$.ajax({
			  type: 'POST',
			  url: "/plugins/web/list_test_entity.json",
			  data: "itemId=" + itemId,
			  dataType: "json",
			  timeout : 30000,
			  success: function(data){
				  layer.open({
					    type: 0,
					    shade: 0.3,
					    area: ['850px', '550px'],
					    title:"测试结果列表<font color='red'>(放置在期望结果，实际结果，可看具体数据)</font>",
					    btn:[],
					    content: juicer(list_test_entity_item_tpl,data),
					    zIndex: layer.zIndex-1, //重点1
					    success: function(layero){
					        //layer.setTop(layero); //重点2
					        $('.detail-test-entity-item').click(function(){
								  layer.tips('请求URL:' + $(this).attr("url") + "<br>" + "请求参数:" + $(this).attr('params'), $(this), {
									    tips: [4, '#3595CC'],
									    time: 3000,
									    zIndex:layer.zIndex
									});
							  })
					    }
					});     
			  },
			  complete:function(xhr, ts){
				  layer.closeAll('loading');
			  },
			  error:function(xhr,err_info,ex){
				  layer.msg('系统发生异常...',{time: 1000});
			  }
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
		    	$.ajax({
					  type: 'POST',
					  url: "/plugins/web/del_entity.json",
					  data: "cfgID=" + $('#currentEntityCfgID').val(),
					  dataType: "json",
					  timeout : 30000,
					  success: function(data){
						  if( "success" == data.flag){
							  window.location.reload();
						  }else{
							  layer.msg('删除失败', {icon: 5,time: 1000 });
						  }
						  
					  }
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
		alert('更新实体');
	});
	
	
	
	//测试结果页面模板
	var test_entity_item_tpl = '<form class="pure-form pure-form-aligned">\
						<fieldset>\
					    <div class="pure-control-group">\
					        <label for="url">请求URL:</label>\
					        <input id="url" type="text" class="pure-u-1-2" value="${data.entity.url}${data.entityItem.url}" readonly>\
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
});