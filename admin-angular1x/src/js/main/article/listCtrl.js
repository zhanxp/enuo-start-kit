(function() {
    'use strict';
    angular.module('app.main.article').controller('articleListCtrl',
        function($rootScope,
            $scope,
            $state,
            $appConfig,
            $cookies,
            articleSrv) {

            $scope.paegList = {
                pageIndex: 1,
                pageSize: 10,
                total: 10
            };

            $scope.loadData = function(pageIndex) {
                pageIndex = pageIndex || 1;
                articleSrv.pageList(pageIndex).then(
                    function(res) {
                        if (res.success) {
                            $scope.paegList = res.data;
                        }
                    }
                );
            };

            $scope.loadData();
        }
    );
})();