 (function() {
     'use strict';
     angular.module('app.main.category')
         .service('categorySrv', function($rootScope, $baseSrv) {
             return {
                 list: function() {
                     return $baseSrv.get('/category/list', {});
                 }
             };
         });
 })();