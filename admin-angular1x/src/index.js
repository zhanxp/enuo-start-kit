var angular = require('angular');

require('./vendor');

angular.module('app', [
    'ngSanitize',
    'ui.router',
    //'ngDialog',
    'ngCookies',
    require('./js/directives/pager'),
    require('./js/shared/module'),
    require('./js/account/module'),
    require('./js/main/module')
]);

require('./js/app.constants');
require('./js/app.config');
require('./js/app.http.interceptor');
require('./js/app.run');

angular.bootstrap(document, ['app']);