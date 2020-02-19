var Dao = require("enuo-core").MysqlDao;
var db = require("enuo-core").mysql;

var adminDao = new Dao(db, 'admin');

adminDao.findByName = async function(name) {
    return await this.findByKV('name', name);
};

module.exports = adminDao;