import {getData,postData} from "./baseService"

export const listAction = () => {
    var url = 'category/list';
    return getData(url);
}