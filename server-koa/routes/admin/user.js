const router = require('koa-router')();

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */


router.get('/profile', async function (ctx, next) {
  var user = ctx.state.user;
  await ctx.render('admin/user/profile', {
    title: 'Profile',
    model: user
  });
});

router.get('/password', async function (ctx, next) {
  await ctx.render('admin/user/password', {
    title: 'Password'
  });
});

module.exports = router;