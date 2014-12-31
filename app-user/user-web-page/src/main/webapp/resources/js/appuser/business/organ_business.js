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
	var tpl ='{@each result as item,index} <tr view="/organ/view.do?organCode=&{item.organCode}&resID=47" id="&{item.organCode}" {@if item.hasChild==1}  haschild="true" {@/if} {@if item.parentOrganCode!="-1"} pid="&{item.parentOrganCode}" {@/if} >  \
		<td>&{index}</td> \
		<td>&{item.name }</td> \
		<td>&{item.tel}</td> \
		<td>&{item.postCode }</td> \
		<td>&{item.description }</td> \
		<td>{@if item.status==1} 有效 {@/if} {@if item.status==0}无效{@/if}</td> \
		<td>\
			<a resID="19" class="popedom" role="popedom" condition="1" params="organCode=&{item.organCode}"></a>\
			<a resID="20" class="popedom" role="popedom" condition="{@if item.status==0}1{@/if}" params="organCode=&{item.organCode}"></a>\
			<a resID="21" class="popedom" role="popedom" condition="{@if item.status==1}1{@/if}" params="organCode=&{item.organCode}"></a>\
		</td>\
	</tr>{@/each} ';
	
	var OrganBusiness = Business.extend({
		
		/**
		 * 构造函数
		 */
		initialize: function(param_options) {
			//调用父类的构造函数
			OrganBusiness.superclass.initialize.call(this, param_options);
		},
		
		
		/**
         * 初始化列表页面
         */
        init_list_page:function(){
        	OrganBusiness.superclass.init_list_page.call(this);
        	this.handleChildTree(this.options.opers);
        },
		
		
		/**
		 * 初始化增删页面
		 */
		init_saveorupdate_page:function(){
			var that = this;
			//调用父类的方法
			OrganBusiness.superclass.init_saveorupdate_page.call(this);
			//2, 选择父组织机构
			$("#selectOrgan").on('click', function(event){
				that.selectOrgan();
			});
		},
		
		/**
		 * 选择父组织机构
		 */
		selectOrgan:function(){
			var that=this;
			//按需要才加载JS文件
			seajs.use('organ',function(OrganSelectModal){
				var organModal = null;
				organModal = new OrganSelectModal({"basePath" : that.options.base_path ,  "selectedOrgan":function(parentOrganCode,parentOrganName) {
					$('#parentOrganCode').val(parentOrganCode);
					$('#parentOrganName').val(parentOrganName);
					organModal.close();
				}});
				organModal.open();
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
	                $.getJSON( that.options.base_path + "/organ/listChild.json", {parentOrganCode:id}, function( data ) { 
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
	
	module.exports = OrganBusiness;

});