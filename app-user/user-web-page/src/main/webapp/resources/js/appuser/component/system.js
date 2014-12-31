/**
 * 系统选择组件
 * 
 * 需要引入 jquery.js, bootstrap.js,bootstrap-paginator.js,juicer.js
 * 
 * @author zhouliang
 * @version	0.1
 * @create 2014-3-6
 */
define(function(require, exports, module){
var $ = require('$');
require('bootstrap');
require('bootstrappaginator');
var juicer = require('juicer');

(function($){
	
	//
	juicer.set({
	    'tag::interpolateOpen': '&{',
	    'tag::noneencodeOpen': '&&{'
	});
	
	//默认参数
	var default_options = {
		id : '_select_system_',									//选择对话框组件的id
		title : '选择系统',										//标题
		width : '80%',											//对话框宽度
		basePath : 'http://localhost:8087/user',				//动作基础路径
		listAction: '/system/list.json',						//列表动作
		selectedSystem : function(systemCode,name){}				//选择组织机构
	};
	
	//组织机构模板
	var tpl = '	<div class="modal-dialog" style="width:&{width}"> \
	    			<div class="modal-content"> \
	    				<div class="modal-header"> \
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> \
							<h4 class="modal-title" id="myModalLabel">&{title}</h4> \
						</div> \
						<div class="modal-body"> \
					    	<form class="form-horizontal" role="form" method="post"> \
								<div class="form-group last-group"> \
									<label for="systemCode" class="col-sm-1 control-label">系统编号:</label> \
									<div class="col-sm-2"> \
										<input type="text" class="form-control" placeholder="系统编号" name="systemCode" value="&{systemCode}" > \
									</div> \
									<label for="name" class="col-sm-1 control-label">名称:</label> \
									<div class="col-sm-3"> \
										<input type="text" class="form-control" placeholder="名称" name="name" value="&{name}"> \
									</div> \
									<div class="col-sm-1"> \
										<button type="button" class="btn btn-primary queryBTN">查询</button> \
									</div> \
								</div> \
							</form> \
							<table id="datatable" width="100%" class="table table-bordered table-hover" style="margin-top:20px"> \
								<thead> \
									<tr> \
										<th>序号</th> \
										<th>应用名称</th> \
										<th>应用编号</th> \
										<th>应用URL</th> \
										<th>描述</th> \
									</tr> \
								</thead> \
								<tbody> \
									{@each data.result as item,index} \
									<tr id="&{item.systemCode}" name="&{item.name}" > \
										<td >&{index}</td> \
										<td>&{item.name}</td> \
										<td>&{item.systemCode}</td> \
										<td>&{item.url }</td> \
										<td>&{item.description }</td> \
									</tr> \
									{@/each}\
								</tbody> \
							</table>\
							<ul id="pagination">\
							</ul> \
					</div>  \
				    <div class="modal-footer"> \
				      <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> \
				    </div> \
				  </div><!-- /.modal-content --> \
				</div>';
	
	
	/**
	 * 构造函数
	 * var options = {
		title : '选择系统',										//标题
		width : '80%',											//对话框宽度
		basePath : 'http://localhost:8087/user',				//动作基础路径
		listAction: '/system/list.json',						//列表动作
		selectedSystem : function(systemCode, name){}			//选择组织机构
	};
	 */
	function SystemSelectModal(paramOptions){
		this.options = $.extend({}, default_options , paramOptions);
		var $modalEle = $('#' + this.options.id);
		if($modalEle.length == 0){
			$('body').append('<div class="modal fade" id="' + this.options.id + '"></div>');
		}
	}
	
	  
	/**
	 * 查询
	 */
	SystemSelectModal.prototype.query = function(currentPage){
		var options = this.options;
		var that = this;
		var $modalEle = $('#' + options.id);
		
		//查询参数
		var param = {
			systemCode : $('input[name="systemCode"]', $modalEle).val() ,
			name : $('input[name="name"]', $modalEle).val() ,
			"pagination.index" : currentPage,
			status : 1
		};
		
		$.post( options.basePath + options.listAction, param, function( data ) { 
			if( "success" ==  data.flag){
				that.render($.extend({}, data, param) );
			}
        },"json");   
	};   
	
	
	
	/**
	 * 打开对话框
	 */
	SystemSelectModal.prototype.open = function(){
		var options = this.options;
		var that = this;
		var $modalEle = $('#' + options.id);
		
		$.getJSON( options.basePath + options.listAction,{status : 1}, function( data ) { 
			if( "success" ==  data.flag){
				that.render(data);
				$modalEle.modal();
			}
        });
	};
	
	
	
	/**
	 * 渲染内容
	 * @param data 具体内容数据
	 */
	SystemSelectModal.prototype.render=function(data){
		
		var options = this.options;
		var that = this;
		var $modalEle = $('#' + options.id);
		var result = $.extend({}, data, options);				//页面内容数据
		
		//渲染内容
		var tplRender = juicer(tpl, result);
		$('#' + options.id).html(tplRender);
		
		//事件绑定
		//双击行选择组织机构
		$('#datatable', $modalEle).unbind('dblclick');
		$('#datatable' , $modalEle).dblclick(function(eventObj){
			options.selectedSystem($(eventObj.target).parent().attr('id') , $(eventObj.target).parent().attr('name'));
		});
		//查询组织机构
		$(".queryBTN", $modalEle).unbind('click');
		$('.queryBTN' , $modalEle).click(function(eventObj){
			that.query(0);
		});
		
		//渲染分页信息
		var pgoptions = {
			bootstrapMajorVersion : 3,
			currentPage : data.data.pagination.index+1 ,
			totalPages : data.data.pagination.totalPage,
			alignment : 'right',
			size : 'small',
			onPageClicked : function(e, originalEvent, type, page){
				that.query(page-1);
			}
		};
		
		if( data.data.pagination.totalPage > 0 ){
			$('#pagination', $('#'+options.id) ).bootstrapPaginator(pgoptions);
		}
		
	};
	
	
	
	/**
	 * 关闭对话框
	 */
	SystemSelectModal.prototype.close = function(){
		$('#' + this.options.id).modal('hide');
	};
	

	module.exports = SystemSelectModal;
})($);

});