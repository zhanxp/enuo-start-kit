var express = require('express');
var router = express.Router();
var articleServcie = require('../service/article/articleServcie');


/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
router.get('/', async function(req, res, next) {
    var list = await articleServcie.categoryList(null);
    // res.send('respond with a resource');
    res.render('category/index', { title: 'Category', list: list });
});

module.exports = router;