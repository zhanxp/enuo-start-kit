/**
 * Created by Peter on 2016/12/19.
 */

(function() {
    'use strict';

    angular.module('app').factory('app.httpInterceptor',
        function(
            $q,
            $rootScope,
            $log,
            $appConfig) {

            return {
                request: function(config) {
                    return config;
                },
                response: function(response) {
                    if ($appConfig.debug === true) {
                        $log.info(response);
                    }

                    if (response.status == 200) {
                        if (typeof(response.data) == "object") {
                            var data = response.data;
                            console.log(data);
                            if (data.code === 401) {
                                data.success = false;
                                $rootScope.$emit("app.httpInterceptor", "notLogin", data);
                            } else if (data.code !== 200) {
                                data.success = false;
                                $rootScope.toast(data.msg, 'warning');
                            } else {
                                data.success = true;
                                $rootScope.updateCookies();
                            }

                            response = data;
                        }
                    } else {
                        $rootScope.$emit("app.httpInterceptor", "serverError", data);
                    }

                    return response;
                }
            };
        }
    );

})();