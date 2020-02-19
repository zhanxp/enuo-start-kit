 (function() {
     'use strict';
     angular.module('app.account')
         .service('accountSrv', function($rootScope, $baseSrv) {
             return {
                 login: function(username, password) {
                     var data = {
                         username: username,
                         password: password
                     };
                     return $baseSrv.post('/login', data);
                 }
             };
         });
 })();