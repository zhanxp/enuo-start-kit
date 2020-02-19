(function() {
    'use strict';

    angular.module('app.shared')
        .factory('$baseSrv', function($http, $rootScope, $appConfig) {

            return {
                header: function() {
                    var ticket = $rootScope.ticket || "";
                    console.log(ticket);
                    var _headers = {
                        'ticket': ticket,
                        'Accept': 'application/json; charset=utf-8',
                        'Content-Type': 'application/json; charset=utf-8'
                    };
                    return _headers;
                },
                post: function(url, data) {
                    var header = this.header();
                    return $http({
                        method: 'POST',
                        url: $appConfig.baseUrl + url,
                        responseType: "json",
                        headers: header,
                        data: data,
                        timeout: 5000
                    });
                },
                get: function(url, data, _success, _error, _finally) {
                    var header = this.header();
                    return $http({
                        method: 'GET',
                        url: $appConfig.baseUrl + url,
                        responseType: "json",
                        headers: header,
                        data: data,
                        params: data,
                        timeout: 5000
                    });
                },
            };
        });
})();