const path = require('path');
const views = require('koa-views');
const config = require('../config')

module.exports = views(path.join(__dirname), {
    noCache: config.debug,
    map: { html: 'jade' },
    extension: 'jade'
});