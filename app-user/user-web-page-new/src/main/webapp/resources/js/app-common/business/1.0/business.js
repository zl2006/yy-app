/**
 * 增删改查的基础业务模块
 * 
 * @author zhouliang
 * @version	0.1
 * @create 2014-3-6
 */
define(function(require, exports, module){
	
	var $ = require('jquery');
	var Class = require('class');
	
	//扩展endWith
	String.prototype.endWith=function(str){
		if(str==null||str==""||this.length==0||str.length>this.length)
		  return false;
		if(this.substring(this.length-str.length).toUpperCase()==str.toUpperCase())
		  return true;
		else
		  return false;
		return true;
	}
	
	//默认参数
	var default_options = {
		/*****公共参数*/
		base_path 			: '/',								//应用基础路径
		selector			: 'body',								//此业务所在html表达式
		/*****增改页参数*/
		save_update_form 	: '#save_update_form',		//保存数据表单的ＩＤ
		query_form			: '#query_form',								//列表查询时的表单ＩＤ
		result_div			: '#result_info',									//ajax submit form时的响应数据存放ID
		error_div 			: '#error_info',										//保存或更新数据等情况下的，检验出错时的结果存放ID
		submitBtn		:'#submitBtn',		
		/*****列表页参数*/
		data_table			: '#data_table',							//数据列表表格的ＩＤ
		row_click				: false,											//允许行可以单击
		pagination			: '#pagination',							//分页组件的ID
		pagination_index	: '#pagination_index',			//表单中隐藏当前页数的元素ＩＤ
		currentPage			:	0,												// 当前页
		totalPages 			:	0,													//总页数
		opers				: 	[]	,														//列表操作权限
		/**操作回调事件*/
		saveorupdate_handle : null
	};
	
	function stopDefaultEvent(e){
		if(e && e.preventDefault){
			e.preventDefault();
		}else{
			window.event.returnValue = fasle;
		}
		return false;
	}

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
    		
			//1, 设置表单提交选项
			var frmOptions = {
				target:        this.options.result_div,   
				dataType:  	   'json', 
				timeout : 3000,
			    beforeSubmit:  function(){return that.validate_form();}, 									//提交前验证 
			    success:       function(data){that.saveorupdate_handle(data);} 						//提交后处理
			};
			
			//2, 绑定表单提交
			$(this.options.submitBtn).click(function(){
				require(['jqajaxform'],function(form){
					$(that.options.save_update_form, that.$ele).ajaxSubmit(frmOptions);
				});
			})
			
			//3, 表单组成部分的显示与隐藏
			$('.showhide').on('click', function (eventObj) {
                var ff = $('fieldset', $(eventObj.currentTarget).parent().parent());
                if (ff.css('display') == 'none') {
                    ff.css('display', 'block');
                    $(eventObj.currentTarget).text('隐藏');
                } else {
                    ff.css('display', 'none');
                    $(eventObj.currentTarget).text('显示');
                }
            })
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
			require(['jqpaginator'],function(pg){
				pg("#pagination").jqPaginator({
		            totalPages: that.options.totalPages,
		            visiblePages: 8,
		            currentPage:   that.options.currentPage,
		            first: '<li class="first"><a href="javascript:void(0);">首页<\/a><\/li>',
		            prev: '<li class="prev"><a href="javascript:void(0);"><i class="arrow arrow2"><\/i>上一页<\/a><\/li>',
		            next: '<li class="next"><a href="javascript:void(0);">下一页<i class="arrow arrow3"><\/i><\/a><\/li>',
		            last: '<li class="last"><a href="javascript:void(0);">末页<\/a><\/li>',
		            page: '<li class="page"><a href="javascript:void(0);">{{page}}<\/a><\/li>',
		            onPageChange: function (page,t) {
		            	if('change' == t){
		            		$(that.options.pagination_index, that.$ele).val(page-1);
							//当属性reset为0时表示表单实际提交时不重置页码，用于当通过按钮提交表单时要重置页码，通过分页提交时不用重置
							$(that.options.pagination_index, that.$ele).attr('reset',0);	
							$(that.options.query_form,that.$ele).submit();
		            	}
		            }
		        });
			});
			
			//2,重置页码 
			$(this.options.query_form).submit(function(){
				if( $(that.options.pagination_index, that.$ele).attr('reset') != 0 ){
					$(that.options.pagination_index, that.$ele).val("0");
				}
				return true;
			});
			
			//3, 所有view行事件
			if(this.options.row_click){
				$('tr[view]',this.$ele).bind('click',function(event){
					var tagName = event.target.tagName;
					if( tagName == 'A' || tagName == 'a' || tagName == 'input' || tagName == 'INPUT' || tagName == 'span' || tagName == 'SPAN' ){
						return true;
					}
					window.location.href = that.options.base_path + $(event.currentTarget).attr('view');
					return true;
				});
			}
			
			//4,初始化权限操作
			$('.list_oper').click(function(e){
				that.operator($(this).attr('ename') ,$(this).attr('href'), e );
			});
        },
        
        
        /**
		 * 权限操作
		 * @res 操作资源
		 * @url 操作的url
		 * return : true表示通过链接生效, false表示不处理链接
		 */
		operator:function(res, url, e){
			if(url == null || url == '' || res == null || res == ''){
				return false;
			}
			if( res.endWith('disable')){
				stopDefaultEvent(e);
				return this.disable(res,url);
			}else if( res.endWith('enable')){
				stopDefaultEvent(e);
				return this.enable(res,url);
			}else{
				return true;
			}
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
	        		alert('禁用操作失败');
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
	        		alert('启用操作失败');
	        		return false;
	        	}
	        });
			return false;
		},
        
        
        /**
         * 验证表单数据
         */
        validate_form : function(){
        	$(this.options.error_div, this.$ele).html("");
        	return $(this.options.save_update_form, this.$ele).isValid();
        },
        
        
        /**
         * 保存或更新数据后的处理
         */
        saveorupdate_handle : function(data){
        	if(this.options.saveorupdate_handle != null){
        		this.options.saveorupdate_handle(data);
        		return;
        	}
        	
        	if(data.flag  == "success"){
        		require(['jqlayer'], function(layer){
        			layer.confirm('保存或更新数据成功！', {
            		    btn: ['确定'] //按钮
            		}, function(){
            		   // layer.msg('的确很重要', {icon: 1});
            			if( document.referrer && document.referrer != ''){
            				location.href=document.referrer
            			}else{
            				window.history.back();
            			}
            		});
        		})
			}else{
				$(this.options.error_div,this.$ele).html(data.errors);
				$("html,body").animate({scrollTop: $(this.options.error_div, this.$ele).offset().top}, 500);
			}
        	return false;
        }
    });

    module.exports = Business;

});