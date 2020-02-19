(function() {
    'use strict';
    angular.module('app.account')
        .controller('loginCtrl',
            function($rootScope,
                $scope,
                $state,
                $appConfig,
                $cookies,
                accountSrv) {

                $scope.loginForm = {};
                $scope.onLogin = function() {
                    console.log($scope.loginForm);
                    accountSrv.login($scope.loginForm.username, $scope.loginForm.password).then(function(res) {
                        if (res.success) {
                            $rootScope.setUserInfo(res.data);
                            $state.go($appConfig.homeview.name);
                        } else {
                            $rootScope.alert(res.msg || '登录失败！');
                        }
                    });
                    return false;
                }
            }
        );
})();