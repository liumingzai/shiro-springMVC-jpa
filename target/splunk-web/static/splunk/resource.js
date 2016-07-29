/**
 * Created by liubing on 2015/12/21.
 */
var resourceMd = angular.module("myApp",['ngPagination','ngDialog']);

//图形展示
resourceMd.controller("resourceController",['$scope','ngDialog', 'userService','$http', function ($scope,ngDialog,userService,$http) {

    $scope.level= 0;

    //获取菜单级别
    $scope.getLevel = function(v){
        return  $scope.level == v;
    }

    //查询事件
    $scope.getSearchContent = function(){
        var cp = $scope.page.page-1;
        $scope.toPage(cp,50,{updatedAt:'desc'});
    }


    //编辑事件
    $scope.getEdit = function(id){
        $http({
            method: 'POST',
            url: F.url('/splunk/resource/edit.do'),
            data: $.param({id: id}),
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (data) {
            $scope.editResource(data.resource);
        }).error(function () {
            $scope.showArtDlg("编辑资源失败");
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
            '<p class="note_p"  style="font-size:12px;">是否确定要删除该资源！</p>' +
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
                    url: F.url('/splunk/resource/remove.do'),
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
    $scope.editResource = function(resource){
        if(resource != null) {
            $scope.rsname = resource.rsname;
            $scope.level = resource.level;
            $scope.parentid = resource.parentid;
            $scope.url = resource.url;
            if(resource.parentid!=null){
                $http.get(F.url('/splunk/resource/getParentName.do?parentid='+resource.parentid)).success(function(data){
                    $scope.parentName = data;
                });
            }else{
                $scope.parentName="";
            }

        }
        ngDialog.open({ template: 'editResource.do',//模式对话框内容为editResource.jsp
            className: 'ngdialog-theme-plain',
            scope:$scope
        })

        $http.get(F.url('/splunk/resource/getParentResources.do')).success(function(data){
            $scope.menus = data;
        });


        $scope.getMenuName = function (v) {
            $scope.parentName= v.name;
            $scope.parentid = v.id;
        }
        $scope.saveBtn = function () {
            $scope.resource = {
                id: resource.id,
                rsname: $('input[name=rsname]').val(),
                level: $('input[name=level]:checked').val(),
                parentid:  $scope.parentid,
                url: $('input[name=url]').val()
            }
            $http({
                method  : 'POST',
                url     : F.url('/splunk/resource/update.do'),
                data    : $.param( $scope.resource),
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
    $scope.createResource = function () {
        $scope.id = "";
        $scope.rsname = "";
        $scope.url =  "";
        $scope.level =  "";
        $scope.parentid = "";
        $scope.parentName = "";

        ngDialog.open({ template: 'editResource.do',//模式对话框内容为editResource.jsp
            className: 'ngdialog-theme-plain',
            scope:$scope
        })

        $http.get(F.url('/splunk/resource/getParentResources.do')).success(function(data){
            $scope.menus = data;
        });
        $scope.getMenuName = function (v) {
            $scope.parentName= v.name;
            $scope.parentid = v.id;
        }

        $scope.saveBtn = function () {
            $scope.resource = {
                rsname: $('input[name=rsname]').val(),
                level: $('input[name=level]:checked').val(),
                parentid: $scope.parentid,
                url: $('input[name=url]').val()
            };
            $http({
                method: 'POST',
                url: F.url('/splunk/resource/add.do'),
                data: $.param($scope.resource),
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


    //请求数据
    $scope.toPage=function(currPage,size,sort){
        var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
        var param={};
        userService.applydatas(currPage,size,sort,param).success(function(d){
            $scope.page= d.pageList.page;
            if(d.pageList.page.total&&d.pageList.page.total>0){
                $scope.resources = d.pageList.list;
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
resourceMd.service("userService",function($q,$http){
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
            return ajaxRequest(F.url("/splunk/resource/list.do"),params);
        }
    }
}).filter("levelfilter",function() {
    return function(v) {
        var levelname = "";
        switch(v){
            case 0 : levelname="顶级菜单";break;
            case 1 : levelname="子级菜单";break;
            case 2 : levelname="操作级菜单";break;
        }
        return levelname;
    };
})

