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

router.get('/category', async function (ctx, next) {
  var list = await categoryService.list();
  await ctx.render('admin/category/index', {
    title: 'Category',
    list: list
  });
});

router.get('/category/add', async function (ctx, next) {
  await ctx.render('admin/category/add', {
    model: {}
  });
});

router.get('/category/edit/:id', async function (ctx, next) {
  var id = ctx.params.id;
  var model = await categoryService.findById(id);
  await ctx.render('admin/category/edit', {
    model: model
  });
});

router.post('/category/save', async function (ctx, next) {
  var body = ctx.request.body;
  var user = ctx.state.user;
  var ent = {};
  if (body.id) {
    ent = await categoryService.findById(body.id);
  }

  ent.title = body.title;
  ent.update_date = new Date();
  ent.updater = user.id;

  if (body.id) {
    await categoryService.update(ent);
  } else {
    ent.create_date = new Date();
    ent.creater = user.id;
    await categoryService.insert(ent);
  }
  ctx.body = "<script> window.parent.fnOnSaved();  </script>";
});

router.post('/category/delete/:id', async function (ctx, next) {
  var id = ctx.params.id;
  var user = ctx.state.user;
  await categoryService.delete(id, user.id);
  ctx.json({
    code: 200,
    success: true,
    msg: 'OK'
  })
});

router.get('/article', async function (ctx, next) {
  var pageIndex = ctx.query.pageIndex || 1;
  var pageSize = ctx.query.pageSize || 10;

  var pageList = await articleServcie.pageList(pageIndex, pageSize);
  var pageCount = parseInt((pageList.total + pageList.pageSize - 1) / pageList.pageSize);
  var categorys = await categoryService.list();

  var data = {
    title: 'Article',
    categorys: categorys,
    pageList: pageList,
    pageIndex: pageList.pageIndex,
    pageSize: pageList.pageSize,
    total: pageList.total,
    pageCount: pageCount,
    url: '?'
  };

  await ctx.render('admin/article/index', data);
});

router.get('/article/add', async function (ctx, next) {
  await ctx.render('admin/article/add', {
    model: {}
  });
});

router.get('/article/edit/:id', async function (ctx, next) {
  var id = ctx.params.id;
  var model = await articleServcie.findById(id);
  await ctx.render('admin/article/edit', {
    model: model
  });
});

router.post('/article/save', async function (ctx, next) {
  var body = ctx.request.body;
  var user = ctx.state.user;
  var ent = {};
  if(body.id){
    ent = await articleServcie.findById(body.id);
  }

  ent.title = body.title;
  ent.intro = body.intro;
  ent.content = body.content;
  ent.category_id = parseInt(body.category_id);
  ent.update_date = new Date();
  ent.updater = user.id;

  if (body.id) {
    await articleServcie.update(ent);
  }else{
    ent.create_date = new Date();
    ent.creater = user.id;
    await articleServcie.insert(ent);
  }
  ctx.body = "<script> window.parent.fnOnSaved();  </script>";
});

router.post('/article/delete/:id', async function (ctx, next) {
  var id = ctx.params.id;
  var user = ctx.state.user;
  await articleServcie.delete(id,user.id);
  ctx.json({
    code:200,
    success:true,
    msg:'OK'
  })
});

router.prefix('/admin');
module.exports = router;