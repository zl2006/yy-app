/**
 * 初始化页面权限链接
 * 
 * @author zhouliang
 * @version	0.1
 * @create 2014-3-6
 */
define(function(require, exports, module){
	
	//加载组件
	var $ = require('$');

	module.exports = {
			
		/**
		 * 初始化权限标签
		 * @selector 	权限标签选择器
		 * @opers		权限列表
		 * @callback 	点击操作的回调函数
		 * @action		action中有实际的动作，避免callback函数中this指针的不正常
		 */
		init : function(selector, opers, callback, action){
			
			var that = this;
			
			//处理所有的权限标签
			$(selector).each(function(idx, obj){
				var $obj = $(obj);
				if($obj.attr('condition') == "1"){	//当权限标签满足条件时显示
					var res = that.getOperByResID(opers, $obj.attr('resID'));	
					if( res ){	//判断是否有操作
						$obj.html(res.name);
						
						if( res.url.indexOf('?') >= 0){
							$obj.attr('href',res.url + "&resID=" + $obj.attr('resID') + "&" + $obj.attr('params'));
						}else{
							$obj.attr('href',res.url + "?resID=" + $obj.attr('resID') + "&" + $obj.attr('params'));
						}
					}
				}else{
					$obj.remove();
				}
			});
			$(selector).click(function(obj){
				if(callback && $.isFunction(callback)){
					var res = that.getOperByResID(opers, $(obj.currentTarget).attr('resid'));	
					return callback.call(action, res, $(obj.currentTarget).attr('href'), action);
				}
			});
		},
		
		/**
		 * 根据操作id从列表中获取指定的操作
		 * [{"createPerson":1,"createTime":1394435517000,"description":"编辑","icon":"none","name":"编辑","orderNO":1,"parentResID":14,"resID":19,"status":1,"systemCode":"USER_SYSTEM","type":"2","updatePerson":1,"updateTime":1384139471000,"url":"http://localhost:8087/user/organ/preEdit.do"},{"createPerson":1,"createTime":1394435517000,"description":"禁用","icon":"none","name":"启用","orderNO":2,"parentResID":14,"resID":20,"status":1,"systemCode":"USER_SYSTEM","type":"2","updatePerson":1,"updateTime":1384139471000,"url":"http://localhost:8087/user/organ/disable.do"},{"createPerson":1,"createTime":1394435517000,"description":"启用","icon":"none","name":"禁用","orderNO":3,"parentResID":14,"resID":21,"status":1,"systemCode":"USER_SYSTEM","type":"2","updatePerson":1,"updateTime":1384139471000,"url":"http://localhost:8087/user/organ/enable.do"}]
		 */
		getOperByResID:function(opers, resID){
			var idx = 0;
			var result = null;
			for(; idx < opers.length; ++idx){
				if( opers[idx].resID == resID){
					result = opers[idx];
					break;
				}
			}
			return result;
		}
	};//exports
});