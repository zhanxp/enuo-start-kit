var dao = require('./categoryDao');

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
var servcie = {
  sortOptions: {
    0: 'id desc',
    1: 'id asc',
    2: 'create_date asc',
    3: 'create_date desc',
  },
  buildQuery: function (query, sort) {
    query = query || {};
    var where = "1=1";
    var params = [];
    if (query.is_delete != undefined) {
      where += ' and is_delete = ?';
      params.push(query.is_delete);
    }else{
      where += ' and is_delete = 0';
    }
    if (query.title != undefined) {
      where += ' and title like ?';
      params.push('%' + query.title + '%');
    }
    var orderBy = "id desc";
    if (sort && sortOptions[sort]) {
      orderBy = sortOptions[sort];
    }
    return {
      where: where,
      params: params,
      orderBy: orderBy
    }
  },
  pageList: async function (pageIndex, pageSize, query, sort) {
    var q = this.buildQuery(query, sort);
    return await dao.pageList(pageIndex, pageSize, q.where, q.params, q.orderBy);
  },
  list: async function (query, count,sort) {
    var q = this.buildQuery(query, sort);
    var opts = {
      orderBy: q.orderBy
    };
    if(count){
      opts.limt = count;
    }
    return await dao.list(q.where, q.params, opts);
  },
  findById: async function (id) {
    return await dao.findByKV("id", id);
  },
  update: async function (data) {
    return await dao.update(data);
  },
  insert: async function (data) {
    return await dao.insert(data);
  },
  delete: async function (id, user_id) {
    return await dao.delete({
      is_delete: 1,
      update_date: new Date(),
      updater: user_id
    }, {
      id: id
    });
  }
};

module.exports = servcie;