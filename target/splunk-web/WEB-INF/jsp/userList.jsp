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
    <title>用户列表</title>
    <%
        String path = request.getContextPath();
        String context = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path ;
    %>
    <script> var BASE_URL = "<%=context%>";</script>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="<%=context%>/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=context%>/static/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="<%=context%>/static/css/ui-dialog.css">
    <!--webuploader样式-->
    <link rel="stylesheet" href="<%=context%>/static/js/webuploader/webuploader.css">
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
<div class="con-padding" style="width:100%;" ng-controller="userController">
    <!--条件选择-->
    <div class="select_big clearfix mb20">
        <%--<div class="dropdown">
           <input class="btn btn-default dropdown-toggle" type="text" id="nickname" placeholder="请输入用户名" data-toggle="dropdown" aria-expanded="true"    ng-model="nickname">
           </input>

       </div>
       <a class="button mt2" ng-click="getSearchContent()">查询</a>--%>
        <div class="add_new_strategy">
            <a class="button has_m_btn pos_popup" ng-href="<%=context%>/splunk/user/exportUserBindRoles.do">导出角色关系</a>
        <%--    <div class="button has_m_btn pos_popup" id="picker">导入</div>--%>
            <a class="button has_m_btn pos_popup" ng-click="createAccount()">添加</a>
        </div>
    </div>
    <!--账户列表-->
    <div class="box mb20">
        <div class="box">
            <div class="recommend_table">
                <ng-table>
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <thead class="title_bg">
                        <tr>
                         <%--   <td width="50px"><input type="checkbox" class="checkbox" /></td>--%>
                            <td>员工号</td>
                            <td>域用户</td>
                            <td>用户名</td>
                            <td>中心</td>
                            <td>部门</td>
                            <td>MAC地址</td>
                            <td>更新时间</td>
                            <td width="240px">操作</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="change_bg_tr" ng-repeat="user in users">
                           <%-- <td width="50px"><input type="checkbox" class="checkbox" /></td>--%>
                            <td>{{user.uno}}</td>
                            <td>{{user.username}}</td>
                            <td>{{user.nickname}}</td>
                            <td>{{user.center}}</td>
                            <td>{{user.department}}</td>
                            <td>{{user.phone_mac}}</td>
                            <td>{{user.update_time | date:"yyyy-MM-dd" }}</td>
                            <td class="nowrap">
                                <a class="grey" ng-click="getEdit(user.id)">编辑</a>
                                <span class="recommend_table_span">|</span>
                                <a class="red" ng-click="getRemove(user.id)">删除</a>
                                <span class="recommend_table_span">|</span>
                                <a class="grey" ng-click="bindUserRole(user.id)">分配角色</a>
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
<!--webuploader-->
<script src="<%=context%>/static/js/webuploader/webuploader.noimage.min.js"></script>
<!--ngDialog-->
<script src="<%=context%>/static/js/angular/ngDialog/ngDialog.min.js"></script>
<!--引入dialog的js-->
<script src="<%=context%>/static/js/dialog-min.js"></script>
<!--自定义js -->
<script src="<%=context%>/static/js/comm.js"></script>
<script src="<%=context%>/static/splunk/user.js"></script>
</body>
</html>


