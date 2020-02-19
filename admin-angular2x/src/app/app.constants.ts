export const constants = {
    debug: true,
    name: 'EnuoCMS',
    homeview: {
        name: "main.home",
        url: "/home"
    },
    loginview: {
        name: "login",
        url: "/login"
    },
    menus:[
        {
            path: "/home",
            text: "主页"
        }, {
            path: "/category/list",
            text: "分类"
        }, {
            path: "/article/list",
            text: "文章"
        }, {
            path: "/about",
            text: "关于"
        }
    ],
    baseUrl:'http://127.0.0.1:8090/api',
    openView: ["login", "register", "userAgreement", "forgetPassword", "loading"]
};
