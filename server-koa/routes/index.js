const router = require('koa-router')();

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
router.get('/', async function(ctx, next) {
    await ctx.render('home/index', { title: 'Home' });
});

router.get('/about', async function(ctx, next) {
    await ctx.render('home/about', { title: 'About' });
});

module.exports = router;