var main = angular.module('app.main', [
    require('./home/module'),
    require('./article/module'),
    require('./category/module')
]);

require('./service');
require('./mainCtrl');


main.config(['$stateProvider', function($stateProvider) {
    $stateProvider
        .state('main', {
            abstract: true,
            template: require('./index.html'),
            controller: 'mainCtrl'
        });
}]);

module.exports = main.name;