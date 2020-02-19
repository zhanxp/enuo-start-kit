import {getData,postData} from "./baseService"

export const pageListAction = (pageIndex,pageSize) => {
    var data = {
        pageIndex:pageIndex,
        pageSize:pageSize
    };
    var url = 'article/list';
    return getData(url,data);
}