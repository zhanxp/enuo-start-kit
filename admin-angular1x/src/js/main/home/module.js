var home = angular.module('app.main.home', []);

require('./homeCtrl');

home.config(['$stateProvider', function($stateProvider) {
    $stateProvider
        .state('main.home', {
            url: "/home",
            template: require('./home.html'),
            controller: 'homeCtrl'
        })
        .state('main.about', {
            url: "/about",
            template: require('./about.html')
        });
}]);

module.exports = home.name;