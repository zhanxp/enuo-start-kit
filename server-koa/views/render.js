const path = require('path');
const views = require('koa-views');

module.exports = views(path.join(__dirname), {
    map: { html: 'jade' },
    extension: 'jade'
});