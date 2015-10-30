/**
 * 增加组织机构页面脚本
 * 
 * @author zhouliang
 * @version	0.1
 * @create 2014-3-6
 */
define(function(require, exports, module){
	
	var Business = require('appuser/business/business.js');
	var $ = require('$');
	
	
	//子树的模板
	var tpl ='{@each result as item,index} <tr view="/resource/view.do?myResID=&{item.resID}&resID=50" id="&{item.resID}" {@if item.hasChild==1}  haschild="true" {@/if} {@if item.parentResID!="-1"} pid="&{item.parentResID}" {@/if} >  \
		<td>&{index}</td> \
		<td>&{item.name }</td> \
		<td>&{item.url}</td> \
		<td>\
				{@if item.type == 2 }\
					菜单(子模块)\
		    	{@else if item.type == 3}\
					列表操作\
		        {@else}\
					按钮等其它操作\
		        {@/if}\
		</td> \
		<td>&{item.systemCode }</td> \
		<td>{@if item.status==1} 有效 {@/if} {@if item.status==0}无效{@/if}</td> \
		<td>\
			<a resID="51" class="popedom" role="popedom" condition="1" params="myResID=&{item.resID}"></a>\
			<a resID="52" class="popedom" role="popedom" condition="{@if item.status==0}1{@/if}" params="myResID=&{item.resID}"></a>\
			<a resID="53" class="popedom" role="popedom" condition="{@if item.status==1}1{@/if}" params="myResID=&{item.resID}"></a>\
		</td>\
	</tr>{@/each} ';
	
	var ResBusiness = Business.extend({
		
		/**
		 * 构造函数
		 */
		initialize: function(param_options) {
			//调用父类的构造函数
			ResBusiness.superclass.initialize.call(this, param_options);
		},
		
		
		/**
         * 初始化列表页面
         */
        init_list_page:function(){
        	ResBusiness.superclass.init_list_page.call(this);
        	this.handleChildTree(this.options.opers);
        },
		
		
		/**
		 * 初始化增删页面
		 */
		init_saveorupdate_page:function(){
			var that = this;
			//调用父类的方法
			ResBusiness.superclass.init_saveorupdate_page.call(this);
			//2, 选择系统
			$("#selectSystem").on('click', function(event){
				that.selectSystem();
			});
			//3,选择资源
			$('#selectResource').on('click', function(event){
				that.selectResource();
			});
		},
		
		/**
		 * 选择系统
		 */
		selectSystem:function(){
			var that=this;
			seajs.use('system',function(SystemSelectModal){
				var systemModal = null;
				systemModal = new SystemSelectModal({"basePath" : that.options.base_path ,  "selectedSystem":function(systemCode,name) {
					$('#systemCode').val(systemCode);
					$('#systemName').val(name);
					$('#parentResID').val('');
					$('#parentName').val('');
					systemModal.close();
				}});
				systemModal.open();
			});
		},
		
		/**
		 * 选择父资源
		 */
		selectResource:function(){
			var that=this;
			seajs.use('resource',function(ResSelectModal){
				var resModal = null;
				resModal = new ResSelectModal({"basePath" : that.options.base_path ,"systemCode":$('#systemCode').val(),  "selectedResource":function(resID,Name,systemCode) {
					$('#parentResID').val(resID);
					$('#parentName').val(systemCode +  ":" + Name);
					$('#systemCode').val(systemCode);
					resModal.close();
				}});
				resModal.open();
			});
		},
		
		
		/**
		 * 处理列表中的子树
		 * opers:列表操作权限
		 */
		 handleChildTree:function(opers){
			
			var that = this;

			//2, 处理子树
			var ttoption = {
	            expandLevel : 1,
	            basepath : that.options.base_path + '/resources/js/jquery/mytreetable/1.4.2/',
	            beforeExpand : function($treeTable, id) {
	                //判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
	                if ($('.' + id, $treeTable).length) { return; };
	                $.getJSON( that.options.base_path + "/resource/listChild.json", {parentResID:id}, function( data ) { 
	                	var treeNodes = null;
	                	seajs.use(['juicer','popedom'],function(juicer,popedom){
	                		juicer.set({ 'tag::interpolateOpen': '&{', 'tag::noneencodeOpen': '&&{' });
	                		treeNodes = $(juicer(tpl, data.data));
	                		$treeTable.addChilds(treeNodes); 
	                		popedom.init($("*[role='popedom']",treeNodes), opers,that.operator,that);
	                		
	                		//4, 所有view行事件
	                		treeNodes.bind('click',function(event){
	            				var tagName = event.target.tagName;
	            				if( tagName == 'A' || tagName == 'a' || tagName == 'input' || tagName == 'INPUT' || tagName == 'span' || tagName == 'SPAN' ){
	            					return true;
	            				}
	            				window.location.href = that.options.base_path + $(event.currentTarget).attr('view');
	            				return true;
	            			});
	                	});
	                });
	            }
	         };
			
			seajs.use('treetable',function(treetable){
				treetable(that.options.data_table).treeTable(ttoption);
			});
	         
		},
		edit : function(res, url){
			 return true;
		}
		
		
	});//extends end
	
	module.exports = ResBusiness;

});