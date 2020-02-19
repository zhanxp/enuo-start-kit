(function() {
    'use strict';

    angular.module('app.shared').factory('$appUtil', function() {
        return {
            uuid: function() {
                var str = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
                    var r = Math.random() * 16 | 0,
                        v = c == 'x' ? r : (r & 0x3 | 0x8);
                    return v.toString(16);
                });
                return str.replace("-", "");
            }
        };
    });

})();