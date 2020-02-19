 (function() {
     'use strict';
     angular.module('app.main')
         .service('mainSrv', function($rootScope, $baseSrv) {
             return {
                 loadUserInfo: function() {
                     return $baseSrv.get('/profile', {});
                 },
                 logout: function() {

                 }
             };
         });
 })();