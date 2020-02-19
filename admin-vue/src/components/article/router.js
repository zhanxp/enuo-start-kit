export default [{
    path: '/article',
    component: resolve => require(['./index'], resolve),
    children: [{
            path: '/',
            redirect: 'list'
        },
        {
            path: 'list',
            name: 'article_list',
            meta: {},
            component: resolve => require(['./list'], resolve)
        },
        {
            path: 'edit/:id',
            name: 'article_edit',
            meta: {},
            component: resolve => require(['./edit'], resolve)
        },
        {
            path: 'edit/:id',
            name: 'article_detail',
            meta: {},
            component: resolve => require(['./detail'], resolve)
        }
    ]
}]