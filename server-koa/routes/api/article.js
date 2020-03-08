const router = require('koa-router')();
var api = require('enuo-core').api;
var redis = require('enuo-core').redis;
var uuid = require('node-uuid');
var config = require('../../config');

var articleServcie = require('../../service/article/articleServcie');


/**
 * @swagger
 * definitions:
 *   Article:
 *     properties:
 *       id:
 *         type: integer
 *       title:
 *         type: string
 *   PageList«Article»:
 *     properties:
 *       pageIndex:
 *          type: integer
 *       pageSize:
 *          type: integer
 *       total:
 *          type: integer
 *       items:
 *          type: array
 *          items:
 *            $ref: "#/definitions/Article"
 *   ApiResult«PageList«Article»»:
 *     properties:
 *       success:
 *         type: boolean
 *       code:
 *          type: integer
 *       msg:
 *         type: integer
 *       data:
 *         $ref: "#/definitions/PageList«Article»"
 */



/**
 * @swagger
 * /api/article/pageList:
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
 *          $ref: '#/definitions/ApiResult«PageList«Article»»'
 */
router.get('/pageList', async function (ctx, next) {
    var pageIndex = parseInt(ctx.query.pageIndex) || 1;
    var pageSize = parseInt(ctx.query.pageSize) || 10;

    var pageList = await articleServcie.pageList(pageIndex, pageSize);

    ctx.json(api.data(pageList));
});

router.get('/detail/:id', async function(ctx, next) {
    var id = ctx.params.id;
    var model = await articleServcie.findById(id);

    ctx.json(api.data(model));
});

module.exports = router;