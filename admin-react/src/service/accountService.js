import {getData,postData} from "./baseService"


export const loginAction = (username,password) => {
    var data = {
        username:username,
        password:password
    };
    var url = 'login';
    return postData(url,data);
}