const router = require('koa-router')();
var api = require('enuo-core').api;
var redis = require('enuo-core').redis;
var uuid = require('node-uuid');
var config = require('../config');

var adminService = require('../service/admin/adminServcie');
var articleServcie = require('../service/article/articleServcie');
var categoryService = require('../service/category/categoryService');

/**
 * @swagger
 * definitions:
 *   ApiResult:
 *     properties:
 *       success:
 *         type: boolean
 *       code:
 *          type: integer
 *       msg:
 *         type: integer
 *   PageList:
 *     properties:
 *       pageIndex:
 *          type: integer
 *       pageSize:
 *          type: integer
 *       total:
 *          type: integer
 *       items:
 *          type: array
 *   Article:
 *     properties:
 *       id:
 *         type: integer
 *       title:
 *         type: string
 *   Category:
 *     properties:
 *       id:
 *         type: integer
 *       title:
 *         type: string
 *   Admin:
 *     properties:
 *       id:
 *         type: integer
 *       name:
 *         type: string
 */

router.post('/login', async function(ctx, next) {
    var username = ctx.request.body.username;
    var password = ctx.request.body.password;
    var user = await adminService.login(username, password);
    if (!user || !user.id) {
        ctx.json(api.error("登录失败！"));
        return;
    }

    user.password = null;
    user.ticket = uuid.v4();
    user.expires = config.account.expire_in;
    await redis.set(user.ticket, JSON.stringify(user), user.expires);

    ctx.json(api.data(user));
});


router.get('/logout', async function(ctx, next) {
    var ticket = ctx.query.ticket;
    if (ticket) {
        await redis.del(ticket);
    }
    ctx.json(api.success());
});

router.get('/profile', async function(ctx, next) {
    var admin = await adminService.findById(ctx.state.user.id);
    delete admin.password;
    ctx.json(api.data(admin));
});

/**
 * @swagger
 * /article/pageList:
 *  get:
 *     tags:
 *       - Article
 *     description:  article  pagelist 
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: ticket
 *         in: query
 *         type: string
 *       - name: pageIndex
 *         in: query
 *         type: integer
 *       - name: pageSize
 *         in: query
 *         type: integer
 *     responses:
 *       200:
 *         description: article pagelist 
 *         schema:
 *          $ref: '#/definitions/PageList'
 *          properties:
 *            items:
 *             type: array
 *             items:
 *              $ref: "#/definitions/Article"
 */
router.get('/article/pageList', async function (ctx, next) {
    var pageIndex = parseInt(ctx.query.pageIndex) || 1;
    var pageSize = parseInt(ctx.query.pageSize) || 10;

    var pageList = await articleServcie.pageList(pageIndex, pageSize);

    ctx.json(api.data(pageList));
});

router.get('/article/detail/:id', async function(ctx, next) {
    var id = ctx.params.id;
    var model = await articleServcie.findById(id);

    ctx.json(api.data(model));
});

router.get('/category/list', async function(ctx, next) {
    var list = await categoryService.list();
    ctx.json(api.data(list));
});


router.prefix('/api');
module.exports = router;