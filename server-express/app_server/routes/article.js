var express = require('express');
var router = express.Router();
var articleServcie = require('../service/article/articleServcie');


/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
router.get('/', async function(req, res, next) {
    var pageIndex = req.query.pageIndex || 1;
    var pageSize = req.query.pageSize || 10;

    var pageList = await articleServcie.pageList(pageIndex, pageSize);

    // res.send('respond with a resource');
    var pageInfo = pageList;
    pageInfo.url = '?';

    res.render('shared/_pager', pageInfo, function(err, html) {
        res.render('article/index', { title: 'Article', pageList: pageList, pager: html });
    });

});

router.get('/edit/:id', async function(req, res, next) {
    var id = req.params.id;

    var model = await articleServcie.findById(id);

    res.render('article/edit', { title: 'Edit', model: model });
});

module.exports = router;