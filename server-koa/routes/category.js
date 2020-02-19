const router = require('koa-router')();
var articleServcie = require('../service/article/articleServcie');

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
router.get('/', async function(ctx, next) {
    var list = await articleServcie.categoryList(null);
    await ctx.render('category/index', { title: 'Category', list: list });
});
router.prefix('/category');
module.exports = router;