import axios from 'axios'
import Cookies from 'universal-cookie';
const cookie = new Cookies();

export let instance = axios.create({
    baseURL: 'http://localhost:8090/api/', //设置默认api路径
    timeout: 10000, //设置超时时间
    headers: { 'content-type': 'application/json' },
});


export const getData = (url, param = {}) => {
    var ticket = cookie.get('ticket') || '';
    param.ticket = ticket;
    return (
        instance.get(`${url}`, {
            params: param
        })
    )
}

export const postData = (url, param = {}) => {
    var ticket = cookie.get('ticket') || '';
    param.ticket = ticket;
    return (
        instance.post(`${url}`, param)
    )
}