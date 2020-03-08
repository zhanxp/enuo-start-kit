var koa = require('koa');
const logger = require('koa-logger');
const koaBody = require('koa-body');
const render = require('./views/render');
const serve = require('koa-static');
var config = require('./config');
const session = require('koa-session');
var flash = require('koa-flash');
var cors = require('koa-cors');
var core = require('enuo-core');
var path = require('path');
var fs = require('fs');

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
    
    var arr = ctx.url.split('/');
    for (var i = 0, length = arr.length; i < length; i++) {
      arr[i] = arr[i].split('?')[0];
    }

    var controller = arr.length > 1 ? arr[1] : '';
    var action = arr.length > 2 ? arr[2] : '';
    var url = controller + '/' + action;

    if (controller == 'api') {
      if (action == '' || action == 'login' || action == 'logout') {
        await next();
      } else {
        var ticket = ctx.query.ticket || ctx.request.body.ticket || ctx.headers.ticket;
        var user = null;
        if (ticket) {
          var userStr = await core.redis.get(ticket);
          if (userStr) {
            user = JSON.parse(userStr);
          }
        }
        if (!user || !user.id) {
          ctx.json(core.api.error("认证失败，请重新登录后再试！", 401));
          return;
        }
        ctx.state.user = user;
        await next();
      }
    } else if (controller == 'admin') {
      if (ctx.session.user) {
        ctx.state.user = ctx.session.user;
        await next();
      } else {
          ctx.session.originalUrl = ctx.originalUrl ? ctx.originalUrl : null;
          ctx.flash.error = '请先登陆！';
          await ctx.redirect('/account/login');
      }
    } else { 
      ctx.state.user = ctx.session.user;
      await next();
    }
});


app.use(async function (ctx, next) {
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
    ctx.app.emit('error', err, ctx);
  }
});

// app.use(require('./routes/index').routes());
// app.use(require('./routes/article').routes());
// app.use(require('./routes/admin').routes());
// app.use(require('./routes/account').routes());
// app.use(require('./routes/api').routes());
var dir = path.join(__dirname, './routes');
var files = fs.readdirSync(dir);
files.forEach(function (file, index) {
  var curPath = path.join(dir, file);
  if (fs.statSync(curPath).isDirectory()) {
    var subfiles = fs.readdirSync(curPath);
    subfiles.forEach(function (subfile, sindex) {
      var jsIndex = subfile.indexOf('.js');
      if (subfile == 'index.js') {
        var router = require('./routes/' + file);
        router.prefix('/' + file);
        app.use(router.routes());
      } else if (jsIndex > 0) {
        var name = subfile.substring(0, jsIndex);
        var router = require('./routes/' + file + '/' + name);
        router.prefix('/' + file + '/' + name);
        app.use(router.routes());
      }
    });
  } else {
    var jsIndex = file.indexOf('.js');
    var name = file.substring(0, jsIndex);
    var router = require('./routes/' + name);
    if (name != 'index') {
      router.prefix("/"+name);
    }
    app.use(router.routes());
  }
});

//** swagger-ui */
if (config.debug==true){
  const router = require('koa-router')();
  var swaggerJSDoc = require('swagger-jsdoc');

  var options = {
    swaggerDefinition: {
      info: {
        title: 'Node Swagger API',
        version: '2.0.0',
        description: 'Demonstrating how to desribe a RESTful API with Swagger',
      },
      host: '127.0.0.1:' + config.port,
      basePath: '/api',
    },
    apis: ['./routes/api/*.js'],
  };

  var swaggerSpec = swaggerJSDoc(options);
  router.get('/v2/api-docs', function (ctx, next) {
    ctx.json(swaggerSpec);
  });

  const pathToSwaggerUi = require('swagger-ui-dist').absolutePath();
  app.use(serve(pathToSwaggerUi, {
    index: 'swagger-ui.html'
  }));
  const indexContent = fs.readFileSync(`${pathToSwaggerUi}/index.html`)
    .toString()
    .replace("https://petstore.swagger.io/v2/swagger.json", "/v2/api-docs");

  //swagger-ui.html
  router.get("/swagger-ui.html", function (ctx, next) {
    ctx.type = 'html';
    ctx.body = indexContent;
  });

  app.use(router.routes());
}

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


app.on('error', function(err) {
    if (process.env.NODE_ENV != 'development') {
        console.log('sent error %s to the cloud', err.message);
        console.log(err);
    }
});

app.listen(config.port);

core.mysql.debug = config.debug;
core.mysql.connect(config.mysql);

core.redis.debug = config.debug;
core.redis.connect(config.redis);

module.exports = app;