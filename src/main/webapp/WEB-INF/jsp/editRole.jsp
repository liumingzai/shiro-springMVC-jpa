<%--
Created by IntelliJ IDEA.
User: liubing
Date: 2016/06/03
编辑角色模块
Time: 16:11
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    label{font-size: 12px;line-height: 30px;}
    .add_account input.text {font-size: 12px;float: left;}
    .add_account label {width: 80px;}
    .radio_popup_p span {font-size: 12px;line-height: 30px;}
    #myForm{width: 465px;text-align: center}
    .ngdialog.ngdialog-theme-plain .ngdialog-content {width: 438px}
    .center_btn_a {text-align: center}
    .center_btn_a a {margin: 10px 10px 10px 0;}
    .popup_title {padding: 0 0 15px 5px;text-align: left}
    .red_note {font-size: 12px; display: block;text-align:left; margin: 35px 0 0 100px;}
    .dropdown .btn-default {width: 300px;float: left;}
    .input_btn {border: none;margin: 10px 10px 10px 0;}
    .btn-default:hover, .btn-default:focus, .btn-default:active, .btn-default.active, .open > .dropdown-toggle.btn-default {
        background-color: #fff;
        border-color: #adadad;
        color: #333;
    }
   </style>

    <form id="myForm" name="myForm" role="role" ng-submit="saveBtn()" novalidate>
        <h4 class="popup_title">编辑角色</h4>
            <div class="add_account clearfix mb10">
                <label>角色名<span style="color:red">*</span>：</label>
                <input class="text" type="text" ng-model="name"  name="name" required/>
                <div class="red_note" style="color:red" ng-show="myForm.name.$dirty && myForm.name.$invalid">
                   <span ng-show="myForm.name.$error.required">角色名必填</span>
                </div>
            </div>
            <div class="dropdown add_account clearfix mb10">
                <label>角色类型：</label>
                <input class="btn btn-default dropdown-toggle" type="text" id="type_name" data-toggle="dropdown" placeholder=" 请输入类型名"  aria-expanded="true" name="type_name"  ng-model="type_name"/>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu4">
                    <li role="presentation" ng-repeat="type in types| filter:type_name"><a role="menuitem" tabindex="-1" href="#" ng-click="getTypeName(type)" ng-bind="type.name"></a></li>
                </ul>
            </div>
        <div class="center_btn_a clearfix">
            <input class="button has_m_btn input_btn" type="submit" ng-disabled="myForm.name.$dirty &&myForm.name.$invalid" value="保存"/>
            <a class="button grey_button" href="#" ng-click="closed()">取消</a>
        </div>
    </form>


