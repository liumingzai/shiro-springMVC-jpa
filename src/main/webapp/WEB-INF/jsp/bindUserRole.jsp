<%--
Created by IntelliJ IDEA.
User: liubing
Date: 2015/11/24
Time: 16:11
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .border_bottom {border-bottom: #ddd solid 1px;}
    .ngdialog.ngdialog-theme-plain .ngdialog-content {width: 500px;}
    .center_btn_a {text-align: center;}
    .center_btn_a a {margin: 10px 10px 10px 0;}
    .input_btn {border: none;margin: 10px 10px 10px 0;}
    .operation_people_list{width: 50%;}
    .operation_people_area {font-size: 12px;width: 100%;}
    .checkbox_title i {top: 6px;}
    .checkbox_title i.shut_tb {top: 6px;}
    .operation_people_arealist {float: left;}
    .operation_people_area input {margin-top: 3px;}
    .area_title .orange {color: #eb6100;}
    .box_con {text-align: center}
</style>
<div class="box">
    <div class="box_title clearfix">
        <b class="box_name">分配角色</b>
    </div>
    <div class="box_con p20">
        <div class="operation_people border_bottom pb20 clearfix">
            <div class="operation_people_area clearfix">
                <div class="operation_people_list border_rl pr10 pl10">
                    <div class="area_title clearfix">
                        <p class="orange" style="color: #eb6100;">用户角色</p>
                    </div>
                    <div class="operation_people_area p20">
                        <div class="first_ul">
                            <div class="unfold_box" style="padding-left:0;" >
                              <%--  <p class="checkbox_title clearfix"><i></i><span>{{type.name}}</span></p>&ndash;%&gt;--%>
                                <ul class="second_ul unfold_box">
                                    <li class="clearfix" ng-repeat="role in roles "><input class="checkbox" type="checkbox" ng-checked="{{role.checked}}" id={{role.name}} ng-model="role.checked" /><label for={{role.name}}>{{role.name}}</label></li>
                                </ul>
                          </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="center_btn_a clearfix"><!--screening_list clearfix center_btn big_btn_area-->
            <input style="margin-top:10px;" class="button has_m_btn input_btn" type="submit" ng-click="saveBtn()" value="保存"/>
            <a class="button grey_button" href="#" ng-click="closed()">取消</a>
        </div>
    </div>
</div>
