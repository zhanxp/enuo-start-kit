var express = require('express');
var router = express.Router();

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
router.get('/', async function(req, res, next) {
    res.render('home/index', { title: 'Home' });
});

router.get('/about', async function(req, res, next) {
    res.render('home/about', { title: 'About' });
});

module.exports = router;