var category = angular.module('app.main.category', []);

require('./service');
require('./listCtrl');

category.config(function($stateProvider) {
    $stateProvider
        .state('main.category', {
            abstract: true,
            url: '/category',
            template: require('./index.html')
        })
        .state('main.category.list', {
            url: '/list',
            template: require('./list.html'),
            controller: 'categoryListCtrl'
        });
});

module.exports = category.name;