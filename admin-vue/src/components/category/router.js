export default [{
    path: '/category',
    component: resolve => require(['./index'], resolve),
    children: [{
            path: '/',
            redirect: 'list'
        },
        {
            path: 'list',
            meta: {},
            component: resolve => require(['./list'], resolve)
        }
    ]
}]