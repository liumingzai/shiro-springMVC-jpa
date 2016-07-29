/**
 * name: tm.pagination
 * Version: 0.0.2
 */
angular.module('tm.pagination', []).directive('tmPagination',[function(){
    return {
        restrict: 'EA',
        template: '<div class="page-list">' +
        '<ul class="pagination" ng-show="cof.totalItems > 0">' +
        '<li ng-class="{disabled: cof.currentPage == 1}" ng-click="prevPage()"><span>&laquo;</span></li>' +
        '<li ng-repeat="item in pageList track by $index" ng-class="{active: item == cof.currentPage, separate: item == \'...\'}" ' +
        'ng-click="changeCurrentPage(item)">' +
        '<span>{{ item }}</span>' +
        '</li>' +
        '<li ng-class="{disabled: cof.currentPage == cof.numberOfPages}" ng-click="nextPage()"><span>&raquo;</span></li>' +
        '</ul>' +
        '<div class="no-items" ng-show="cof.totalItems <= 0">暂无数据</div>' +
        '</div>',
        replace: true,
        scope: {
            cof: '='
        },
        link: function(scope){


            console.log(scope);


            // 变更当前页
            scope.changeCurrentPage = function(item){
                if(item == '...'){
                    return;
                }else{
                    scope.cof.currentPage = item;
                }
            };

            // 定义分页的长度必须为奇数 (default:9)
            scope.cof.pagesLength = parseInt(scope.cof.pagesLength) ? parseInt(scope.cof.pagesLength) : 9 ;
            if(scope.cof.pagesLength % 2 === 0){
                // 如果不是奇数的时候处理一下
                scope.cof.pagesLength = scope.cof.pagesLength -1;
            }

            // cof.erPageOptions
            if(!scope.cof.perPageOptions){
                scope.cof.perPageOptions = [10, 15, 20, 30, 50];
            }

            // pageList数组
            function getPagination(){
                // cof.currentPage
                scope.cof.currentPage = parseInt(scope.cof.currentPage) ? parseInt(scope.cof.currentPage) : 1;
                // cof.totalItems
                scope.cof.totalItems = parseInt(scope.cof.totalItems);

                // cof.itemsPerPage (default:15)
                // 先判断一下本地存储中有没有这个值
                if(scope.cof.rememberPerPage){
                    if(!parseInt(localStorage[scope.cof.rememberPerPage])){
                        localStorage[scope.cof.rememberPerPage] = parseInt(scope.cof.itemsPerPage) ? parseInt(scope.cof.itemsPerPage) : 15;
                    }

                    scope.cof.itemsPerPage = parseInt(localStorage[scope.cof.rememberPerPage]);


                }else{
                    scope.cof.itemsPerPage = parseInt(scope.cof.itemsPerPage) ? parseInt(scope.cof.itemsPerPage) : 15;
                }

                // numberOfPages
                scope.cof.numberOfPages = Math.ceil(scope.cof.totalItems/scope.cof.itemsPerPage);

                // judge currentPage > scope.numberOfPages
                if(scope.cof.currentPage < 1){
                    scope.cof.currentPage = 1;
                }

                if(scope.cof.currentPage > scope.cof.numberOfPages){
                    scope.cof.currentPage = scope.cof.numberOfPages;
                }

                // jumpPageNum
                scope.jumpPageNum = scope.cof.currentPage;

                // 如果itemsPerPage在不在perPageOptions数组中，就把itemsPerPage加入这个数组中
                var perPageOptionsLength = scope.cof.perPageOptions.length;
                // 定义状态
                var perPageOptionsStatus;
                for(var i = 0; i < perPageOptionsLength; i++){
                    if(scope.cof.perPageOptions[i] == scope.cof.itemsPerPage){
                        perPageOptionsStatus = true;
                    }
                }
                // 如果itemsPerPage在不在perPageOptions数组中，就把itemsPerPage加入这个数组中
                if(!perPageOptionsStatus){
                    scope.cof.perPageOptions.push(scope.cof.itemsPerPage);
                }

                // 对选项进行sort
                scope.cof.perPageOptions.sort(function(a, b){return a-b});

                scope.pageList = [];
                if(scope.cof.numberOfPages <= scope.cof.pagesLength){
                    // 判断总页数如果小于等于分页的长度，若小于则直接显示
                    for(i =1; i <= scope.cof.numberOfPages; i++){
                        scope.pageList.push(i);
                    }
                }else{
                    // 总页数大于分页长度（此时分为三种情况：1.左边没有...2.右边没有...3.左右都有...）
                    // 计算中心偏移量
                    var offset = (scope.cof.pagesLength - 1)/2;
                    if(scope.cof.currentPage <= offset){
                        // 左边没有...
                        for(i =1; i <= offset +1; i++){
                            scope.pageList.push(i);
                        }
                        scope.pageList.push('...');
                        scope.pageList.push(scope.cof.numberOfPages);
                    }else if(scope.cof.currentPage > scope.cof.numberOfPages - offset){
                        scope.pageList.push(1);
                        scope.pageList.push('...');
                        for(i = offset + 1; i >= 1; i--){
                            scope.pageList.push(scope.cof.numberOfPages - i);
                        }
                        scope.pageList.push(scope.cof.numberOfPages);
                    }else{
                        // 最后一种情况，两边都有...
                        scope.pageList.push(1);
                        scope.pageList.push('...');

                        for(i = Math.ceil(offset/2) ; i >= 1; i--){
                            scope.pageList.push(scope.cof.currentPage - i);
                        }
                        scope.pageList.push(scope.cof.currentPage);
                        for(i = 1; i <= offset/2; i++){
                            scope.pageList.push(scope.cof.currentPage + i);
                        }

                        scope.pageList.push('...');
                        scope.pageList.push(scope.cof.numberOfPages);
                    }
                }

                if(scope.cof.onChange){
                    scope.cof.onChange();
                }
                scope.$parent.cof = scope.cof;
            }

            // prevPage
            scope.prevPage = function(){
                if(scope.cof.currentPage > 1){
                    scope.cof.currentPage -= 1;
                }
            };
            // nextPage
            scope.nextPage = function(){
                if(scope.cof.currentPage < scope.cof.numberOfPages){
                    scope.cof.currentPage += 1;
                }
            };

            // 跳转页
            scope.jumpToPage = function(){
                scope.jumpPageNum = scope.jumpPageNum.replace(/[^0-9]/g,'');
                if(scope.jumpPageNum !== ''){
                    scope.cof.currentPage = scope.jumpPageNum;
                }
            };

            // 修改每页显示的条数
            scope.changeItemsPerPage = function(){
                // 清除本地存储的值方便重新设置
                if(scope.cof.rememberPerPage){
                    localStorage.removeItem(scope.cof.rememberPerPage);
                }
            };

            scope.$watch(function(){
                var newValue = scope.cof.currentPage + ' ' + scope.cof.totalItems + ' ';
                // 如果直接watch perPage变化的时候，因为记住功能的原因，所以一开始可能调用两次。
                //所以用了如下方式处理
                if(scope.cof.rememberPerPage){
                    // 由于记住的时候需要特别处理一下，不然可能造成反复请求
                    // 之所以不监控localStorage[scope.cof.rememberPerPage]是因为在删除的时候会undefind
                    // 然后又一次请求
                    if(localStorage[scope.cof.rememberPerPage]){
                        newValue += localStorage[scope.cof.rememberPerPage];
                    }else{
                        newValue += scope.cof.itemsPerPage;
                    }
                }else{
                    newValue += scope.cof.itemsPerPage;
                }
                return newValue;

            }, getPagination);

        }
    };
}]);
