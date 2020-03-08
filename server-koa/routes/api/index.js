const router = require('koa-router')();
var api = require('enuo-core').api;
var redis = require('enuo-core').redis;
var uuid = require('node-uuid');
var config = require('../../config');

var adminService = require('../../service/admin/adminServcie');

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
 *       data:
 *         type: object
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
 *   Admin:
 *     properties:
 *       id:
 *         type: integer
 *       name:
 *         type: string
 */

 /**
* @swagger
* /api/login:
*  post:
*     tags:
*       - Account
*     description:  user login
*     produces:
*       - application/json
*     parameters:
*       - name: passport
*         in: query
*         type: string
*       - name: password
*         in: query
*         type: string
*     responses:
*       200:
*         description: user login
*         schema:
*          $ref: '#/definitions/ApiResult'
*/
router.post('/login', async function(ctx, next) {
    var username = ctx.request.body.passport;
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

module.exports = router;