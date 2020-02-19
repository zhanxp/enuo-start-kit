(function() {
    'use strict';
    angular.module('app.main.category').controller('categoryListCtrl',
        function($rootScope,
            $scope,
            $state,
            $appConfig,
            $cookies,
            categorySrv) {

            $scope.list = [];
            $scope.loadData = function() {
                categorySrv.list().then(
                    function(res) {
                        if (res.success) {
                            $scope.list = res.data;
                        }
                    }
                );
            };

            $scope.loadData();
        }
    );
})();