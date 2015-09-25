/**
 * 初始化应用配置
 */
seajs.config({
	  base: "http://localhost:8087/resources/js/",
	  alias: {
	    "$": "jquery/jquery/1.10.1/jquery.js",
	    "bootstrap" : "gallery/bootstrap/3.0.0/bootstrap.js",
	    "bootstrappaginator" : "gallery/bootstrappaginator/0.5.0/bootstrappaginator.js",
	    "bootstrapvalidator":"gallery/bootstrapvalidator/1.0.3/bootstrap3validator.js",
	    "juicer" : "gallery/juicer/0.6.5/juicer.js",
	    "treetable" : "jquery/mytreetable/1.4.2/treeTable.js",
	    "bootbox" : "gallery/bootbox/4.2.0/bootbox.js",
	    "form" : "jquery/form/3.50.0/form.js",
	    "my97date" : "gallery/my97date/4.8.0/WdatePicker.js",
	    "organ" : "appuser/component/organ",
	    "system" : "appuser/component/system",
	    "resource" : "appuser/component/resource",
	    "popedom" : "appuser/component/popedom"
	  }
});