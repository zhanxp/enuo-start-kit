import Vue from 'vue'
import store from 'src/store'
import router from 'src/router'

import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap.min.js"
import { getTicket } from './http';

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.public)) {
        next()
    } else {
        var ticket = getTicket();
        console.log(ticket);
        if (!ticket) {
            next({
                path: '/login',
                query: {
                    redirect: to.fullPath
                }
            })
        } else {
            next()
        }
    }
})

new Vue({
    el: '#app',
    store,
    router,
    template: `
  <transition>
    <router-view></router-view>
  </transition>`
})