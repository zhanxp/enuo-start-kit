import {
    get,
    post
} from '../../http';

export const list = function() {
    var data = {};
    var url = 'category/list';
    return get(url, data);
}