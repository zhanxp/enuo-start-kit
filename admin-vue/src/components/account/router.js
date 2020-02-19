export default [{
    path: '/login',
    component: resolve => require(['./login'], resolve),
    meta: {
        public: true
    },
}]