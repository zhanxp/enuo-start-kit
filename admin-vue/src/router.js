import Vue from 'vue'
import Router from 'vue-router'
import Layout from 'components/shared/layout.vue'
Vue.use(Router);

import account from './components/account/router'
import home from './components/home/router'
import article from './components/article/router'
import category from './components/category/router'
import error from './components/shared/router'

var opts = {
    routes: [
        ...account,
        {
            path: '/',
            component: Layout,
            children: [
                ...home,
                ...article,
                ...category
            ]
        },
        ...error
    ]
};
console.log(opts);
const router = new Router(opts);

// import Home from 'components/home/index.vue'
// const About = () =>
//     import ( /* webpackChunkName: 'about' */ 'components/home/about.vue')

// const Category = () =>
//     import ( /* webpackChunkName: 'category' */ 'components/category/list.vue')
//     const Article = () =>
//         import ( /* webpackChunkName: 'article' */ 'components/article/list.vue')
// const Login = () =>
//     import ( /* webpackChunkName: 'account' */ 'components/account/login.vue')



// const router = new Router({
//     routes: [{
//         path: '/login',
//         meta: {
//             public: true
//         },
//         component: Login
//     }, {
//         path: '/',
//         component: Layout,
//         children: [{
//                 path: '',
//                 component: Home
//             }, {
//                 path: 'about',
//                 component: About
//             }, {
//                 path: 'category',
//                 component: Category
//             },
//             // {
//             //     path: 'article',
//             //     component: Article
//             // },
//             {
//                 path: 'login',
//                 component: Login
//             }
//         ]
//     }],
//     linkActiveClass: 'active'
// })

export default router;