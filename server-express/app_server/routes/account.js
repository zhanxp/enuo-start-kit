var express = require('express');
var router = express.Router();
var adminService = require('../service/admin/adminServcie');
/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
router.get('/login', async function(req, res, next) {
    res.render('account/login', { title: 'Login' });
});

router.post('/login', async function(req, res, next) {
    // 判断用户名密码是否正确
    var username = req.body.username;
    var password = req.body.password;


    var user = await adminService.login(username, password);
    if (user == null) {
        req.flash('error', '用户名密码错误，登录失败！');
        res.redirect('login');
        return;
    }

    req.session.user = user;
    console.log(user);

    var redirectUrl = '/';
    if (req.session.originalUrl) {
        redirectUrl = req.session.originalUrl;
        req.session.originalUrl = null;
    }
    res.redirect(redirectUrl);
});


router.get('/logout', async function(req, res, next) {
    req.session.user = null;
    res.redirect('login');
});

module.exports = router;