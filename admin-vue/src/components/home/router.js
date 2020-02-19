export default [{
    path: '/',
    component: resolve => require(['./index'], resolve)
}, {
    path: '/about',
    component: resolve => require(['./about'], resolve)
}]