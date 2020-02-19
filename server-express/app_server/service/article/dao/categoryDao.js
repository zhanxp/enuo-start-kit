var Dao = require("enuo-core").MysqlDao;
var db = require("enuo-core").mysql;

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
var categoryDao = new Dao(db, 'category');

module.exports = categoryDao;