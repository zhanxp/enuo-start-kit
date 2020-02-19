var koa = require('koa');
const logger = require('koa-logger');
// const router = require('koa-router')();
const koaBody = require('koa-body');
const render = require('./nunjucks/render');
const serve = require('koa-static');
var config = require('./config');
const session = require('koa-session');
var flash = require('koa-flash');
var cors = require('koa-cors');
var enuocms = require('enuo-core');

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
var app = new koa();
app.use(logger());
app.use(koaBody());
app.use(cors());
app.use(flash());
//views
app.use(render);
//static
app.use(serve('public'));

//session
app.keys = ['some secret key'];
app.use(session({
    key: "SESSIONID", //default "koa:sid" 
    maxAge: 86400000,
    overwrite: true,
    httpOnly: false,
    signed: true,
    rolling: false
}, app));

//moment
app.use(async function(ctx, next) {
    ctx.json = function(obj) {
        ctx.set("Content-Type", "application/json;charset=utf-8");
        ctx.body = JSON.stringify(obj);
    }

    ctx.state.moment = require('moment');
    if (ctx.url.match(/favicon\.ico$/)) {
        ctx.body = "";
        return;
    }

    if (ctx.session.user) {
        ctx.state.user = ctx.session.user;
        await next();
    } else {
        var arr = ctx.url.split('/');
        for (var i = 0, length = arr.length; i < length; i++) {
            arr[i] = arr[i].split('?')[0];
        }

        var controller = arr.length > 1 ? arr[1] : '';
        var action = arr.length > 2 ? arr[2] : '';
        var url = controller + '/' + action;
        if (controller == 'api') {
            console.log(action);
            if (action == '' || action == 'login' || action == 'logout') {
                await next();
            } else {
                var ticket = ctx.query.ticket || ctx.request.body.ticket || ctx.headers.ticket;
                var user = null;
                if (ticket) {
                    var userStr = await enuocms.redis.get(ticket);
                    if (userStr) {
                        user = JSON.parse(userStr);
                    }
                }

                if (!user || !user.id) {
                    ctx.json(enuocms.api.error("认证失败，请重新登录后再试！", 401));
                    return;
                }

                ctx.state.user = user;
                await next();
            }
        } else {
            var openUrl = ['account/login', 'account/logout'];
            if (openUrl.indexOf(url) > -1) {
                await next();
            } else {
                ctx.session.originalUrl = ctx.originalUrl ? ctx.originalUrl : null;
                ctx.flash.error = '请先登陆！';
                await ctx.redirect('/account/login');
            }
        }
    }
});

//favicon 
// router.use(favicon);
// async function favicon(ctx, next) {
//     if (ctx.url.match(/favicon\.ico$/)) {
//         ctx.body = "";
//         return;
//     }
//     await next();
// };
// app.use(router.routes());

app.use(require('./routes/index').routes());
app.use(require('./routes/category').routes());
app.use(require('./routes/article').routes());
app.use(require('./routes/account').routes());
app.use(require('./routes/api').routes());

app.use(async function pageNotFound(ctx, next) {
    var info = {
        error: {
            status: 404,
            stack: 'Page Not Found'
        },
        message: 'Page Not Found'
    };

    ctx.status = 404;
    switch (ctx.accepts('html', 'json')) {
        case 'html':
            ctx.type = 'html';
            //ctx.body = '<p>Page Not Found</p>';
            await ctx.render('error/404', info);
            break;
        case 'json':
            ctx.body = {
                code: 404,
                msg: 'Page Not Found'
            };
            break;
        default:
            ctx.type = 'text';
            //ctx.body = 'Page Not Found';
            await ctx.render('error/404', info);
    }
});

app.use(async function(ctx, next) {
    try {
        await next();
    } catch (err) {
        ctx.status = err.status || 500;
        var info = {
            error: {
                status: ctx.status
            },
            message: 'Server Error'
        };

        ctx.type = 'html';
        await ctx.render('error/index', info);
        //ctx.body = '<p>Something <em>exploded</em>, please contact Maru.</p>';

        ctx.app.emit('error', err, ctx);
    }
});

app.on('error', function(err) {
    if (process.env.NODE_ENV != 'development') {
        console.log('sent error %s to the cloud', err.message);
        console.log(err);
    }
});

app.listen(config.port);

enuocms.mysql.debug = config.debug;
enuocms.mysql.connect(config.mysql);

enuocms.redis.debug = config.debug;
enuocms.redis.connect(config.redis);

module.exports = app;