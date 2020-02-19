var article = angular.module('app.main.article', []);

require('./service');
require('./listCtrl');
require('./editCtrl');

article.config(function($stateProvider) {
    $stateProvider
        .state('main.article', {
            abstract: true,
            url: '/article',
            template: require('./index.html')
        })
        .state('main.article.list', {
            url: '/list',
            template: require('./list.html'),
            controller: 'articleListCtrl'
        });
});

module.exports = article.name;