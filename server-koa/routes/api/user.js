const router = require('koa-router')();
var api = require('enuo-core').api;
var redis = require('enuo-core').redis;
var uuid = require('node-uuid');
var config = require('../../config');

var adminService = require('../../service/admin/adminServcie');

router.get('/profile', async function(ctx, next) {
    var admin = await adminService.findById(ctx.state.user.id);
    delete admin.password;
    ctx.json(api.data(admin));
});

module.exports = router;