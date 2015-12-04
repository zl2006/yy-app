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
			  url: "/plugins/web/list.json",
			  data: "itemId=" + itemId,
			  dataType: "json",
			  timeout : 30000,
			  success: function(data){
				  /*layer.open({
					    type: 0,
					    shade: 0.3,
					    area: '700px',
					    title:'测试结果',
					    content: juicer(test_tpl,data),
					    zIndex: layer.zIndex, //重点1
					    success: function(layero){
					        layer.setTop(layero); //重点2
					    }
					});  */       
			  },
			  complete:function(xhr, ts){
				  layer.closeAll('loading');
			  },
			  error:function(xhr,err_info,ex){
				  layer.closeAll('loading');
				  layer.msg('系统发生异常...',{time: 1000});
			  }
			});
	});
	
	
	//选择实体
	$('.select_entity').click(function(){
		layer.load(2);
		window.location.href = $(this).attr("href");
	});
	
	
	//测试结果
	var test_tpl = '<form class="pure-form pure-form-aligned">\
						<fieldset>\
					    <div class="pure-control-group">\
					        <label for="url">请求URL:</label>\
					        <input id="url" type="text" class="pure-u-1-2" value="${data.entity.url}${data.entityItem.url}">\
					    </div>\
					    <div class="pure-control-group">\
					        <label for="params">请求参数:</label>\
					        <input id="params" type="text"  class="pure-u-1-2" value="${data.entityItem.params}">\
					    </div>\
					    <div class="pure-control-group">\
					        <label for="method">请求方法:</label>\
					        <input id="method" type="text"  class="pure-u-1-2"  value="${data.entityItem.method}">\
					    </div>\
					    <div class="pure-control-group">\
					        <label for="expectResp">期望结果:</label>\
					        <input id="expectResp" type="text" class="pure-u-1-2"  value="${data.entityItem.resp}">\
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
	
});