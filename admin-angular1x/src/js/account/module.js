var account = angular.module('app.account', []);

require('./service');
require('./loginCtrl');

account.config(['$stateProvider', function($stateProvider) {
    $stateProvider
        .state('login', {
            url: "/login",
            template: require('./login.html'),
            controller: 'loginCtrl'
        });
}]);


module.exports = account.name;