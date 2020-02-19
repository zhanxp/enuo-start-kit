var express = require('express');
var path = require('path');
// var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
// var db = require('./app_server/models/mysql');
var config = require('./app_server/config');
var session = require('express-session');
var flash = require('connect-flash');
var wrap = require('co-express');
var cors = require('cors');
var enuocms = require('enuo-core'); //require('./app_server/models');

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */

var app = express();

// var router = express.Router();
// router.use(function(req, res, next) {
//     console.log('Time:', Date.now());
//     next();
// });

// view engine setup
app.set('views', path.join(__dirname, 'app_server', 'views'));
app.set('view engine', 'jade');

// uncomment after placing your favicon in /public
//app.use(favicon(path.join(__dirname, 'public', 'favicon.ico')));
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cors());
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

// Use the session middleware 
app.use(session({
    secret: 'enuocms',
    cookie: ('enuo-auth', 'value', { path: '/', httpOnly: false, secure: false, maxAge: 60 * 1000 * 60 * 24 }),
    resave: true,
    saveUninitialized: true,
}));

app.use(flash());

app.use(async function(req, res, next) {
    //console.log('all-', req.url);
    app.locals.moment = require('moment');

    res.json = function(obj) {
        res.writeHead(200, { "Content-Type": "application/json;charset=utf-8" });
        res.end(JSON.stringify(obj));
    }

    if (req.url.match(/favicon\.ico$/)) {
        res.end("");
        return;
    }

    if (req.session.user) {
        res.locals.user = req.session.user;
        next();
    } else {
        var arr = req.url.split('/');
        for (var i = 0, length = arr.length; i < length; i++) {
            arr[i] = arr[i].split('?')[0];
        }
        //console.log(JSON.stringify(arr));
        var controller = arr.length > 1 ? arr[1] : '';
        var action = arr.length > 2 ? arr[2] : '';
        var url = controller + '/' + action;
        if (controller == 'api') {
            if (action == '' || action == 'login' || action == 'logout') {
                next();
            } else {
                var ticket = req.query.ticket || req.get("ticket");
                var user = null;
                if (ticket) {
                    var userStr = await enuocms.redis.get(ticket);
                    if (userStr) {
                        user = JSON.parse(userStr);
                    }
                }

                if (!user || !user.id) {
                    res.json(enuocms.api.error("认证失败，请重新登录后再试！", 401));
                }

                res.locals.user = user;
                next();
            }
        } else {
            var openUrl = ['account/login', 'account/logout'];
            if (openUrl.indexOf(url) > -1) {
                next();
            } else {
                req.session.originalUrl = req.originalUrl ? req.originalUrl : null;
                req.flash('error', '请先登录');
                res.redirect('/account/login');
            }
        }
    }
});

app.use('/', require('./app_server/routes/index'));
app.use('/admin', require('./app_server/routes/admin'));
app.use('/article', require('./app_server/routes/article'));
app.use('/category', require('./app_server/routes/category'));
app.use('/account', require('./app_server/routes/account'));
app.use('/api', require('./app_server/routes/api'));

// catch 404 and forward to error handler
app.use(function(req, res, next) {
    var err = new Error('Not Found');
    err.status = 404;
    next(err);
});

// error handler
app.use(function(err, req, res, next) {
    // set locals, only providing error in development
    res.locals.message = err.message;
    res.locals.error = req.app.get('env') === 'development' ? err : {};

    // render the error page
    res.status(err.status || 500);
    res.render('error/index');
});

enuocms.mysql.debug = config.debug;
enuocms.mysql.connect(config.mysql);

enuocms.redis.debug = config.debug;
enuocms.redis.connect(config.redis);
var server = app.listen(config.port, function() {
    console.log("http://localhost:" + server.address().port);
});


module.exports = app;