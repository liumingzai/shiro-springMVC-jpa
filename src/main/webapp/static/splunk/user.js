/**
 * Created by liubing on 2015/12/21.
 */
var userMd = angular.module("myApp",['ngPagination','ngDialog']);

//图形展示
userMd.controller("userController",['$scope','ngDialog', 'userService','$http','$filter',  function ($scope,ngDialog,userService,$http,$filter) {

    var uploader = WebUploader.create({
        auto:true,
        // swf文件路径
        swf: BASE_URL + '/static/webuploader/Uploader.swf',
        // 文件接收服务端。
        server: F.url('/splunk/user/importDatas.do'),
        pick: '#picker',
        resize: false
    });

    uploader.on("beforeFileQueued", function(file,response){
        uploader.reset();
    });
    uploader.on( 'uploadSuccess', function( file,response ) {
        if(response.msg) {
            $scope.showArtDlg(response.msg);
        }
    });
    uploader.on( 'uploadError', function( file,response ) {
        $scope.showArtDlg(response.msg);
    });

    uploader.on( 'uploadComplete', function( file) {
        $scope.getSearchContent();
    });


    $scope.flag = false;

    //查询事件
    $scope.getSearchContent = function(){
        var cp = $scope.page.page-1;
        $scope.toPage(cp,50,{updatedAt:'desc'});
    }

    //编辑事件
    $scope.getEdit = function(id){
        $http({
            method: 'POST',
            url: F.url('/splunk/user/edit.do'),
            data: $.param({id: id}),
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (data) {
            $scope.editUser(data.user);
            $scope.getSearchContent();
        }).error(function () {
            $scope.showArtDlg("编辑用户失败");
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
            '<p class="note_p"  style="font-size:12px;">是否确定要删除该用户！</p>' +
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
                    url: F.url('/splunk/user/remove.do'),
                    data: $.param({id: id}),
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                }).success(function () {
                    ArtDlg.showArtDlg("删除成功");
                    $scope.getSearchContent();
                }).error(function(e) {
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
    $scope.editUser = function(user){
        if(user != null) {
            $scope.uno = user.uno;
            $scope.username = user.username;
            $scope.nickname = user.nickname;
            $scope.center = user.center;
            $scope.department = user.department;
            $scope.phone_mac = user.phone_mac;
        }
        ngDialog.open({ template: 'editUser.do' ,//模式对话框内容为editUser.jsp
            className: 'ngdialog-theme-plain',
            scope: $scope
        })
        $scope.saveBtn = function () {
            $scope.user = {
                id: user.id,
                uno: $('input[name=uno]').val(),
                username: $('input[name=username]').val(),
                nickname: $('input[name=nickname]').val(),
                center: $('input[name=center]').val(),
                department: $('input[name=department]').val(),
                phone_mac: $('input[name=phone_mac]').val()
            };
            $http({
                method  : 'POST',
                url     : F.url('/splunk/user/update.do'),
                data    : $.param( $scope.user),
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
    $scope.createAccount = function () {
        $scope.uno = "";
        $scope.username = "";
        $scope.nickname =  "";
        $scope.center =  "";
        $scope.department =  "";
        $scope.phone_mac =  "";

        ngDialog.open({ template: 'editUser.do',//模式对话框内容为editUser.jsp
            className: 'ngdialog-theme-plain',
            scope:$scope
        })

        $scope.saveBtn = function () {
            $scope.user = {
                uno: $('input[name=uno]').val(),
                username: $('input[name=username]').val(),
                nickname: $('input[name=nickname]').val(),
                center: $('input[name=center]').val(),
                department: $('input[name=department]').val(),
                phone_mac : $('input[name=phone_mac]').val()
            };
            $http({
                method: 'POST',
                url: F.url('/splunk/user/add.do'),
                data: $.param($scope.user),
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
    $scope.bindUserRole = function (id) {
        ngDialog.open({
            template: 'bindUserRole.do',//模式对话框内容为bindUserRole.jsp
            className: 'ngdialog-theme-plain',
            scope: $scope
        })

        //定义角色类型
        $scope.types =  [{ id: 'MANAGE', name: '高管' },{ id: 'SALE', name: '销售' },{ id: 'OWNER', name: '自营' },{ id: 'COMB', name: '联营' },{ id: 'FINANCE', name: '金融' },{ id: 'OVERSEA', name: '海外购' },{ id: 'VIRTUAL', name: '虚拟' }];
        //获取角色信息
        $http.get(F.url('/splunk/role/getRoles.do?uid='+id)).success(function(data){
            $scope.roles = data;
        });

        //监听角色选中状态
        $scope.$watch('roles', function(newValue, oldValue) {
            if(newValue !== oldValue){
                $scope.flag = true;
            }
            $scope.selectRoles=[];
            angular.forEach($filter('filter')(newValue, {checked: true}), function(v) {
                $scope.selectRoles.push(v.id);
            });
        }, true);

        $scope.saveBtn = function () {
            var params = {
                roleIds: $scope.selectRoles,
                uId: id
            };
            $http.post(F.url("/splunk/user/userBindRole.do"), params, {
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                transformRequest: function (data) {
                    return $.param(data);
                }
            }).success(function(data){
                if(data.success){
                    ArtDlg.showArtDlg("分配角色成功");
                    //$scope.getSearchContent();
                }else{
                    ArtDlg.showArtDlg("分配角色失败");
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
                $scope.users = d.pageList.list;
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
userMd.service("userService",function($q,$http){
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
            return ajaxRequest(F.url("/splunk/user/list.do"),params);
        }
    }
})