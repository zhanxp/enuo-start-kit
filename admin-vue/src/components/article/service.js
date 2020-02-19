import {get, post } from '../../http';

export const pageList = function(pageIndex, pageSize) {
    var data = {
        pageIndex: pageIndex,
        pageSize: pageSize
    };
    var url = 'article/list';
    return get(url, data);
}