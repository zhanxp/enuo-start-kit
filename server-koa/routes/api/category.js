const router = require('koa-router')();
var api = require('enuo-core').api;
var redis = require('enuo-core').redis;
var uuid = require('node-uuid');
var config = require('../../config');

var categoryService = require('../../service/category/categoryService');

/**
 * @swagger
 * definitions:
 *   Category:
 *     properties:
 *       id:
 *         type: integer
 *       title:
 *         type: string
 *   ApiResult«List«Category»»:
 *     properties:
 *       success:
 *         type: boolean
 *       code:
 *          type: integer
 *       msg:
 *         type: integer
 *       data:
 *          type: array
 *          items:
 *            $ref: "#/definitions/Category"
 */

/**
 * @swagger
 * /api/category/list:
 *  get:
 *     tags:
 *       - Category
 *     description:  categoyr  pagelist 
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: ticket
 *         in: query
 *         type: string
 *     responses:
 *       200:
 *         description: category list 
 *         schema:
 *          $ref: '#/definitions/ApiResult«List«Category»»'
 */
router.get('/list', async function(ctx, next) {
    var list = await categoryService.list();
    ctx.json(api.data(list));
});

module.exports = router;