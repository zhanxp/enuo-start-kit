var Dao = require('enuo-core').MysqlDao;
var db = require('enuo-core').mysql;

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
var articleDao = new Dao(db, 'article');


module.exports = articleDao;