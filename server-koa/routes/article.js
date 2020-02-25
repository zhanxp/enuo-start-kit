const router = require('koa-router')();
var articleServcie = require('../service/article/articleServcie');

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
router.get('/:category', async function (ctx, next) {
    var pageIndex = ctx.query.pageIndex || 1;
    var pageSize = ctx.query.pageSize || 10;
    var categoryId = ctx.params.category;

    var pageList = await articleServcie.pageList(pageIndex, pageSize, "category_id=?", categoryId);
    var category = await articleServcie.categoryById(categoryId);
    var categorys = await articleServcie.categoryList();

    var pageCount = parseInt((pageList.total + pageList.pageSize - 1) / pageList.pageSize);

    var data = {
        title: 'Article',
        categorys: categorys,
        category: category,
        pageList: pageList,
        pageIndex: pageList.pageIndex,
        pageSize: pageList.pageSize,
        total: pageList.total,
        pageCount: pageCount,
        url: '?'
    };

    await ctx.render('article/index', data);
});

router.get('/detail/:id', async function(ctx, next) {
    var id = ctx.params.id;
    var model = await articleServcie.findById(id);
    await ctx.render('article/detail', {
      model: model
    });
});
router.prefix('/article');
module.exports = router;