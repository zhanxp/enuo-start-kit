const router = require('koa-router')();
var articleServcie = require('../service/article/articleServcie');

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
router.get('/', async function(ctx, next) {
    var pageIndex = ctx.query.pageIndex || 1;
    var pageSize = ctx.query.pageSize || 10;

    var pageList = await articleServcie.pageList(pageIndex, pageSize);

    var data = {
        title: 'Article',
        pageList: pageList,
        pageIndex: pageList.pageIndex,
        pageSize: pageList.pageSize,
        total: pageList.total,
        url: '?'
    };

    await ctx.render('article/index', data);
});

router.get('/edit/:id', async function(ctx, next) {
    var id = ctx.params.id;
    var model = await articleServcie.findById(id);
    await ctx.render('article/edit', { model: model });
});
router.prefix('/article');
module.exports = router;