/**
 * web插件js
 */
$(function(){
	
	
	$('.test').click(function(){
		
		var itemId =  $(this).attr('itemid') ;
		var url =  $('#web_url').val();
		
		$.ajax({
			  type: 'POST',
			  url: "/plugins/web/test.json",
			  data: "itemId=" + itemId + "&url=" + url,
			  dataType: "json",
			  timeout : 30000,
			  success: function(data){
				  alert('success');
			  },
			  complete:function(xhr, ts){
				  alert('complete');
			  },
			  error:function(xhr,err_info,ex){
				  alert('error');
			  }
			});
		
	});
	
});