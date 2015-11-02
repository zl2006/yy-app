requirejs.config({
    baseUrl: '/resources/js/',
    paths: {
        /*插件*/
        'css' : 'require/plugins/css.min',                          //require加载css的插件
        //'cs' : 'require/plugins/cs',
        'domReady' : 'require/plugins/domReady.min',                //require文档准备好后调用的插件
        'i18n' : 'require/plugins/i18n.min',                        //require国际化插件
        'text' : 'require/plugins/text.min',                        //require加载文本文件的插件

        /*基础组件*/
        'class' : 'modules/arale-class/1.2.0/class.min',            //类组件，实现继承等操作
        //'attribute' : 'modules/arale-base/1.2.0/attribute.min',     //属性组件
        //'aspect' : 'modules/arale-base/1.2.0/aspect.min',           //切面组件
        //'events' : 'modules/arale-events/1.2.0/events.min',         //事件组件
        'base' : 'modules/arale-base/1.2.0/base.min',               //面向对象的基础组件，使用非min时依赖class、attribute、aspect、events

        'cookie' : 'modules/arale-cookie/1.1.0/cookie.min',             //cookie操作工具类
        'url' : 'modules/url/1.2.0/url.min',                            //url组件
        'platform' : 'modules/platform/1.2.0/platform.min',             //获取浏览见面的当前平台信息
        'json3' : 'modules/json3/3.3.2/json3.min',                      //json与对象操作组件
        'juicer' :  'modules/juicer/0.6.5/juicer.min',                  //模板组件

        //'auto-render' : 'modules/arale-widget/1.2.0/auto-render.min',   //自动渲染组件
        //'daparser' : 'modules/arale-widget/1.2.0/daparser.min',
        'widget' : 'modules/arale-widget/1.2.0/widget.min',             //编写UI组件的基础类，使用非min时依赖auto-render,daparser
        'dnd' : 'modules/arale-dnd/1.1.0/dnd.min',                      //drop、drag事件处理
        'position' : 'modules/arale-position/1.1.0/position.min',       //元素定位组件
        'overlay' : 'modules/arale-overlay/1.2.0/overlay.min',          //浮动层基础组件,支持定位和select元素bug
        'mask' : 'modules/arale-overlay/1.2.0/mask.min',                 //浮动层基础组件,带遮照层

        'sticky' : 'modules/arale-sticky/1.4.0/sticky.min',             //固定元素组件，使其不随文档滚动而移动
        'keymaster' : 'modules/keymaster/1.6.2/keymaster.min',          //键盘事件监听组件
        'easing' : 'modules/arale-easing/1.1.0/easing.min',             //动画效果

        /*界面 UI组件*/
        'jqlazy' : 'modules/jqlazy/1.0.2/jqlazy.min',                  				 //图片延迟加载组件
        'qrcode' : 'modules/arale-qrcode/1.1.0/qrcode.min',             //二维码生成组件
        'jquery' : 'jquery-amd/1.11.1/jquery.min',                      				//jquery工具类
        'jqpaginator' : 'jqpaginator-amd/1.2.0/jqPaginator.min',        //分页组件
        'jqvalidator' : 'jqvalidator-amd/0.7.3/jquery.validator',       //验证组件
        'jqsuperslide' : 'jqsuperslide-amd/2.1.0/jqsuperslide',       //验证组件
        'jqtreetable':'jqmytreetable-amd/1.4.2/treeTable',				//树形表格

        'jqajaxform' : 'jqajaxform-amd/3.51.0/jqajaxform.min',          //form表单异步提交组件
        'jqlayer' : 'jqlayer/2.0/layer',        											  //弹出窗口
        'dialog5' : 'artdialog-amd/5.0.4/artDialog.min',                //对话框组件
        'dialog5-plugins' : 'artdialog-amd/5.0.4/artDialog.plugins.min',
        'dialog6' : 'modules/artdialog/6.0.4/dialog6.min',
        'dialog6-plus' : 'modules/artdialog/6.0.4/dialog6-plus.min',
        

        /**测试demo*/
        'register' : "app-user/register/1.0.0/register",
        
        
        /**app业务层组件*/
        'business' : 'app-common/business/1.0/business',					//增删改查页通用处理逻辑
        'organ_business' : 'app-user/business/organ_business',		//组织机构列表页处理逻辑
        'res_business' : 'app-user/business/res_business',					//资源列表页处理逻辑
        'organ' : 'app-user/component/organ',										//组织机构弹出框
        'system' : 'app-user/component/system',									//应用系统弹出框
        'resource' : 'app-user/component/resource'									//应用系统弹出框
    },
    shim: {
        'dialog5-plugins': {
            deps: ['dialog5'],
            exports: 'art'
        },
        'jqsuperslide': {
            deps: ['jquery'],
            exports: 'jQuery'
        },
        'jqpaginator': {
            deps: ['jquery'],
            exports: 'jQuery'
        },
        'jqvalidator': {
            deps: ['jquery'],
            exports: 'jQuery'
        }
    }
});