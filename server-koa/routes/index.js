const router = require('koa-router')();
var articleServcie = require('../service/article/articleServcie');
var categoryService = require('../service/category/categoryService');

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
router.get('/', async function(ctx, next) {
  var pageIndex = ctx.query.pageIndex || 1;
  var pageSize = ctx.query.pageSize || 10;
  var pageList = await articleServcie.pageList(pageIndex, pageSize);
  var categorys = await categoryService.list();
  var pageCount = parseInt((pageList.total + pageList.pageSize - 1) / pageList.pageSize);
  var data = { 
    title: 'Home',
    categorys: categorys,
    pageList: pageList, 
    pageIndex: pageList.pageIndex,
    pageSize: pageList.pageSize,
    total: pageList.total,
    pageCount: pageCount,
    url: '?'
  }

  await ctx.render('home/index', data);
});

router.get('/about', async function(ctx, next) {
    var categorys = await categoryService.list();
    var data = {
      title: 'About',
      categorys: categorys
    };
    await ctx.render('home/about', data);
});

module.exports = router;