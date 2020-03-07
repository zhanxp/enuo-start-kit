const router = require('koa-router')();
var adminService = require('../service/admin/adminServcie');

router.get('/login', async function (ctx, next) {
  await ctx.render('account/login', {
    title: 'Home'
  });
});

router.post('/login', async function (ctx, next) {
  var username = ctx.request.body.username;
  var password = ctx.request.body.password;
  var user = await adminService.login(username, password);
  if (user == null) {
    ctx.flash.error = '用户名密码错误，登录失败！';
    await ctx.redirect('login');
    return;
  }

  ctx.session.user = user;

  var redirectUrl = '/admin';
  if (ctx.session.originalUrl) {
    redirectUrl = ctx.session.originalUrl;
    ctx.session.originalUrl = null;
  }

  await ctx.redirect(redirectUrl);
});

router.get('/logout', async function (ctx, next) {
  ctx.session.user = null;
  await ctx.redirect('/');
});

router.prefix('/account');
module.exports = router;