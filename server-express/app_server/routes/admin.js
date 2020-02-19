var express = require('express');
var router = express.Router();
var adminService = require('../service/admin/adminServcie');


/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
router.get('/', async function(req, res, next) {
    var admin = await adminService.findById(req.session.user.id);
    // res.send('respond with a resource');
    res.render('admin/index', { title: 'Profile', profile: admin });
});

module.exports = router;