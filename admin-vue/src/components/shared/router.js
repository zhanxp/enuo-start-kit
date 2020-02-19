export default [{
        path: '/404',
        meta: {
            title: '页面不存在'
        },
        component: resolve => require(['./404'], resolve)
    },
    // {
    //     path: '/noAuth',
    //     meta: {
    //         title: '无权限',
    //         requiresAuth: false
    //     },
    //     component: resolve => require(['./noAuth'], resolve)
    // },
    {
        path: '*',
        redirect: '/404'
    }
]