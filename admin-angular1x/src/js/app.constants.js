(function() {
    'use strict';

    var appconfig = {
        debug: false,
        name: 'EnuoCMS',
        menus: [{
            path: "/home",
            router: 'main.home',
            text: "主页"
        }, {
            path: "/category/list",
            router: 'main.category.list',
            text: "分类"
        }, {
            path: "/article/list",
            router: 'main.article.list',
            text: "文章"
        }, {
            path: "/about",
            router: 'main.about',
            text: "关于"
        }],
        homeview: {
            name: "main.home",
            url: "/home"
        },
        loginview: {
            name: "login",
            url: "/login"
        },
        baseUrl: "http://localhost:8090/api",
        openView: ["login", "register", "userAgreement", "forgetPassword", "loading"]
    }

    if (PROD) {
        appconfig.baseUrl = '/api';
    }

    angular.module('app')
        .constant("$appConfig", appconfig);

})();