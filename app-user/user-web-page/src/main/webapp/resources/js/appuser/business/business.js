/**
 * 增删改查的基础业务模块
 * 
 * @author zhouliang
 * @version	0.1
 * @create 2014-3-6
 */
define(function(require, exports, module){
	
	var $ = require('$');
	var Class = require('arale/class/2.0.0/class');
	
	//默认参数
	var default_options = {
		/*****公共参数*/
		base_path 			: '/',						//应用基础路径
		selector			: 'body',					//此业务所在html表达式
		/*****增改页参数*/
		save_update_form 	: '#save_update_form',		//保存数据表单的ＩＤ
		query_form			: '#query_form',			//列表查询时的表单ＩＤ
		result_div			: '#result_info',			//ajax submit form时的响应数据存放ID
		error_div 			: '#error_info',			//保存或更新数据等情况下的，检验出错时的结果存放ID
		/*****列表页参数*/
		data_table			: '#data_table',			//数据列表表格的ＩＤ
		pagination			: '#pagination',			//分页组件的ID
		pagination_index	: '#pagination_index',		//表单中隐藏当前页数的元素ＩＤ
		currentPage			:	0,						// 当前页
		totalPages 			:	0,						//总页数
		opers				: 	[]						//列表操作权限
	};

	
    var Business = Class.create({
    	
    	/**
    	 * 构造函数
    	 * 
    	 * @param_options 详见默认参数中的属性
    	 */
        initialize: function(param_options) {
        	this.options = $.extend({}, default_options, param_options);
        	this.$ele = $(this.options.selector);
        },
        

        /**
         * 初始化增加/更新业务数据页面
         */
        init_saveorupdate_page: function() {
        	
        	var that = this;
        	
        	//1, 表单验证
        	seajs.use('bootstrapvalidator',function(bootvalidator){
        		bootvalidator(that.options.save_update_form, that.$ele).validation();
        	});
			
			//2, 绑定表单提交事件
			var frmOptions = {
				target:        this.options.result_div,   
				dataType:  	   'json', 
			    beforeSubmit:  function(){return that.validate_form();}, 							//提交前验证 
			    success:       function(data){that.saveorupdate_handle(data);} 						//提交后处理
			};
			
			seajs.use('form',function(form){
				form(that.options.save_update_form, that.$ele).ajaxForm(frmOptions); 
			});
			
        },
        
        
        /**
         * 初始化列表页面
         */
        init_list_page:function(){
        	var that = this;
        	
        	//1, 分页
			if( this.options.totalPages <= 0 ){
				return;
			}
			var pgoptions = {
				bootstrapMajorVersion : 3,
				currentPage : this.options.currentPage ,
				totalPages : this.options.totalPages ,
				alignment : 'right',
				size : 'small',
				onPageClicked : function(e, originalEvent, type, page) {
					$(that.options.pagination_index, that.$ele).val(page-1);
					//当属性reset为0时表示表单实际提交时不重置页码，用于当通过按钮提交表单时要重置页码，通过分页提交时不用重置
					$(that.options.pagination_index, that.$ele).attr('reset',0);	
					$(that.options.query_form,that.$ele).submit();
				}
			};
			seajs.use('bootstrappaginator',function(bootpaginator){
				bootpaginator(that.options.pagination, that.$ele).bootstrapPaginator(pgoptions);
			});
			
			
			//2, 初始化列表操作权限
			if( this.options.opers && this.options.opers.length > 0){
				seajs.use('popedom',function(popedom){
					popedom.init(that.options.selector + " " + that.options.data_table + " .popedom", that.options.opers , that.operator, that);
				});
			}
			
			//3,重置页码 
			$(this.options.query_form).submit(function(){
				if( $(that.options.pagination_index, that.$ele).attr('reset') != 0 ){
					$(that.options.pagination_index, that.$ele).val("0");
				}
				return true;
			});
			
			//4, 所有view行事件
			$('tr[view]',this.$ele).bind('click',function(event){
				var tagName = event.target.tagName;
				if( tagName == 'A' || tagName == 'a' || tagName == 'input' || tagName == 'INPUT' || tagName == 'span' || tagName == 'SPAN' ){
					return true;
				}
				window.location.href = that.options.base_path + $(event.currentTarget).attr('view');
				return true;
			});
        },
        
        
        /**
		 * 权限操作
		 * @res 操作资源
		 * @url 操作的url
		 * return : true表示通过链接生效, false表示不处理链接
		 */
		operator:function(res, url){
			if( "add" == res.ename){
				return this.add(res,url);
			}else if( "edit" == res.ename){
				return this.edit(res,url);
			}else if( "delete" == res.ename){
				return this._delete(res,url);
			}else if( "view" == res.ename){
				return this.view(res,url);
			}else if( "disable" == res.ename){
				return this.disable(res,url);
			}else if( "enable" == res.ename){
				return this.enable(res,url);
			}else if( "review" == res.ename){
				return this.review(res,url);
			}else{
				return true;
			}
		},
		
		
		/**
		 * 增加操作
		 */
		add : function(res,url){
			return true;
		},
		
		/**
		 * 编辑操作
		 */
		edit : function(res, url){
			return true;
		},
		
		/**
		 * 删除操作
		 */
		_delete : function(res, url){
			return true;
		},
		
		/**
		 * 查看操作
		 */
		view : function(res, url){
			return true;
		},
		
		/**
		 * 审核操作
		 */
		review : function(res, url){
			return true;
		},
		
		/**
		 * 禁用操作
		 */
		disable : function(res, url){
			$.getJSON( url,  function( data ) { 
	        	if(data.flag == "success"){
	        		window.location.reload(true);
	        		return false;
	        	}else{
	        		seajs.use('bootbox',function(bootbox){
						bootbox.alert("禁用操作失败！" + data.errors);
					});
	        		return false;
	        	}
	        });
			return false;
		},
		
		/**
		 * 启用操作
		 */
		enable : function(res, url){
			$.getJSON( url,  function( data ) { 
	        	if(data.flag == "success"){
	        		window.location.reload(true);
	        		return false;
	        	}else{
	        		seajs.use('bootbox',function(bootbox){
						bootbox.alert("启用操作失败！" + data.errors);
					});
	        	}
	        });
			return false;
		},
        
        
        /**
         * 验证表单数据
         */
        validate_form : function(){
        	$(this.options.error_div, this.$ele).html("");
			return $(this.options.save_update_form, this.$ele).valid();
        },
        
        
        /**
         * 保存或更新数据后的处理
         */
        saveorupdate_handle : function(data){
        	if(data.flag  == "success"){
				seajs.use('bootbox',function(bootbox){
					bootbox.alert("操作成功！",function(){window.history.back(true);});
				});
			}else{
				$(this.options.error_div,this.$ele).html(data.errors);
				$("html,body").animate({scrollTop: $(this.options.error_div, this.$ele).offset().top}, 500);
			}
        }
        
    });

    module.exports = Business;

});