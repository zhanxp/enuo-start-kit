 (function() {
     'use strict';
     angular.module('app.main.article')
         .service('articleSrv', function($rootScope, $baseSrv) {
             return {
                 pageList: function(pageIndex, pageSize) {
                     var data = {
                         pageIndex: pageIndex || 1,
                         pageSize: pageSize || 10
                     };
                     return $baseSrv.get('/article/list', data);
                 }
             };
         });
 })();