/**
 * Created by liubing on 2015/12/21.
 */
var roleMd = angular.module("myApp",['ngPagination','ngDialog']);

//图形展示
roleMd.controller("roleController",['$scope','ngDialog', 'userService','$http','$filter', function ($scope,ngDialog,userService,$http,$filter) {

    $scope.resources = "";
    $scope.type_name = "";
    $scope.chirlds = "";
    $scope.types =  [{ id: 'MANAGER', name: '高管' },{ id: 'OWNER', name: '自营' },{ id: 'COMB', name: '联营' },{ id: 'FINANCE', name: '金融' },{ id: 'OVERSEA', name: '海外购' },{ id: 'VIRTUAL', name: '虚拟' }];


    //获取平台列表信息
    $scope.getTypeName = function(v){
        $scope.type_name = v.name;
    }

    //查询事件
    $scope.getSearchContent = function(){
        $scope.toPage(0,50,{updatedAt:'desc'});
    }

    //编辑事件
    $scope.getEdit = function(id){
        $http({
            method: 'POST',
            url: F.url('/splunk/role/edit.do'),
            data: $.param({id: id}),
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (data) {
            $scope.editRole(data.role);
        }).error(function () {
            $scope.showArtDlg("编辑角色失败");
        });
    }
    //删除事件
    $scope.getRemove = function(id){
        dialog({
            title: '提示',
            width: 400,
            content: '<div class="position_close">' +
            '<div class="popup_text">' +
            '<table width="100%" border="0">' +
            '<tbody>' +
            '<tr>' +
            '<td>' +
            '<p class="note_p"  style="font-size:12px;">是否确定要删除该角色！</p>' +
            '</td>' +
            '</tr>' +
            '</tbody>' +
            '</table>' +
            '</div>' +
            '</div>',
            cancel:true,
            style:"overflower:auto",
            ok:function(){
                $http({
                    method: 'POST',
                    url: F.url('/splunk/role/remove.do'),
                    data: $.param({id: id}),
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                }).success(function () {
                    ArtDlg.showArtDlg("删除成功");
                    $scope.getSearchContent();
                }).error(function() {
                    ArtDlg.showArtDlg("删除失败");
                })
            },
            cancelValue: '取消',
            okValue:"确定"
        }).showModal();
    }

    $scope.showArtDlg = function(msg){
        ArtDlg.showArtDlg(msg);
    }

    //点击编辑按钮
    $scope.editRole = function(role){
        if(role != null) {
            $scope.name = role.name;
            $scope.type_name = role.type_name;
        }
        ngDialog.open({ template: 'editRole.do',//模式对话框内容为editRole.jsp
            className: 'ngdialog-theme-plain',
            scope:$scope
        })

        $scope.saveBtn = function () {
            $scope.role = {
                id: role.id,
                name: $('input[name=name]').val(),
                type_name: $('input[name=type_name]').val()
            }
            $http({
                method  : 'POST',
                url     : F.url('/splunk/role/update.do'),
                data    : $.param( $scope.role),
                headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
            }).success(function(data) {
                if(data.success){
                    ArtDlg.showArtDlg("编辑成功");
                    $scope.getSearchContent();
                }else{
                    ArtDlg.showArtDlg("编辑失败");
                }

            });
            ngDialog.close();
        }
        $scope.closed = function(){
            ngDialog.close();
        }
    }

    //点击添加按钮时，跳出模式对话框
    $scope.createRole = function () {
        $scope.id = "";
        $scope.name = "";
        $scope.type_name =  "";
        ngDialog.open({ template: 'editRole.do',//模式对话框内容为editRole.jsp
            className: 'ngdialog-theme-plain',
            scope:$scope
        })

        $scope.saveBtn = function () {
            $scope.role = {
                name: $('input[name=name]').val(),
                type_name: $('input[name=type_name]').val()
            };
            $http({
                method: 'POST',
                url: F.url('/splunk/role/add.do'),
                data: $.param($scope.role),
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            }).success(function (data) {
                if(data.success){
                    ArtDlg.showArtDlg("添加成功");
                    $scope.getSearchContent();
                }else{
                    ArtDlg.showArtDlg("添加失败");
                }
            });
            ngDialog.close();
        }
        $scope.closed = function(){
            ngDialog.close();
        }
    }


    //分配角色
    $scope.bindRoleResource = function (id) {
        ngDialog.open({
            template: 'bindRoleResource.do',//模式对话框内容为bindUserRole.jsp
            className: 'ngdialog-theme-plain',
            scope: $scope
        })

        //定义角色类型
        $scope.levels =  [{ id: '0', name: '顶级菜单' },{ id: '1', name: '子级菜单' },{ id: '2', name: '操作级菜单' }];
        //获取角色信息
        $http.get(F.url('/splunk/resource/getParentResources.do?rid='+id)).success(function(data){
            $scope.resources = data;
        });
        $scope.selectIds=[];
        //获取角色信息
        $http.get(F.url('/splunk/resource/getChirlds.do?rid='+id)).success(function(data){
            $scope.chirlds = data;
        });
        //监听角色选中状态
        $scope.$watch('resources', function(newValue, oldValue) {
            if(newValue !== oldValue){
                $scope.flag = true;
            }
            $scope.selectRes=[];
            angular.forEach($filter('filter')(newValue, {checked: true}), function(v) {
                $scope.selectRes.push(v.id);
            });
        }, true);

        //监听角色选中状态
        $scope.$watch('chirlds', function(newValue, oldValue) {
            if(newValue !== oldValue){
                $scope.flag = true;
            }
            $scope.selectChIds=[];
            angular.forEach($filter('filter')(newValue, {checked: true}), function(v) {
                $scope.selectChIds.push(v.id);
            });
        }, true);

        $scope.saveBtn = function () {
            $scope.selectIds = $scope.selectIds.concat($scope.selectRes,$scope.selectChIds);
            var params = {
                resIds: $scope.selectIds,
                rId: id
            };
            $http.post(F.url("/splunk/role/roleBindResource.do"), params, {
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                transformRequest: function (data) {
                    return $.param(data);
                }
            }).success(function(data){
                if(data.success){
                    ArtDlg.showArtDlg("分配资源成功");
                    //$scope.getSearchContent();
                }else{
                    ArtDlg.showArtDlg("分配资源失败");
                }
            })
            ngDialog.close();
        }
        $scope.closed = function(){
            ngDialog.close();
        }
    }


    //请求数据
    $scope.toPage=function(currPage,size,sort){
        var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
        var param={};
        userService.applydatas(currPage,size,sort,param).success(function(d){
            $scope.page= d.pageList.page;
            if(d.pageList.page.total&&d.pageList.page.total>0){
                $scope.roles = d.pageList.list;
            }
            //关闭加载层
            layer.close(index);
            if(d.pageList.sort){
                var sort ={};
                sort[d.pageList.sort[0].property]= d.pageList.sort[0].direction;
                $scope.page.sort=sort;
            }
        });
    }

    $scope.toPage(0,50,{updatedAt:'desc'});

}])

//service服务userService
roleMd.service("userService",function($q,$http){
    function ajaxRequest(url,params) {
        return $http.post(url, params, {
            headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
            transformRequest: function (data) {
                return $.param(data);
            }
        })
    }
    return {
        applydatas:function(page,size,sort,param){
            var start = 0;
            if(page>0){
                start = (page)*size;
            }else{
                start = 0;
            }
            var params={page:page+1,limit:size,start:start};
            params= angular.extend(params,param);
            if(sort){
                for(var k in sort){
                    params['sort']=k+','+sort[k];
                }
            }
            return ajaxRequest(F.url("/splunk/role/list.do"),params);
        }
    }
})

$(function(){
    $(document).on("click",'.first_ul .checkbox_title',function(){
        $(this).find('i').toggleClass("shut_tb");
        $(this).parent().find('.unfold_box').slideToggle(300);
    })
});