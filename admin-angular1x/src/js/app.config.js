/**
 * Created by Peter on 2016/12/15.
 */
(function() {
    'use strict';

    angular.module('app').config(
        function($httpProvider,
            $urlRouterProvider,
            $stateProvider,
            $appConfig,
            $locationProvider) {


            $httpProvider.interceptors.push('app.httpInterceptor');

            $urlRouterProvider.otherwise($appConfig.homeview.url);
        }
    );
})();