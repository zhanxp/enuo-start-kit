var articleDao = require('./dao/articleDao');
var categoryDao = require('./dao/categoryDao');

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
var articleServcie = {
    pageList: async function(pageIndex, pageSize, where, params) {
        return await articleDao.pageList(pageIndex, pageSize, where, params);
    },
    findById: async function(id) {
        return await articleDao.findById(id);
    },
    categoryList: async function(query) {
        return await categoryDao.list(query);
    },
    categoryById: async function (id) {
      return await categoryDao.findById(id);
    }
};

module.exports = articleServcie;