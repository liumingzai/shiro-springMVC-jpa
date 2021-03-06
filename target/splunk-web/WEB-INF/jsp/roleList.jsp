<%--
  Created by IntelliJ IDEA.
  User: liubing-ds3
  Date: 2016/5/13
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html ng-app="myApp">


<head>
    <title>角色列表</title>
    <%
        String path = request.getContextPath();
        String context = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path ;

    %>
    <script> var BASE_URL = "<%=context%>";</script>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="<%=context%>/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=context%>/static/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="<%=context%>/static/css/ui-dialog.css">
    <!-- ngDialog -->
    <link rel="stylesheet" href="<%=context%>/static/js/angular/ngDialog/css/ngDialog.min.css">
    <link rel="stylesheet" href="<%=context%>/static/js/angular/ngDialog/css/ngDialog-theme-default.min.css">
    <link rel="stylesheet" href="<%=context%>/static/js/angular/ngDialog/css/ngDialog-theme-plain.min.css">
    <!-- 自定义样式 -->
    <link rel="stylesheet" href="<%=context%>/static/css/base.css">
    <link rel="stylesheet" href="<%=context%>/static/css/styles.css">
</head>
<body>
<!--右侧内容-->
<div class="con-padding" style="width:100%;" ng-controller="roleController">
    <!--条件选择-->
    <div class="select_big clearfix mb20">
        <%-- <div class="dropdown">
             <input class="btn btn-default dropdown-toggle" type="text" id="name" placeholder="请输入角色名" data-toggle="dropdown" aria-expanded="true"    ng-model="name">
             </input>
         </div>
         <a class="button mt2" ng-click="getSearchContent()">查询</a>--%>
        <div class="add_new_strategy">
            <a class="button has_m_btn pos_popup" ng-click="createRole()">添加</a>
        </div>
    </div>
    <!--用户列表-->
    <div class="box mb20">
        <div class="box">
            <div class="recommend_table">
                <ng-table>
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <thead class="title_bg">
                        <tr>
                          <%--  <td width="50px"><input type="checkbox" class="checkbox" /></td>--%>
                            <td>角色名</td>
                           <%-- <td>角色类型</td>--%>
                            <td>更新时间</td>
                            <td width="240px">操作</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="change_bg_tr" ng-repeat="role in roles">
                           <%-- <td width="50px"><input type="checkbox" class="checkbox" /></td>--%>
                            <td>{{role.name}}</td>
                          <%--  <td>{{role.type}}</td>--%>
                            <td>{{role.update_time | date:"yyyy-MM-dd" }}</td>
                            <td class="nowrap">
                                <a class="grey" ng-click="getEdit(role.id)">编辑</a>
                                <span class="recommend_table_span">|</span>
                                <a class="red" ng-click="getRemove(role.id)">删除</a>
                                <span class="recommend_table_span">|</span>
                                <a class="grey" ng-click="bindRoleResource(role.id)">分配资源</a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </ng-table>
            </div>
            <!--翻页-->
            <ng-pagination total_page="page.totalPage"
                           total_ele="page.total"
                           curr_page="page.page"
                           to_page="toPage"
                           sort='page.sort'
                           slicer="[50]"
                           template="${pageContext.request.contextPath}/static/js/angular/ngPagination/template/paginationTemplate_front.html"
            />
        </div>
    </div>
</div>
</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="<%=context%>/static/js/jquery.min.js"></script>
<script src="<%=context%>/static/js/layer-v2.0/layer.js"></script>

<script src="<%=context%>/static/js/bootstrap.min.js"></script>
<!-- angular插件 -->
<script src="<%=context%>/static/js/angular.js"></script>
<!-- AngularJS分页 -->
<script src="<%=context%>/static/js/angular/ngPagination/ngPagination_bitch.js"></script>
<!--ngDialog-->
<script src="<%=context%>/static/js/angular/ngDialog/ngDialog.min.js"></script>
<!--引入dialog的js-->
<script src="<%=context%>/static/js/dialog-min.js"></script>
<!--自定义js -->
<script src="<%=context%>/static/js/comm.js"></script>
<script src="<%=context%>/static/splunk/role.js"></script>
</body>
</html>


