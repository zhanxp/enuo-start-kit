var express = require('express');
var router = express.Router();
var adminService = require('../service/admin/adminServcie');
var articleServcie = require('../service/article/articleServcie');
var api = require('enuo-core').api;
var redis = require('enuo-core').redis;
var uuid = require('node-uuid');
var config = require('../config');

router.get('/', async function(req, res, next) {
    //res.end(api.success("it works!"));
    res.json(api.success("it works!"));
});

router.get('/login', async function(req, res, next) {
    var username = req.query.username;
    var password = req.query.password;
    var user = await adminService.login(username, password);
    if (!user || !user.id) {
        res.json(api.error("登录失败！"));
    }

    user.password = null;
    user.ticket = uuid.v4();
    user.expires = config.account.expire_in;
    await redis.set(user.ticket, JSON.stringify(user), user.expires);

    res.json(api.data(user));
});


router.get('/logout', async function(req, res, next) {
    var ticket = req.query.ticket;
    if (ticket) {
        await redis.del(ticket);
    }
    res.json(api.success());
});

router.get('/profile', async function(req, res, next) {
    var admin = await adminService.findById(res.locals.user.id);
    delete admin.password;
    res.json(api.data(admin));
});

router.get('/article/list', async function(req, res, next) {
    var pageIndex = req.query.pageIndex || 1;
    var pageSize = req.query.pageSize || 10;

    var pageList = await articleServcie.pageList(pageIndex, pageSize);

    res.json(api.data(pageList));
});

router.get('/article/detail/:id', async function(req, res, next) {
    var id = req.params.id;
    var model = await articleServcie.findById(id);

    res.json(api.data(model));
});

router.get('/category/list', async function(req, res, next) {

    var list = await articleServcie.categoryList(null);

    res.json(api.data(list));
});

module.exports = router;