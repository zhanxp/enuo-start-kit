const router = require('koa-router')();
var articleServcie = require('../service/article/articleServcie');
var categoryService = require('../service/category/categoryService');

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */

router.get('/', async function (ctx, next) {
  await ctx.render('admin/index', {
    title: 'Admin'
  });
});

router.get('/profile', async function (ctx, next) {
  await ctx.render('admin/profile/index', {
    title: 'Profile'
  });
});

router.get('/category', async function (ctx, next) {
  var list = await categoryService.list();
  await ctx.render('admin/category/index', {
    title: 'Category',
    list: list
  });
});

router.get('/article', async function (ctx, next) {
  var pageIndex = ctx.query.pageIndex || 1;
  var pageSize = ctx.query.pageSize || 10;

  var pageList = await articleServcie.pageList(pageIndex, pageSize);
  var pageCount = parseInt((pageList.total + pageList.pageSize - 1) / pageList.pageSize);

  var data = {
    title: 'Article',
    pageList: pageList,
    pageIndex: pageList.pageIndex,
    pageSize: pageList.pageSize,
    total: pageList.total,
    pageCount: pageCount,
    url: '?'
  };

  await ctx.render('admin/article/index', data);
});

router.get('/article/edit/:id', async function (ctx, next) {
  var id = ctx.params.id;
  var model = await articleServcie.findById(id);
  await ctx.render('admin/article/edit', {
    model: model
  });
});

router.prefix('/admin');
module.exports = router;