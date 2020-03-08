const router = require('koa-router')();
var categoryService = require('../../service/category/categoryService');

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */

router.get('/', async function (ctx, next) {
  var list = await categoryService.list();
  await ctx.render('admin/category/index', {
    title: 'Category',
    list: list
  });
});

router.get('/add', async function (ctx, next) {
  await ctx.render('admin/category/add', {
    model: {}
  });
});

router.get('/edit/:id', async function (ctx, next) {
  var id = ctx.params.id;
  var model = await categoryService.findById(id);
  await ctx.render('admin/category/edit', {
    model: model
  });
});

router.post('/save', async function (ctx, next) {
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

router.post('/delete/:id', async function (ctx, next) {
  var id = ctx.params.id;
  var user = ctx.state.user;
  await categoryService.delete(id, user.id);
  ctx.json({
    code: 200,
    success: true,
    msg: 'OK'
  })
});


module.exports = router;