import {get, post } from '../../http';

export const login = function(username, password) {
    var data = {
        username: username,
        password: password
    };
    return post('/login', data);
}

export const logout = () => post('/logout');