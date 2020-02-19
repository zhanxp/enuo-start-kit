/**
 * Created by Peter on 2016/12/15.
 */
(function() {
    'use strict';

    angular.module('app').run(
        function($rootScope,
            $state,
            $appConfig,
            $cookies,
            $stateParams) {

            $rootScope.$state = $state;
            $rootScope.$stateParams = $stateParams;
            $rootScope.config = $appConfig;

            $rootScope.ticket = $cookies.get("ticket");
            $rootScope.nick = $cookies.get("nick");


            $rootScope.alert = function(msg) {
                alert(msg);
            };

            $rootScope.confirm = function(msg, fn1, fn2) {
                if (confirm(msg)) {
                    fn1();
                } else {
                    fn2();
                }
            };

            $rootScope.toast = function(msg, type) {
                console.log(msg);
            };

            $rootScope.setUserInfo = function(data) {
                if (!data) {
                    $cookies.remove("ticket");
                    $cookies.remove("nick");
                    $rootScope.ticket = null;
                } else {
                    $rootScope.name = data.name;
                    $rootScope.ticket = data.ticket;
                    $rootScope.expires_in = data.expires_in;
                    $rootScope.updateCookies(data.expires_in);
                }
                $rootScope.$broadcast("user_did_changed", {
                    data: data
                });
            };

            $rootScope.updateCookies = function(expires_in) {
                if (expires_in) {
                    $rootScope.expires_in = expires_in;
                }
                $rootScope.expires_in = $rootScope.expires_in || 60 * 60 * 1000 * 10000;
                var timestamp = Date.parse(new Date());
                timestamp += $rootScope.expires_in / 10000;
                var expireDate = new Date();
                expireDate.setTime(timestamp);
                $cookies.put("ticket", $rootScope.ticket, {
                    'expires': expireDate.toUTCString()
                });
                $cookies.put("nick", $rootScope.nick, {
                    'expires': expireDate.toUTCString()
                });
            };

            // $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams, options) {
            //     if ($appConfig.openView.indexOf(toState.name) >= 0) {

            //     } else if ($appConfig.openView.indexOf(toState.name) < 0 && (!$rootScope.ticket || $rootScope.ticket.length <= 0)) {
            //         $state.go("login");
            //         event.preventDefault();
            //     }
            // });

            $rootScope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams) {

            });

        }
    );

})();