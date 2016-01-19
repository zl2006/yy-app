<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>开店助手-互联网营销</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <%@ include file="/WEB-INF/pages/common/head.jsp"%>
    <link rel="stylesheet" href='${applicationScope.pageConfig["resources.path"] }/app/user/css/site/index.css'>
</head>
<body>
 <%@ include file="/WEB-INF/pages/common/top.jsp"%>
 <%@ include file="/WEB-INF/pages/common/menu.jsp"%>


<!--广告区域-->
<div id="banner">
    <img src='${applicationScope.pageConfig["resources.path"] }/app/user/images/demo/index_banner.png' width="100%">
</div><!--banner end-->


<!-- 内容区-统计,公告,动态-->
<div id="cnt-statis">

    <div class="statis-group"><!--统计组-->
        <div class="statis"><!--统计-->
            <div class="title">
                <img src='${applicationScope.pageConfig["resources.path"] }/app/user/images/site/statis_logo.jpg'>
                <a>活动统计</a>
            </div>
            <div class="content">
                <a><label>活动:</label> 总计（６）&nbsp;&nbsp;进行中（２）&nbsp;&nbsp;即将过期（１）</a>
                <a><label>会员:</label> 总计（23.5W）&nbsp;&nbsp;&nbsp;昨日新增（1000）<img src='${applicationScope.pageConfig["resources.path"] }/app/user/images/site/up.jpg' style="vertical-align: -6px" alt="">　</a>
              <a><label>参与:</label> 总计（436W）&nbsp;&nbsp;&nbsp;昨日新增（8234）<img src='${applicationScope.pageConfig["resources.path"] }/app/user/images/site/up.jpg' style="vertical-align: -6px"  alt="">  </a>
            </div>
            <div class="oper">
                <button class="pure-button pure-button-primary">策划活动</button>
                <a class="pure-button button-warning" href="activity/list.html">活动列表</a>
            </div>
        </div><!-- statis end-->
        <div class="dynamic"><!--动态-->
            <div class="title">
                <img src='${applicationScope.pageConfig["resources.path"] }/app/user/images/site/dynamic_logo.jpg'>
                <a>会员动态</a>
                <img src='${applicationScope.pageConfig["resources.path"] }/app/user/images/site/refresh.jpg' style="float:right">
            </div>
            <div class="content">
                <a>逍遥***子参与春节扫码拿红包活动。</a>
                <a>杨***过成为会员。</a>
                <a>小龙***女中5元红包一个。</a>
                <a>东***邪中参与冰泉抽IPhone活动。</a>
                <a>西***毒会员签到啦</a>
            </div>
        </div><!-- dynamic end-->
    </div>
    <div class="notice"><!--公告-->
        <div class="title">
            <img src='${applicationScope.pageConfig["resources.path"] }/app/user/images/site/notice_logo.jpg'>
            <a>系统公告</a>
        </div>
        <div class="content">
            <a class="import">码互动【光棍上树】游戏上线啦，欢迎体验！</a>
            <a class="import">亲，您订购的码营销【预抢购】即将到期啦！</a>
            <a>小豆教你玩疯码世界，准备好接招了吗！</a>
            <a>码世界深圳区商盟活动快开始啦，点击报名！</a>
        </div>
    </div><!--notice end-->
</div><!-- cnt-statis end -->



<!--内容区-活动区-->
<table width="100%" id="cnt-activity" class="pure-table">
    <thead>
    <tr>
        <th>活动名称</th>
        <th>进度</th>
        <th>参与人次</th>
        <th>扫码总量</th>
        <th>扫码日均量</th>
        <th>奖池</th>
    </tr>
  </thead>
    <tbody>
    <tr>
        <td><a class="name">蓄水抽IPhone</a></td>
        <td>
            <div class="progress">
                80%，45天
            </div>
            <div class="progress-bar">
                <div class="progress-left" style="width:80%;"></div>
                <div class="progress-right" style="width:20%;"></div>
            </div>
        </td>
        <td>133,333人</td>
        <td>123231次</td>
        <td>34次</td>
        <td>总量（345）,派发（333）</td>
    </tr>

    <tr class="odd">
        <td><a class="name">大转盘抽奖</a></td>
        <td class="progress">
            <div class="progress">
                70%，30天
            </div>
            <div class="progress-bar">
                <div class="progress-left" style="width:70%;"></div>
                <div class="progress-right" style="width:30%;"></div>
            </div>
        </td>
        <td>3242,344人</td>
        <td>3895次</td>
        <td>92次</td>
        <td>总量（345）,派发（333）</td>
    </tr>

    <tr>
        <td><a class="name">38节，5折优惠券限量派送...</a></td>
        <td class="progress">
            <div class="progress">
                20%，30天
            </div>
            <div class="progress-bar">
                <div class="progress-left" style="width:20%;"></div>
                <div class="progress-right" style="width:80%;"></div>
            </div>
        </td>
        <td>32,676人</td>
        <td>99078次</td>
        <td>96次</td>
        <td>总量（345）,派发（333）</td>
    </tr>

    <tr class="odd">
        <td><a class="name">大转盘抽奖</a></td>
        <td class="progress">
            <div class="progress">
                20%，30天
            </div>
            <div class="progress-bar">
                <div class="progress-left" style="width:20%;"></div>
                <div class="progress-right" style="width:80%;"></div>
            </div>
        </td>
        <td>21312,222人</td>
        <td>3455次</td>
        <td>78次</td>
        <td>总量（345）,派发（333）</td>
    </tr>

    <tr>
        <td><a class="name">大转盘抽奖</a></td>
        <td class="progress">
            <div class="progress">
                20%，30天
            </div>
            <div class="progress-bar">
                <div class="progress-left" style="width:20%;"></div>
                <div class="progress-right" style="width:80%;"></div>
            </div>
        </td>
        <td>21312,222人</td>
        <td>34655次</td>
        <td>99次</td>
        <td>总量（345）,派发（333）</td>
    </tr>
    </tbody>
</table><!-- cnt-activity end-->



<!-- 内容区-模板区 -->
<div id="cnt-template">
    <div class="header">
        <label class="title">活动模板</label>
        <ul>
            <li>溯源</li>
            <li>营销</li>
            <li>互动</li>
            <li>游戏</li>
        </ul>
        <a class="more"> >>更多</a>
    </div>
    <div class="content">
        <ul>
            <li>
                <img src='${applicationScope.pageConfig["resources.path"] }/app/user/images/demo/template.jpg' alt="">
                <div class="info">
                    扫码溯源<br>
                    行业:饮料
                </div>
            </li>
            <li>
                <img src='${applicationScope.pageConfig["resources.path"] }/app/user/images/demo/template1.jpg' >
                <div class="info">
                    扫码溯源<br>
                    行业:饮料
                </div>
            </li>
            <li>
                <img src='${applicationScope.pageConfig["resources.path"] }/app/user/images/demo/template.jpg' >
                <div class="info">
                    扫码溯源<br>
                    行业:饮料
                </div>
            </li>
            <li>
                <img src='${applicationScope.pageConfig["resources.path"] }/app/user/images/demo/template1.jpg' >
                    <div class="info">
                    扫码溯源<br>
                    行业:饮料
                </div>
            </li>
            <li>
                <img src='${applicationScope.pageConfig["resources.path"] }/app/user/images/demo/template.jpg' >
                <div class="info">
                    扫码溯源<br>
                    行业:饮料
                </div>
            </li>
            <li>
                <img src='${applicationScope.pageConfig["resources.path"] }/app/user/images/demo/template1.jpg'>
                <div class="info">
                    扫码溯源<br>
                    行业:饮料
                </div>
            </li>
        </ul>
    </div>
</div><!--cnt-template end-->
 <%@ include file="/WEB-INF/pages/common/footer.jsp"%>
<script type="text/javascript">
    requirejs(['jqsuperslide','sticky'], function (slide,sticky) {
        sticky("#menu", {top:0, left:0});
        slide("#site-menu").slide({ type: "menu", titCell: ".menu-item", targetCell: ".menu-item-sub", delayTime: 400, triggerTime: 0, returnDefault: false  });
    })
</script>
</body>
</html>