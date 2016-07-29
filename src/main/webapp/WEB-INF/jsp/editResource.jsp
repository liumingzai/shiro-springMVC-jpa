<%--
Created by IntelliJ IDEA.
User: liubing
Date: 2016/06/03
编辑资源模块
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
<form id="myForm" name="myForm" role="resource" ng-submit="saveBtn()" novalidate>
    <h4 class="popup_title">编辑资源</h4>
    <div class="add_account clearfix mb10">
        <label>资源名<span style="color:red">*</span>：</label>
        <input class="text" type="text" ng-model="rsname"  name="rsname" required/>
        <div class="red_note" style="color:red" ng-show="myForm.rsname.$dirty && myForm.rsname.$invalid">
            <span ng-show="myForm.rsname.$error.required">资源名必填</span>
        </div>
    </div>
    <div class="add_account clearfix mb10">
        <label>资源等级：</label>
        <p class="radio_popup_p">
            <input class="radio" type="radio"  value="0"  id="level0" name="level" ng-model="level"  ng-checked="getLevel(0)" />
            <span>顶级</span>
        </p>
        <p class="radio_popup_p">
            <input class="radio" type="radio"  value="1" id="level1"  name="level" ng-model="level"  ng-checked="getLevel(1)" />
            <span>子级</span>
        </p>
        <p class="radio_popup_p">
            <input class="radio" type="radio"  value="2"  id="level2" name="level" ng-model="level"  ng-checked="getLevel(2)"/>
            <span>操作级</span>
        </p>
    </div>
    <div class="dropdown add_account clearfix mb10" ng-show="level">
        <label>父节点：</label>
        <input type="hidden" name="parentid" ng-model="parentid" />
        <input class="btn btn-default dropdown-toggle" type="text"  data-toggle="dropdown" placeholder=" 请选父节点"  aria-expanded="true"  ng-model="parentName"  ng-change="parentid='';" required/>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu4" style="left: 100px;">
            <li role="presentation" ng-repeat="m in menus| filter:parentName"><a role="menuitem" tabindex="-1"  ng-click="getMenuName(m)" ng-model="m.name">{{m.name}}</a></li>
        </ul>
    </div>
    <div class="add_account clearfix mb10"  >
        <label>资源URL<span style="color:red">*</span>：</label>
        <input class="text" type="text" ng-model="url" name="url" required/>
        <div class="red_note" style="color:red" ng-show="myForm.url.$dirty && myForm.url.$invalid">
            <span ng-show="myForm.username.$error.required">url必填</span>
        </div>
    </div>
    <div class="center_btn_a clearfix" >
        <input class="button has_m_btn input_btn" type="submit" ng-disabled="myForm.rsname.$dirty &&myForm.rsname.$invalid||myForm.level.$dirty &&myForm.level.$invalid" value="保存"/>
        <a class="button grey_button" href="#" ng-click="closed()">取消</a>
    </div>
</form>


