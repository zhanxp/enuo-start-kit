const router = require('koa-router')();
var articleServcie = require('../../service/article/articleServcie');
var categoryService = require('../../service/category/categoryService');

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */

router.get('/', async function (ctx, next) {
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

router.get('/add', async function (ctx, next) {
  await ctx.render('admin/article/add', {
    model: {}
  });
});

router.get('/edit/:id', async function (ctx, next) {
  var id = ctx.params.id;
  var model = await articleServcie.findById(id);
  await ctx.render('admin/article/edit', {
    model: model
  });
});

router.post('/save', async function (ctx, next) {
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

router.post('/delete/:id', async function (ctx, next) {
  var id = ctx.params.id;
  var user = ctx.state.user;
  await articleServcie.delete(id,user.id);
  ctx.json({
    code:200,
    success:true,
    msg:'OK'
  })
});

module.exports = router;