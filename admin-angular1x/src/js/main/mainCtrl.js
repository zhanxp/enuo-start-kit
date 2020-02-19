(function() {
    'use strict';
    angular.module('app.main').controller('mainCtrl',
        function($rootScope,
            $scope,
            $state,
            $appConfig,
            $cookies,
            mainSrv) {

            $scope.appName = $appConfig.name;
            $scope.menus = $appConfig.menus;
            $scope.userInfo = {
                nick: $rootScope.nick,
                ticket: $rootScope.ticket
            };

            $scope.loadUserInfo = function() {
                mainSrv.loadUserInfo().then(
                    function(res) {
                        if (res.success) {
                            $scope.userInfo = res.data;
                        }
                    }
                );
            };

            // 处理消息通知
            $rootScope.$on('app.httpInterceptor', function(event, mass, data) {
                switch (mass) {
                    case 'notLogin': // 用户未登录
                        $rootScope.toast('登录信息已过期，请退出后重新登录！', 'error');
                        $state.go("login");
                        break;
                    case 'serverError': //
                        $rootScope.toast(data.msg || '服务器返回异常，请检测您的网络设置！', 'error');
                        break;
                    default:
                        $rootScope.toast('请求错误!');
                }
            });


            $scope.logout = function() {
                $rootScope.confirm("确定要退出吗?", function() {
                    mainSrv.logout();
                    $rootScope.setUserInfo(null);
                    $state.go($rootScope.config.loginview.name);
                }, function() {});
            };

            $rootScope.$on("user_did_changed", function(evnet, args) {
                if (args.data) {
                    $scope.appInfo = $appConfig.appInfo;
                    $scope.userinfo = {
                        nick: $rootScope.nick
                    };
                    $scope.loadUserInfo();
                }
            });

            $scope.loadUserInfo();
        }
    );
})();