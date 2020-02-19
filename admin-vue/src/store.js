import Vue from "vue";
import Vuex from "vuex";

import Cookies from 'universal-cookie';
const cookie = new Cookies();

Vue.use(Vuex);

import {
    get,
    post
} from './http';

const store = new Vuex.Store({
    state: {
        appName: "EnuoCMS",
        userInfo: {
            id: '',
            name: ''
        },
        menus: [{
            path: "/",
            text: "主页"
        }, {
            path: "/category",
            text: "分类"
        }, {
            path: "/article",
            text: "文章"
        }, {
            path: "/about",
            text: "关于"
        }]
    },
    mutations: {
        saveUserInfo(state, userInfo) {
            state.userInfo = userInfo;
        }
    },
    actions: {
        async getUserProfile({ commit }) {
            try {
                const result = await get('/profile');
                if (result.success == 1) {
                    commit('saveUserInfo', result.data);
                } else {
                    commit('saveUserInfo', {});
                }
            } catch (err) {
                commit('saveUserInfo', {});
            }
        }
    }
})

export default store;