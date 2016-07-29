<%--
  Created by IntelliJ IDEA.
  User: liubing-ds3
  Date: 2016/5/17
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<%
    String path = request.getContextPath();
    String context = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path ;

%>
<head>
    <script> var BASE_URL = "<%=context%>";</script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>登录</title>
    <link rel="stylesheet" type="text/css"  href='<s:url value="/static/css/bootstrap.min.css"/>'>
    <link rel="stylesheet" type="text/css"  href='<s:url value="/static/css/bootstrap-theme.min.css"/>'>
    <style type="text/css">
        .container { width: 970px !important; }
        .login-icon { top: 1000px; }
        .login-form { margin-top: 56px; }
        .demo-type-example { color: #48C9B0; text-align: center; }
    </style>
</head>
<body>
<div class="container">
    <div class="login">
        <div class="login-screen">
            <div class="login-icon">
                <h2>高管报表权限 <small>管理系统</small></h2>
            </div>
            <div class="login-form">
                <form id="login-form">
                    <div class="form-group">
                        <input type="text" class="form-control login-field" placeholder="请输入您的用户名" id="login-name" name="username" />
                        <label class="login-field-icon fui-user" for="login-name"></label>
                    </div>

                    <div class="form-group">
                        <input type="password" class="form-control login-field" placeholder="请输入您的密码" id="login-pass" name="password" />
                        <label class="login-field-icon fui-lock" for="login-pass"></label>
                    </div>
                    <a href="#" class="btn btn-primary btn-lg btn-block" id="login-submit">登录</a>
                    <div class="demo-type-example">
                        <h5 id="login_error_info"></h5>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- javascript -->
<script type="text/javascript" src='<s:url value="/static/js/jquery.min.js"/>'></script>
<script src="<%=context%>/static/js/comm.js"></script>
<script type="text/javascript">
   $(function(){

        // 用户登录
        $('#login-submit').click(function () {
            var username = $('#login-name').val();
            var password = $('#login-pass').val();
            if (username && password) {
                $("#login_error_info").html("登录中,请稍候...");
                var params = $("#login-form").serialize();
                $.ajax({
                    url : F.url("/login/login.do"),
                    type : "post",
                    dataType : 'json',
                    data : params,
                    success : function (data) {
                        if(data.status){
                            window.location.href = "index.do";
                        } else {
                            $('#login-name').focus();
                            $("#login_error_info").html("用户名或登录密码错误");
                        }
                    },
                    error : function (XMLHttpRequest, textStatus, errorThrown) {
                        $('#login-name').focus();
                        $("#login_error_info").html("登录超时");
                    }
                });
            } else {
                $('#login-name').focus();
                $("#login_error_info").html("请输入用户名或密码");
            }
        });
    });
</script>
</body>
</html>