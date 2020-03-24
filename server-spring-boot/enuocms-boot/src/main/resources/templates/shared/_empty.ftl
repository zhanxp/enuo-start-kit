<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Spring MVC</title>
    ${htmlTitle!""}
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport" />
    <script type="text/javascript">
        var APP_BASE = '${base}';
    </script>
    <link rel='stylesheet' href='${base}/webjars/bootstrap/3.1.0/css/bootstrap.min.css' />
    <link rel='stylesheet' href='${base}/static/site.css'>
    <script type="text/javascript" src="${base}/webjars/jquery/1.9.0/jquery.min.js" ></script>
    <script type="text/javascript" src="${base}/webjars/bootstrap/3.1.0/js/bootstrap.min.js" ></script>
    ${htmlHead!""}
</head>
<body >
    <div class="container body-content">
    ${contentBody}
    </div>

    ${htmlScript!""}

</body>
</html>