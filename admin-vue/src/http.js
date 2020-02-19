import { baseUrl } from './env'
import axios from 'axios'
import Cookies from 'universal-cookie';
const cookie = new Cookies();

export let instance = axios.create({
    baseURL: baseUrl, //设置默认api路径
    timeout: 10000, //设置超时时间
    headers: {
        'content-type': 'application/json'
    },
});



export const getTicket = () => {
    return cookie.get('ticket') || '';
}

export const setTicket = (ticket) => {
    return cookie.set('ticket', ticket);
}

export const get = (url, param = {}) => {
    var ticket = cookie.get('ticket') || '';
    param.ticket = ticket;
    return new Promise((resolve, reject) => {
        instance.get(`${url}`, {
            params: param
        }).then(function(response) {
            resolve(response.data);
        }).catch(function(error) {
            resolve({ success: false, code: 501, msg: '服务或网络异常！' });
            console.log(error);
        });
    });
}

export const post = (url, param = {}) => {
    var ticket = cookie.get('ticket') || '';
    param.ticket = ticket;
    return new Promise((resolve, reject) => {
        instance.post(`${url}`, param).then(function(response) {
            resolve(response.data);
        }).catch(function(error) {
            resolve({
                success: false,
                code: 501,
                msg: '服务或网络异常！'
            });
            console.log(error);
        });
    });
}