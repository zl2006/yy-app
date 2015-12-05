/**
 * web插件js
 */
$(function(){
	
	
	//测试web监控实体
	$('.web-test').click(function(){
		
		var itemId =  $(this).attr('itemid') ;
		var url =  $('#web_url').val();
		layer.load(2);
		
		$.ajax({
			  type: 'POST',
			  url: "/plugins/web/test.json",
			  data: "itemId=" + itemId + "&url=" + url,
			  dataType: "json",
			  timeout : 30000,
			  success: function(data){
				  layer.open({
					    type: 0,
					    shade: 0.3,
					    area: '700px',
					    title:'测试结果',
					    content: juicer(test_tpl,data),
					    zIndex: layer.zIndex, //重点1
					    success: function(layero){
					        layer.setTop(layero); //重点2
					    }
					});         
			  },
			  complete:function(xhr, ts){
				  layer.closeAll('loading');
			  },
			  error:function(xhr,err_info,ex){
				  layer.closeAll('loading');
				  layer.msg('测试时，系统发生异常...',{time: 1000});
			  }
			});
		
	});
	
	//web监控项结果列表
	$('.web-list').click(function(){
		var itemId =  $(this).attr('itemid') ;
		layer.load(2);
		$.ajax({
			  type: 'POST',
			  url: "/plugins/web/list_test.json",
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
					    content: juicer(test_list_tpl,data),
					    zIndex: layer.zIndex-1, //重点1
					    success: function(layero){
					        //layer.setTop(layero); //重点2
					        $('.web-detail').click(function(){
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
	$('.web-select-entity').click(function(){
		layer.load(2);
		window.location.href = $(this).attr("href");
	});
	
	
	//测试结果
	var test_tpl = '<form class="pure-form pure-form-aligned">\
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
	
	
	var test_list_tpl = '<div class="pure-g">\
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
											<td><a class="web-detail" href="javascript:void(0)" url="${item.url}" params="${item.params}">参数明细</a></td>\
										</tr>\
										{@/each}\
									</tbody>\
								</table>\
							</div>\
						</div>';
	
});