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
    <title>修改密码</title>
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
                <h2>修改密码</h2>
            </div>
            <div class="login-form">
                <form id="login-form">
                    <div class="form-group">
                        <input type="password" class="form-control login-field" placeholder="请输入您的新密码" id="login-pass0" name="password0" />
                        <label class="login-field-icon fui-user" for="login-pass0"></label>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control login-field" placeholder="请确认您的密码" id="login-pass1" name="password1" />
                        <label class="login-field-icon fui-lock" for="login-pass1"></label>
                    </div>
                    <a href="#" class="btn btn-primary btn-lg btn-block" id="login-submit">提交</a>
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

        // 修改密码
        $('#login-submit').click(function () {
            var password0 = $('#login-pass0').val();
            var password1 = $('#login-pass1').val();
            if (checkFormValid(password0,password1)) {
                var params =  $("#login-form").serialize();
                $.ajax({
                    url : F.url("/newPwd.do"),
                    type : "post",
                    dataType : 'json',
                    data : params,
                    success : function (data) {
                        console.log(data);
                        if(data.status){
                            window.location.href = "login.do";
                        } else {
                            $('#login-pass0').focus();
                            $("#login_error_info").html("修改失败");
                        }
                    },
                    error : function (XMLHttpRequest, textStatus, errorThrown) {
                        console.log(errorThrown.message);
                        $('#login-pass0').focus();
                        $("#login_error_info").html("操作超时");
                    }
                });
            } else {
                $('#login-pass0').focus();
                $("#login_error_info").html("输入有误，请重新输入");
            }
        });

        function checkFormValid(password0,password1){
            if (isNullObj(password0) && isNullObj(password1)) {
                return false;
            }else if(password0 != password1){
                return false;
            }
            return true
        }

       function isNullObj(obj){
           for(var i in obj){
               if(obj.hasOwnProperty(i)){
                   return false;
               }
           }
           return true;
       }

    });
</script>
</body>
</html>