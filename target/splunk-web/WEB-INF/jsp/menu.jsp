<%--
  Created by IntelliJ IDEA.
  User: liubing
  Date: 2015/11/25
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="myApp">
<%
  String path = request.getContextPath();
  String context = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path ;
%>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>splunk 权限管理</title>

  <!-- Bootstrap -->
  <link rel="stylesheet" href="<%=context%>/static/css/bootstrap.min.css">
  <link rel="stylesheet" href="<%=context%>/static/css/bootstrap-theme.min.css">

  <!-- 自定义样式 -->
  <link rel="stylesheet" href="<%=context%>/static/css/base.css">
  <link rel="stylesheet" href="<%=context%>/static/css/styles.css">

</head>
<body class="position_body" ng-controller="menuController" style="width: 100%;height: 100%;overflow: hidden;overflow:-Scroll;overflow-y:hidden">
<div style="width: 101.2%;height: 100%;overflow-y: auto;">
  <!--头部加载-->
  <jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
  <!--主体-->
  <div class="main" >
    <!--菜单-->
    <div class="menu" ng-init="mnueShow=true">
      <div class="contraction">
        <a></a>
      </div>
      <div class="right_unfold_tb"></div>
      <ul class="menu-list" ng-show="mnueShow">
        <li class="first_ul" id="l1">
          <p class="checkbox_title menu_nav clearfix"><a><i></i><span>权限管理</span></a></p>
          <dl class="unfold_box">
            <dt class="current"><a href="<%=context%>/splunk/index/userList.do" target="pageFrame"  ng-click="htitle='用户信息'">用户列表</a></dt>
            <dt><a href="<%=context%>/splunk/index/roleList.do" target="pageFrame" ng-click="htitle='角色信息'">角色列表</a></dt>
            <dt><a href="<%=context%>/splunk/index/resourceList.do" target="pageFrame" ng-click="htitle='角色信息'">资源列表</a></dt>
          </dl>
        </li>
      </ul>
    </div>
    <!--右侧内容-->
    <div class="content" style="padding-bottom:0">
      <iframe id="pageFrame" name="pageFrame" src="<%=context%>/splunk/index/userList.do" width="100%" height="105%" frameborder="0" scrolling="auto" onload="this.style.height=document.body.clientHeight-50"
              height="100%" mce_src="map.jsp"></iframe>
    </div>
  </div>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="<%=context%>/static/js/jquery.min.js"></script>
<!-- angular文件-->
<script src="<%=context%>/static/js/angular.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="<%=context%>/static/js/bootstrap.min.js"></script>
<script type="text/javascript">

  var menu = angular.module("myApp",[]);
  menu.controller("menuController", function ($scope) {

  });

  $(function() {
    /**
     * 切换导航菜单
     */
    $('.unfold_box a').click(function(){
      $(this).parent().addClass("current").siblings().removeClass();
      var pid = $(this).parent().parent().parent().attr("id");
      $(".menu-list li").each(function(index,item) {
        if ($(item).attr("id") != pid) {
          $(item).find("dt").removeClass();
        }
      });
    });

    $('.first_ul .checkbox_title').click(function(){
      $(this).find('i').toggleClass("shut_tb");
      $(this).parent().find('.unfold_box').slideToggle(300);
    });
  })

  //左侧菜单点击按钮收回展开
  $(".contraction a").click(function(){
    $('.main').animate({
      paddingLeft: '0'
    }, 'slow');
    $('.menu').animate({
      left: "-180px"
    }, 'slow');
    $('.right_unfold_tb').animate({
      left: "0"
    }, 'slow')
  });
  $('.right_unfold_tb').click(function(){
    $(this).animate({
      left: '-20px'
    }, 'slow');
    $('.main').animate({
      paddingLeft: '180px'
    }, 'slow');
    $('.menu').animate({
      left: "0"
    }, 'slow');
  })

</script>
</body>
</html>






