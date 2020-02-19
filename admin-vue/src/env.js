let baseUrl = '';
let routerMode = 'hash';
let imgBaseUrl = '';

if (process.env.NODE_ENV == 'production') {
    baseUrl = '//www.xxxx.xxx/api';
    imgBaseUrl = '//www.xxxx.xxx/public';
} else {
    baseUrl = 'http://localhost:8090/api';
    imgBaseUrl = '/img';
}

export {
    baseUrl,
    routerMode,
    imgBaseUrl,
}