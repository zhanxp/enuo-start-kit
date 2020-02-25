/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
module.exports = {
    debug: true,
    port: 8090,
    app_name: "enuocms",
    mysql: {
        connectionLimit: 10,
        host: '127.0.0.1',
        user: 'root',
        password: '123456',
        database: 'enuocms_test'
    },
    redis: {
      host: '127.0.0.1',
      port: '6379'
    },
    account: {
        expire_in: 7200,
    }
};