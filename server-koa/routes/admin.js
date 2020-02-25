const router = require('koa-router')();
var adminService = require('../service/admin/adminServcie');
var articleServcie = require('../service/article/articleServcie');

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

router.get('/account/login', async function(ctx, next) {
    await ctx.render('admin/account/login', { title: 'Home' });
});

router.post('/account/login', async function (ctx, next) {
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

router.get('/account/logout', async function (ctx, next) {
    ctx.session.user = null;
    await ctx.redirect('/');
});

router.get('/category/', async function (ctx, next) {
  var list = await articleServcie.categoryList(null);
  await ctx.render('admin/category/index', {
    title: 'Category',
    list: list
  });
});


router.get('/article/', async function (ctx, next) {
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