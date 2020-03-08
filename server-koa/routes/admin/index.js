const router = require('koa-router')();
var articleServcie = require('../../service/article/articleServcie');
var categoryService = require('../../service/category/categoryService');

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */

router.get('/', async function (ctx, next) {
  await ctx.render('admin/index', {
    title: 'Admin'
  });
});

module.exports = router;