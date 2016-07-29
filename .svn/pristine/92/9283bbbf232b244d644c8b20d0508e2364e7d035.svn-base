<%--
  Created by IntelliJ IDEA.
  User: liubing-ds3
  Date: 2016/5/13
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>登录成功</title>
    <base href="${CONTEXT_PATH}/">
    <script type="text/javascript">if (window != top) { top.location.href = window.location.href; }</script>
</head>
<body>
    <a href="${CONTEXT_PATH}/index.do">进入Index页面</a><p />
    <a href="${CONTEXT_PATH}/login.do">进入Admin页面</a><p />
    <a href="${CONTEXT_PATH}/error.do">进入NoAuth页面</a><p />
    <a href="${CONTEXT_PATH}/logout.do">退出</a><p />

    <h1>${USER.nickname}:${USER.username}</h1>

  <%--  登录之后:<span style="color:red;"><@shiro.authenticated>true</@shiro.authenticated></span><br/>
    不在登录状态时:<span style="color:red;"><@shiro.notAuthenticated>true</@shiro.notAuthenticated></span><br/>
    显示用户登录名:<@shiro.authenticated><span style="color:red;"><@shiro.principal/></span></@shiro.authenticated><br/>
    用户在没有RememberMe时:<span style="color:red;"><@shiro.guest>true</@shiro.guest></span><br/>
    用户在RememberMe时:<span style="color:red;"><@shiro.user>true</@shiro.user></span><br/>
    拥有角色admin:<span style="color:red;"><@shiro.hasRole name="admin">true</@shiro.hasRole></span><br/>
    没有角色admin:<span style="color:red;"><@shiro.lacksRole name="admin"> true</@shiro.lacksRole></span><br/>
    在有admin或者123角色时:<span style="color:red;"><@shiro.hasAnyRoles name="admin,123" >true</@shiro.hasAnyRoles></span><br/>
    拥有权限INSERT:<span style="color:red;"><@shiro.hasPermission name="INSERT">true</@shiro.hasPermission></span><br/>
    没有权限INSERT:<span style="color:red;"><@shiro.lacksPermission name="INSERT">true</@shiro.lacksPermission></span><br/>--%>
</body>
</html>
